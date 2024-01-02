package com.example.umchackathonr.domain.friend;

import com.example.umchackathonr.domain.friend.dto.FriendRequest;
import com.example.umchackathonr.domain.friend.dto.CreateFriendResponse;
import com.example.umchackathonr.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Friend", description = "친구 관련 API")
public class FriendController {

    private final FriendService friendService;
    private final UserRepository userRepository;

    @PostMapping("/api/{userId}/friend")
    @Operation(summary = "친구 생성", description = "새로운 친구 정보를 등록합니다.")
    @ApiResponse(responseCode = "201", description = "친구 생성 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateFriendResponse.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "409", description = "이미 등록된 친구 정보입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<CreateFriendResponse> createFriend(@RequestParam(defaultValue = "1") Long userId,
                                                             @Valid @RequestBody FriendRequest request) {
        Long id = friendService.save(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateFriendResponse(id));
    }

    @PatchMapping("/api/{userId}/friend/{friendId}")
    @Operation(summary = "친구 수정", description = "친구 정보를 수정합니다.")
    @ApiResponse(responseCode = "201", description = "친구 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "403", description = "존재하지 않는 친구 ID입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Map<String, String>> updateFriend(@RequestParam(defaultValue = "1") Long userId,
                                                            @RequestParam(defaultValue = "1") Long friendId,
                                                            @Valid @RequestBody FriendRequest request) {
        friendService.update(userId, request, friendId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "수정 완료");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/api/{userId}/friend/{friendId}")
    @Operation(summary = "친구 삭제", description = "친구 정보를 삭제합니다.")
    @ApiResponse(responseCode = "201", description = "친구 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "403", description = "존재하지 않는 친구 ID입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Map<String, String>> deleteFriend(@RequestParam(defaultValue = "1") Long userId,
                                                            @RequestParam(defaultValue = "1") Long friendId) {


        friendService.delete(userId, friendId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "삭제 완료");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

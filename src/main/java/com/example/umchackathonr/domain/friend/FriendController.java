package com.example.umchackathonr.domain.friend;

import com.example.umchackathonr.domain.friend.dto.*;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Friend", description = "친구 관련 API")
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/api/{userId}/friend")
    @Operation(summary = "친구 생성", description = "새로운 친구 정보를 등록합니다.")
    @ApiResponse(responseCode = "201", description = "친구 생성 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateFriendResponse.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "409", description = "이미 등록된 친구 정보입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<CreateFriendResponse> createFriend(@PathVariable Long userId,
                                                             @Valid @RequestBody FriendRequest request) {
        Long id = friendService.save(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateFriendResponse(id));
    }


    @GetMapping("/api/{userId}/friend")
    @Operation(summary = "친구 전체 조회", description = "유저의 친구 목록을 조회합니다.")
    @ApiResponse(responseCode = "201", description = "친구 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = FriendResponse.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Result<FriendResponse>> findAllFriend(@PathVariable Long userId) {
        List<Friend> findFriends = friendService.findFriends(userId);

        List<FriendResponse> collect = findFriends.stream()
                .map(m -> FriendResponse.builder()
                        .friendId(m.getId())
                        .friendName(m.getName())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(new Result<>(collect.size(), collect));
    }

    @GetMapping("/api/friend/{friendId}")
    @Operation(summary = "친구 상세 조회", description = "친구를 상세 조회합니다.")
    @ApiResponse(responseCode = "201", description = "친구 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = FriendDetailResponse.class))
    })
    @ApiResponse(responseCode = "403", description = "존재하지 않는 친구 ID입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<FriendDetailResponse> findFriend(@PathVariable Long friendId) {
        FriendDetailResponse friendDetailResponse = friendService.findFriend(friendId);

        return ResponseEntity.ok().body(friendDetailResponse);
    }

    @PatchMapping("/api/friend/{friendId}")
    @Operation(summary = "친구 수정", description = "친구 정보를 수정합니다.")
    @ApiResponse(responseCode = "201", description = "친구 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "403", description = "존재하지 않는 친구 ID입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Map<String, String>> updateFriend(
            @PathVariable Long friendId,
            @Valid @RequestBody FriendRequest request) {
        friendService.update(request, friendId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "수정 완료");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/api/friend/{friendId}")
    @Operation(summary = "친구 삭제", description = "친구 정보를 삭제합니다.")
    @ApiResponse(responseCode = "201", description = "친구 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "403", description = "존재하지 않는 친구 ID입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Map<String, String>> deleteFriend(
            @PathVariable Long friendId) {


        friendService.delete(friendId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "삭제 완료");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

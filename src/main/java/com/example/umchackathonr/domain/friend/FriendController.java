package com.example.umchackathonr.domain.friend;

import com.example.umchackathonr.domain.friend.dto.CreateFriendRequest;
import com.example.umchackathonr.domain.friend.dto.CreateFriendResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiResponse(responseCode = "409", description = "이미 등록된 친구 정보입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<CreateFriendResponse> saveMission(@RequestParam(defaultValue = "1") int userId,
                                                            @Valid @RequestBody CreateFriendRequest request) {
        Long id = friendService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateFriendResponse(id));
    }
}

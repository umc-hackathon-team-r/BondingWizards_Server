package com.example.umchackathonr.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{

    // 추가할 에러
    INACTIVE_USER(HttpStatus.FORBIDDEN, "존재하지 않는 유저입니다."),
    INACTIVE_FRIEND(HttpStatus.FORBIDDEN, "존재하지 않는 친구 ID입니다."),
    DUPLICATION_FRIEND(HttpStatus.CONFLICT, "이미 등록된 친구 정보입니다."),
    INACTIVE_PRESENT(HttpStatus.NOT_FOUND, "존재하지 않는 선물입니다."),
    INACTIVE_CUSTOM_EVENT(HttpStatus.NOT_FOUND, "존재하지 않는 이벤트 입니다."),
    INACTIVE_EVENT(HttpStatus.NOT_FOUND, "존재하지 않는 이벤트입니다.");




    // 기본 에러 형식
    private final HttpStatus httpStatus;
    private final String message;

}

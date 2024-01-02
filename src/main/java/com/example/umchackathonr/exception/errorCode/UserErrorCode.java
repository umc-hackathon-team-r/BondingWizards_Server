package com.example.umchackathonr.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{

    // 추가할 에러
    INACTIVE_USER(HttpStatus.FORBIDDEN, "User is inactive");



    // 기본 에러 형식
    private final HttpStatus httpStatus;
    private final String message;

}
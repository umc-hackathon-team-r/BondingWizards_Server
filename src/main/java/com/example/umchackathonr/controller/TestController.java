package com.example.umchackathonr.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "해커톤 API", description = "해당 API에 대한 설명 + {domain}-Service API")
@RequestMapping("")
public class TestController {
    @GetMapping(value = "/health-check")
    @Operation(summary = "요약", description = "API 설명 작성해주게요")
    public ResponseEntity<?> healthcheck(){
        return ResponseEntity.ok().build();
    }

}

//swagger 주소입니다. localhost:8080/swagger-ui/index.html#/
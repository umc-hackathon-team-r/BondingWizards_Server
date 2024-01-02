package com.example.umchackathonr.global.s3;

import com.example.umchackathonr.global.s3.dto.S3Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "S3 API", description = "S3 파일 업로드 + {domain}-Service API")
@RequestMapping("")
@RequiredArgsConstructor
public class S3Controller {

  private final S3Service s3Service;

  @Operation(summary = "S3에 파일 업로드")
  @PostMapping("/files")
  public ResponseEntity<S3Result> uploadFiles(
      @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
    return ResponseEntity.ok(s3Service.uploadFile(multipartFile));
  }
}
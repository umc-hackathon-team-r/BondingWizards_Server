package com.example.umchackathonr.global.s3;

import com.example.umchackathonr.global.s3.dto.S3Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.net.URLEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @PostMapping("/uploadfile")
  public ResponseEntity<S3Result> uploadFiles(
      @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
    return ResponseEntity.ok(s3Service.uploadFile(multipartFile));
  }
  @Operation(summary = "버킷에서 파일 다운로드")
  @GetMapping("/downloadfile")
  public ResponseEntity<ByteArrayResource> downloadFiles(@RequestParam String fileName){
    try{
      byte[] data = s3Service.download(fileName);
      ByteArrayResource resource = new ByteArrayResource(data);
      return ResponseEntity
          .ok()
          .contentLength(data.length)
          .header("Content-type", "application/octet-stream")
          .header("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"")
          .body(resource);
    }catch (IOException ex){
      return ResponseEntity.badRequest().contentLength(0).body(null);
    }
  }
}
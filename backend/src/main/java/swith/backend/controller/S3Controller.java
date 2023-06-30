package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.dto.S3DeleteDto;
import swith.backend.dto.S3DownloadDto;
import swith.backend.service.S3Service;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    /**
     * 파일 업로드
     */
    @PostMapping("/{postId}/upload")
    public ResponseEntity<Object> uploadFiles(
            @PathVariable("postId") Long postId,
            @RequestParam(value = "fileType", defaultValue = "image") String fileType,
            @RequestPart(value = "files") List<MultipartFile> multipartFiles) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3Service.uploadFiles(postId, fileType, multipartFiles));
    }

    /**
     * 파일 삭제
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFile(
            @RequestBody S3DeleteDto s3DeleteDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3Service.deleteFile(s3DeleteDto.getUploadFilePath(), s3DeleteDto.getUploadFileName()));
    }

    /**
     * 파일 다운로드
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestBody S3DownloadDto s3DownloadDto) throws IOException {
        return s3Service.getFile(s3DownloadDto.getOriginalFileName());
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(s3Service.downloadFile(s3DownloadDto.getOriginalFileName()));
    }
}

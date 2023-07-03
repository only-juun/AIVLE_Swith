package swith.backend.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.domain.Attachment;
import swith.backend.dto.S3FileDto;
import swith.backend.exception.PostException;
import swith.backend.exception.PostExceptionType;
import swith.backend.repository.PostRepository;
import swith.backend.repository.S3Repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3Client amazonS3Client;
    private final S3Repository s3Repository;
    private final PostRepository postRepository;

    /**
     * S3로 파일 업로드
     */
    public List<S3FileDto> uploadFiles(Long postId, String fileType, List<MultipartFile> multipartFiles) {

        List<S3FileDto> s3files = new ArrayList<>();

        String uploadFilePath = fileType + "/" + getFolderName();

        for (MultipartFile multipartFile : multipartFiles) {

            String originalFileName = multipartFile.getOriginalFilename();
            String uploadFileName = getUuidFileName(originalFileName);
            String uploadFileUrl = "";

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType());

            try (InputStream inputStream = multipartFile.getInputStream()) {

                String keyName = uploadFilePath + "/" + uploadFileName; // ex) 구분/년/월/일/파일.확장자

                // S3에 폴더 및 파일 업로드
                amazonS3Client.putObject(
                        new PutObjectRequest(bucketName, keyName, inputStream, objectMetadata));

                // S3에 업로드한 폴더 및 파일 URL
                uploadFileUrl = amazonS3Client.getUrl(bucketName, keyName).toString();

            } catch (IOException e) {
                e.printStackTrace();
                log.error("Filed upload failed", e);
            }

            Attachment attachment = Attachment.builder()
                    .originalFileName(originalFileName)
                    .uploadFileName(uploadFileName)
                    .uploadFilePath(uploadFilePath)
                    .uploadFileUrl(uploadFileUrl)
                    .build();
            attachment.confirmPost(postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.POST_NOT_FOUND)));

            s3files.add(S3FileDto.builder()
                    .originalFileName(originalFileName)
                    .uploadFileName(uploadFileName)
                    .uploadFilePath(uploadFilePath)
                    .uploadFileUrl(uploadFileUrl)
                    .build());

            s3Repository.save(attachment);
        }

        return s3files;
    }

    /**
     * S3에 업로드된 파일 삭제
     */
    public String deleteFile(String uploadFilePath, String uuidFileName) {

        String result = "success";

        try {
            String keyName = uploadFilePath + "/" + uuidFileName; // ex) 구분/년/월/일/파일.확장자
            boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, keyName);
            if (isObjectExist) {
                Attachment attachment = s3Repository.findByUploadFileName(uuidFileName);
                amazonS3Client.deleteObject(bucketName, keyName);
                s3Repository.delete(attachment);

            } else {
                result = "file not found";
            }
        } catch (Exception e) {
            log.debug("Delete File failed", e);
        }

        return result;
    }

    /**
     * S3 파일 다운로드
     */

    public ResponseEntity<byte[]> getFile(String uploadFileName, String uploadFilePath) throws IOException {
        String keyName = uploadFilePath + "/" + uploadFileName;
        S3Object o = amazonS3Client.getObject(new GetObjectRequest(bucketName, keyName));
        S3ObjectInputStream objectInputStream = ((S3Object) o).getObjectContent();
        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(keyName, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(contentType(keyName));
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    private MediaType contentType(String keyName) {
        String[] arr = keyName.split("\\.");
        String type = arr[arr.length - 1];
        switch (type) {
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpeg":
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    /**
     * UUID 파일명 반환
     */
    public String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    /**
     * 년/월/일 폴더명 반환
     */
    private String getFolderName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", "/");
    }
}

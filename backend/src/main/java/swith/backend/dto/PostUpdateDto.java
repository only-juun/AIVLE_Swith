package swith.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDto {
    private Optional<String> title;
    private Optional<String> content;
    private Optional<MultipartFile> uploadFile;
}
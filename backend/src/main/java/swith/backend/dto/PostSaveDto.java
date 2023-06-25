package swith.backend.dto;

import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.domain.Post;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@NoArgsConstructor
public class PostSaveDto {
    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private Optional<MultipartFile> uploadFile;

    public PostSaveDto(String title, String content, Optional<MultipartFile> uploadFile) {
        this.title = title;
        this.content = content;
        this.uploadFile = uploadFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Optional<MultipartFile> getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(Optional<MultipartFile> uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}



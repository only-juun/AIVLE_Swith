package swith.backend.dto;

import java.util.Optional;

public class CommentUpdateDto {
    private Optional<String> content;

    public CommentUpdateDto(Optional<String> content) {
        this.content = content;
    }

    public Optional<String> getContent() {
        return content;
    }

    public void setContent(Optional<String> content) {
        this.content = content;
    }
}
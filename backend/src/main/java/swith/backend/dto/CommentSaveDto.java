package swith.backend.dto;

import swith.backend.domain.Comment;

public class CommentSaveDto {
    private String content;

    public CommentSaveDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
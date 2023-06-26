package swith.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDto {
    private String content;
}
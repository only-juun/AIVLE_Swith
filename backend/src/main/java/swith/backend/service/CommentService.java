package swith.backend.service;

import swith.backend.domain.Comment;
import swith.backend.dto.CommentUpdateDto;
import swith.backend.exception.CommentException;

public interface CommentService {
    void save(Long postId , Comment comment);

    void update(Long commentId, CommentUpdateDto commentUpdateDto);

    void remove(Long id) throws CommentException;
}

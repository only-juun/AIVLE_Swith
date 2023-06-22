package swith.backend.service;

import swith.backend.dto.CommentSaveDto;
import swith.backend.dto.CommentUpdateDto;
import swith.backend.exception.CommentException;

public interface CommentService {
    void save(Long postId , CommentSaveDto commentSaveDto);
    void saveReComment(Long postId, Long parentId ,CommentSaveDto commentSaveDto);

    void update(Long id, CommentUpdateDto commentUpdateDto);

    void remove(Long id) throws CommentException;
}

package swith.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swith.backend.config.SecurityUtil;
import swith.backend.domain.Comment;
import swith.backend.dto.CommentSaveDto;
import swith.backend.dto.CommentUpdateDto;
import swith.backend.exception.*;
import swith.backend.repository.CommentRepository;
import swith.backend.repository.PostRepository;
import swith.backend.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    /**
     * 댓글 등록
     */
    @Override
    public void save(Long postId, Comment comment) {
        comment.confirmWriter(userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER)));
        comment.confirmPost(postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.POST_NOT_FOUND)));

        commentRepository.save(comment);

    }

    /**
     * 댓글 수정
     */
    @Override
    public void update(Long commentId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT));
        checkAuthority(comment, CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT);

        commentUpdateDto.getContent().ifPresent(comment::updateContent);
    }

    /**
     * 댓글 삭제
     */
    @Override
    public void remove(Long id) throws CommentException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

        if(!comment.getUser().getUsername().equals(SecurityUtil.getLoginUsername())){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_DELETE_COMMENT);
        }
        commentRepository.delete(comment);
    }

    private void checkAuthority(Comment comment, CommentExceptionType commentExceptionType) {
        if(!comment.getUser().getUsername().equals(SecurityUtil.getLoginUsername()))
            throw new CommentException(commentExceptionType);
    }
}

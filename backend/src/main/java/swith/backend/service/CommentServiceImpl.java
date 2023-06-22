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
import swith.backend.repository.MemberRepository;
import swith.backend.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    public void save(Long postId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmWriter(memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER)));

        comment.confirmPost(postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.POST_NOT_POUND)));


        commentRepository.save(comment);

    }

    @Override
    public void saveReComment(Long postId, Long parentId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmWriter(memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER)));

        comment.confirmPost(postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.POST_NOT_POUND)));

//        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT)));

        commentRepository.save(comment);

    }



    @Override
    public void update(Long id, CommentUpdateDto commentUpdateDto) {

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

//        if(!comment.getMember().getUsername().equals(SecurityUtil.getLoginUsername())){
//            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT);
//        }

        commentUpdateDto.getContent().ifPresent(comment::updateContent);
    }



    @Override
    public void remove(Long id) throws CommentException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

        if(!comment.getMember().getUsername().equals(SecurityUtil.getLoginUsername())){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_DELETE_COMMENT);
        }

        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        commentRepository.deleteAll(removableCommentList);
    }
}

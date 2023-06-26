package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import swith.backend.domain.Comment;
import swith.backend.dto.CommentSaveDto;
import swith.backend.dto.CommentUpdateDto;
import swith.backend.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {


    private final CommentService commentService;

    /**
     * 댓글 등록
     */
    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentSave(@PathVariable("postId") Long postId,
                            @RequestBody CommentSaveDto commentSaveDto){
        Comment comment = Comment.builder()
                .content(commentSaveDto.getContent())
                .build();

        commentService.save(postId, comment);
    }


    /**
     * 댓글 수정
     */
    @PutMapping("/{commentId}")
    public void update(@PathVariable("commentId") Long commentId,
                       @RequestBody CommentUpdateDto commentUpdateDto){

        commentService.update(commentId, commentUpdateDto);
    }


    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId){
        commentService.remove(commentId);
    }
}

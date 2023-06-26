package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import swith.backend.dto.CommentSaveDto;
import swith.backend.dto.CommentUpdateDto;
import swith.backend.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    /**
     * 댓글 작성
     */
    @PostMapping("/comment/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentSave(@PathVariable("postId") Long postId, CommentSaveDto commentSaveDto){
        commentService.save(postId, commentSaveDto);
    }


    /**
     * 댓글 수정
     */
    @PutMapping("/comment/{commentId}")
    public void update(@PathVariable("commentId") Long commentId,
                       CommentUpdateDto commentUpdateDto){
        commentService.update(commentId, commentUpdateDto);
    }


    /**
     * 댓글 삭제
     */
    @DeleteMapping("/comment/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId){
        commentService.remove(commentId);
    }


//    @PostMapping("/comment/{postId}/{commentId}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void reCommentSave(@PathVariable("postId") Long postId,
//                              @PathVariable("commentId") Long commentId,
//                              CommentSaveDto commentSaveDto){
//        commentService.saveReComment(postId, commentId, commentSaveDto);
//    }
}

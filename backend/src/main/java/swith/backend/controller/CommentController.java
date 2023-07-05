package swith.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "comments", description = "댓글 API")
public class CommentController {


    private final CommentService commentService;

    /**
     * 댓글 등록
     */
    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "save comment", description = "댓글 등록하기")
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
    @Operation(summary = "update comment", description = "댓글 수정하기")
    @PutMapping("/{commentId}")
    public void update(@PathVariable("commentId") Long commentId,
                       @RequestBody CommentUpdateDto commentUpdateDto){

        commentService.update(commentId, commentUpdateDto);
    }

    /**
     * 댓글 삭제
     */
    @Operation(summary = "delete comment", description = "댓글 삭제하기")
    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId){
        commentService.remove(commentId);
    }
}

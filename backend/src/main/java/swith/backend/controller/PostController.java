package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swith.backend.cond.PostSearchCondition;
import swith.backend.domain.Post;
import swith.backend.dto.PostSaveDto;
import swith.backend.dto.PostUpdateDto;
import swith.backend.service.PostService;
import swith.backend.service.PostServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    /**
     * 게시글 저장
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public void save(@Valid @RequestBody PostSaveDto postSaveDto){
        postService.save(postSaveDto);
    }

    /**
     * 게시글 수정
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/post/{postId}")
    public void update(@PathVariable("postId") Long postId,
                       @ModelAttribute PostUpdateDto postUpdateDto){


        postService.update(postId, postUpdateDto);
    }

    /**
     * 게시글 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable("postId") Long postId){
        postService.delete(postId);
    }


    /**
     * 게시글 조회
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity getInfo(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(postService.getPostInfo(postId));
    }

    /**
     * 게시글 검색
     */
    @GetMapping("/post")
    public ResponseEntity search(Pageable pageable,
                                 @ModelAttribute PostSearchCondition postSearchCondition){

        return ResponseEntity.ok(postService.getPostList(pageable,postSearchCondition));
    }
}
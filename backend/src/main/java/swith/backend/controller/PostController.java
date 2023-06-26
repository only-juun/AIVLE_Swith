package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import swith.backend.domain.Post;
import swith.backend.dto.PostSaveDto;
import swith.backend.dto.PostUpdateDto;
import swith.backend.service.PostService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    /**
     * 게시글 등록
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody PostSaveDto postSaveDto){
        Post post = Post.builder()
                .title(postSaveDto.getTitle())
                .content(postSaveDto.getContent())
                .build();
        postService.register(post);
    }

    /**
     * 게시글 수정
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/edit/{postId}")
    public void update(@PathVariable("postId") Long postId,
                       @RequestBody PostUpdateDto postUpdateDto){

        postService.update(postId, postUpdateDto);
    }

    /**
     * 게시글 조회
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity getInfo(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(postService.getPostInfo(postId));
    }

    /**
     * 게시글 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/post/{postId}")
    @Transactional
    public void delete(@PathVariable("postId") Long postId){
        postService.delete(postId);
    }

    /**
     * 게시글 목록 조회
     */
//    @GetMapping("/postList")
//    public ResponseEntity search(Pageable pageable,
//                                 @RequestBody PostSearchCondition postSearchCondition){
//
//        return ResponseEntity.ok(postService.getPostList(pageable,postSearchCondition));
//    }
}
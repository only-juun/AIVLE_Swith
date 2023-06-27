package swith.backend.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import swith.backend.domain.Post;
import swith.backend.dto.PageNumberDto;
import swith.backend.dto.PostSaveDto;
import swith.backend.dto.PostUpdateDto;
import swith.backend.dto.PostsRespondDto;
import swith.backend.service.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;

    /**
     * 게시글 등록
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody PostSaveDto postSaveDto) {
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
                       @RequestBody PostUpdateDto postUpdateDto) {

        postService.update(postId, postUpdateDto);
    }


    /**
     * 게시글 조회
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity getInfo(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPostInfo(postId));
    }

    /**
     * 게시글 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/post/{postId}")
    @Transactional
    public void delete(@PathVariable("postId") Long postId) {
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

    /**
     * 게시글 페이징 조회
     */
    @GetMapping("/postList")
    public Page<PostsRespondDto> getPostList(@RequestParam("page") int page) {
        Page<Post> posts = postService.getPostList(page,2);

        log.info("전체 페이지 번호 : {}",posts.getTotalPages());
        log.info("전체 데이터 수 : {}",posts.getTotalElements());


        List<PostsRespondDto> result = posts.stream()
                .map(p -> new PostsRespondDto(p))
                .collect(Collectors.toList());

        return new PageImpl<>(result);
    }

    /**
     * 게시글 전체 페이지 번호와 전체 데이터 수
     */
    @GetMapping("/pageNumber")
    public PageNumberDto pageNumber() {
        Page<Post> posts = postService.getPageList(2);
        int totalPages = posts.getTotalPages();
        long totalElements = posts.getTotalElements();

        return new PageNumberDto(totalPages,totalElements);
    }


}
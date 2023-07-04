package swith.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.domain.Post;
import swith.backend.domain.PostSearch;
import swith.backend.dto.*;
import swith.backend.exception.PostException;
import swith.backend.repository.PostRepository;
import swith.backend.service.PostService;
import swith.backend.service.S3Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static swith.backend.exception.PostExceptionType.POST_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
@Tag(name = "posts", description = "게시물 API")
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;
    private final PostRepository postRepository;

    /**
     * 게시글 등록
     */
    @Operation(summary = "register post", description = "게시글 등록하기")
    @PostMapping(value = "/new", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)

    public void register(@Valid @RequestPart(value = "data") PostSaveDto postSaveDto,
                         @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles) {
        Post post = Post.builder()
                .title(postSaveDto.getTitle())
                .content(postSaveDto.getContent())
                .build();

        postService.register(post);

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            s3Service.uploadFiles(post.getId(), "image", multipartFiles);
        }
    }

    /**
     * 게시글 수정
     */
    @Operation(summary = "update post", description = "게시글 수정하기")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/edit/{postId}")
    public void update(@PathVariable("postId") Long postId,
                       @RequestBody PostUpdateDto postUpdateDto) {

        postService.update(postId, postUpdateDto);
    }


    /**
     * 게시글 조회
     */
    @Operation(summary = "getInfo post", description = "게시글 단건 조회하기")
    @GetMapping("/post/{postId}")
    public ResponseEntity getInfo(@PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(POST_NOT_FOUND));
        post.updateSearchCount(post.getSearchCount());
        postRepository.save(post);
        return ResponseEntity.ok(postService.getPostInfo(postId));
    }

    /**
     * 게시글 삭제
     */
    @Operation(summary = "delete post", description = "게시글 삭제하기")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable("postId") Long postId) {
        postService.delete(postId);
    }

    /**
     * 게시글 페이징 조회
     */
    @Operation(summary = "paging posts", description = "게시글 목록 조회하기")
    @GetMapping("/postList")
    public Page<PostsRespondDto> getPostList(@RequestParam("page") int page) {
        Page<Post> posts = postService.getPostList(page, 10);

        log.info("전체 페이지 번호 : {}", posts.getTotalPages());
        log.info("전체 데이터 수 : {}", posts.getTotalElements());


        List<PostsRespondDto> result = posts.stream()
                .map(p -> new PostsRespondDto(p,posts.getTotalPages()))
                .collect(Collectors.toList());


        return new PageImpl<>(result);
    }

    /**
     * 게시글 전체 페이지 번호와 전체 데이터 수
     */
    @Operation(summary = "board paging", description = "게시글 수정하기")
    @GetMapping("/pageNumber")
    public PageNumberDto pageNumber() {
        Page<Post> posts = postService.getPageList(10);
        int totalPages = posts.getTotalPages();
        long totalElements = posts.getTotalElements();

        return new PageNumberDto(totalPages, totalElements);
    }

    @Operation(summary = "search posts", description = "게시글 검색하기")
    @GetMapping("/search")
    public Page<SearchRespondDto> getCondList(@RequestParam("type") String type,
                                              @RequestParam("content") String content,
                                              @PageableDefault(size=10,sort="createdDate",direction = Sort.Direction.DESC) Pageable pageable){



        PostSearch postSearch = PostSearch.builder()
                .type(type)
                .content(content)
                .build();

//        List<Post> posts = postService.getSearchedPost(postSearch);

        PageImpl<Post> pagedSearchedPosts = postService.getPagedSearchedPosts(postSearch,pageable);

        List<SearchRespondDto> result = pagedSearchedPosts.stream()
                .map(p -> new SearchRespondDto(p))
                .collect(Collectors.toList());
        return new PageImpl<>(result);

//        Page<Post> postListPage = postService.PostSearch(postSearch,pageable);

//        Page<Post> posts = postService.PostSearch(postSearch,page,10);
//        log.info("total:{}",posts.getTotalPages());
//
//        List<SearchRespondDto> result = posts.stream()
//                .map(p -> new SearchRespondDto(p))
//                .collect(Collectors.toList());
//        return new PageImpl<>(result);
    }
}
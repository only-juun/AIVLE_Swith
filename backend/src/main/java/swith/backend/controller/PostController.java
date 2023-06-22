package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swith.backend.domain.Post;
import swith.backend.service.PostServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postlist")
public class PostController {

//    private final PostRepository postRepository;
    private final PostServiceImpl postService;

    @GetMapping()
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(posts);
    }

//    @PostMapping()
//    public ResponseEntity<Post> addPost(@RequestBody PostRequest postRequest) {
//        Post post = postRepository.save(postRequest);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(post);
//    }


}
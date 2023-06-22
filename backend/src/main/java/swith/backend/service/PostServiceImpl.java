package swith.backend.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.MemberRemoval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swith.backend.domain.Member;
import swith.backend.domain.Post;
import swith.backend.repository.PostRepository;
import java.util.List;

@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
//    private final MemberRepository memberRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    /**
     * 게시글 작성
     */
    public List<Post> findAll() {
        return postRepository.findAll();
    }

//    public Post save(PostRequest postRequest) {
//        return postRepository.save(postRequest.toEntity());
//    }

}

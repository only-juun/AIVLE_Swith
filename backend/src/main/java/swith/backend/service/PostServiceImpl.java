package swith.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swith.backend.config.SecurityUtil;
import swith.backend.domain.Attachment;
import swith.backend.domain.Post;
import swith.backend.domain.PostSearch;
import swith.backend.dto.PostInfoDto;
import swith.backend.dto.PostUpdateDto;
import swith.backend.exception.*;
import swith.backend.repository.PostRepository;
import swith.backend.repository.PostRepositorySupport;
import swith.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static swith.backend.exception.PostExceptionType.POST_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final PostRepositorySupport postRepositorySupport;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    /**
     * 게시글 저장
     */
    @Override
    @Transactional
    public void register(Post post){
        post.confirmWriter(userRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER)));

        postRepository.save(post);
    }

    /**
     * 게시글 수정
     */
    @Override
    @Transactional
    public void update(Long postId, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(POST_NOT_FOUND));
        checkAuthority(post,PostExceptionType.NOT_AUTHORITY_UPDATE_POST );

        postUpdateDto.getTitle().ifPresent(post::updateTitle);
        postUpdateDto.getContent().ifPresent(post::updateContent);

    }

    /**
     * 게시글 삭제
     */
    @Override
    @Transactional
    public void delete(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostException(POST_NOT_FOUND));

        checkAuthority(post, PostExceptionType.NOT_AUTHORITY_DELETE_POST);

        if (!post.getAttachments().isEmpty()) {
            for (Attachment attachment : post.getAttachments()) {
                String uploadFileName = attachment.getUploadFileName();
                String uploadFilePath = attachment.getUploadFilePath();
                s3Service.deleteFile(uploadFilePath, uploadFileName);
            }
        }

        postRepository.delete(post);
    }


    private void checkAuthority(Post post, PostExceptionType postExceptionType) {
        if(!post.getUser().getUsername().equals(SecurityUtil.getLoginUsername()))
            throw new PostException(postExceptionType);
    }

    /**
     * Post의 id를 통해 Post 조회
     */
    @Override
    public PostInfoDto getPostInfo(Long postId) {

        return new PostInfoDto(postRepository.findWithUserById(postId)
                .orElseThrow(() -> new PostException(POST_NOT_FOUND)));

    }

    /**
     * 게시글 페이징 조회
     */
    @Override
    public Page<Post> getPostList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Post> postList = postRepository.findAll(pageable);

        return postList;
    }

    @Override
    public Page<Post> getPageList(int size) {
        Pageable pageable = PageRequest.ofSize(size);
        Page<Post> postList = postRepository.findAll(pageable);

        return postList;
    }

//    @Override
//    public Page<Post> PostSearch(PostSearch postSearch,Pageable pageable) {
////        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
////        Page<Post> postList = postRepositorySupport.findAllWithQuerydsl(postSearch,pageable);
//
////        return postList;
//    }

//    @Override
//    public List<Post> getSearchedPost(PostSearch postSearch) {
//        String type = postSearch.getType();
//        String content = postSearch.getContent();
//        Optional<List<Post>> searchPost = postRepository.findSearchPost(postSearch);
//        return searchPost.get();
//    }

    public PageImpl<Post> getPagedSearchedPosts(PostSearch postSearch, Pageable pageable){
        return postRepositorySupport.findCustomSearchResultsWithPagination(postSearch,pageable);
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        List<Post> posts = new ArrayList<>();

        Optional<List<Post>> allPosts = postRepository.findAllByUserId(userId);

        if (!allPosts.isEmpty()) {
            posts = allPosts.get();
        }

        return posts;
    }
}

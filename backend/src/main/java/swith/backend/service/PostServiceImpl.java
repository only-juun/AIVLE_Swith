package swith.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swith.backend.cond.PostSearchCondition;
import swith.backend.config.SecurityUtil;
import swith.backend.domain.Post;
import swith.backend.dto.PostInfoDto;
import swith.backend.dto.PostPagingDto;
import swith.backend.dto.PostSaveDto;
import swith.backend.dto.PostUpdateDto;
import swith.backend.exception.*;
import swith.backend.repository.PostRepository;
import swith.backend.repository.UserRepository;

import static swith.backend.exception.PostExceptionType.POST_NOT_POUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;

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
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostException(POST_NOT_POUND));
        checkAuthority(post,PostExceptionType.NOT_AUTHORITY_UPDATE_POST );


        postUpdateDto.getTitle().ifPresent(post::updateTitle);
        postUpdateDto.getContent().ifPresent(post::updateContent);

    }


    /**
     * 게시글 삭제
     */
    @Override
    public void delete(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostException(POST_NOT_POUND));

        checkAuthority(post, PostExceptionType.NOT_AUTHORITY_DELETE_POST);

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
    public PostInfoDto getPostInfo(Long id) {


        /**
         * Post + MEMBER 조회 -> 쿼리 1번 발생
         *
         * 댓글&대댓글 리스트 조회 -> 쿼리 1번 발생(POST ID로 찾는 것이므로, IN쿼리가 아닌 일반 where문 발생)
         * (댓글과 대댓글 모두 Comment 클래스이므로, JPA는 구분할 방법이 없어서, 당연히 CommentList에 모두 나오는것이 맞다,
         * 가지고 온 것을 가지고 우리가 구분지어주어야 한다.)
         *
         * 댓글 작성자 정보 조회 -> 배치사이즈를 이용했기때문에 쿼리 1번 발생
         *
         *
         */
        return new PostInfoDto(postRepository.findWithUserById(id)
                .orElseThrow(() -> new PostException(POST_NOT_POUND)));

    }


//    /**
//     * 게시글 검색
//     */
//    @Override
//    public PostPagingDto getPostList(Pageable pageable, PostSearchCondition postSearchCondition) {
//
//        return new PostPagingDto(postRepository.search(postSearchCondition, pageable));
//    }
}

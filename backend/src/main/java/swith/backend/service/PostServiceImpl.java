package swith.backend.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.MemberRemoval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swith.backend.cond.PostSearchCondition;
import swith.backend.config.SecurityUtil;
import swith.backend.domain.Member;
import swith.backend.domain.Post;
import swith.backend.dto.PostInfoDto;
import swith.backend.dto.PostPagingDto;
import swith.backend.dto.PostSaveDto;
import swith.backend.dto.PostUpdateDto;
import swith.backend.exception.*;
import swith.backend.repository.MemberRepository;
import swith.backend.repository.PostRepository;
import java.util.List;

import static swith.backend.exception.PostExceptionType.POST_NOT_POUND;

@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
//    private final FileService fileService;


    /**
     * 게시글 저장
     */
    @Override
    public void save(PostSaveDto postSaveDto) throws FileException {
        Post post = postSaveDto.toEntity();

        post.confirmWriter(memberRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER)));

//        postSaveDto.getUploadFile().ifPresent(
//                file ->  post.updateFilePath(fileService.save(file))
//        );
        postRepository.save(post);
    }


    /**
     * 게시글 수정
     */
    @Override
    public void update(Long id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostException(POST_NOT_POUND));
        checkAuthority(post,PostExceptionType.NOT_AUTHORITY_UPDATE_POST );


        postUpdateDto.getTitle().ifPresent(post::updateTitle);
        postUpdateDto.getContent().ifPresent(post::updateContent);


//        if(post.getFilePath() !=null){
//            fileService.delete(post.getFilePath());//기존에 올린 파일 지우기
//        }
//
//        postUpdateDto.uploadFile().ifPresentOrElse(
//                multipartFile ->  post.updateFilePath(fileService.save(multipartFile)),
//                () ->  post.updateFilePath(null)
//        );

    }


    /**
     * 게시글 삭제
     */
    @Override
    public void delete(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostException(POST_NOT_POUND));

        checkAuthority(post, PostExceptionType.NOT_AUTHORITY_DELETE_POST);


//        if(post.getFilePath() !=null){
//            fileService.delete(post.getFilePath());//기존에 올린 파일 지우기
//        }

        postRepository.delete(post);
    }


    private void checkAuthority(Post post, PostExceptionType postExceptionType) {
        if(!post.getMember().getUsername().equals(SecurityUtil.getLoginUsername()))
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
        return new PostInfoDto(postRepository.findWithWriterById(id)
                .orElseThrow(() -> new PostException(POST_NOT_POUND)));

    }


    /**
     * 게시글 검색
     */
    @Override
    public PostPagingDto getPostList(Pageable pageable, PostSearchCondition postSearchCondition) {

        return new PostPagingDto(postRepository.search(postSearchCondition, pageable));
    }
}

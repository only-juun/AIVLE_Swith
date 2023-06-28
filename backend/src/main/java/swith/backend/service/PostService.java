package swith.backend.service;

import org.springframework.data.domain.Page;
import swith.backend.domain.Post;
import swith.backend.dto.PostInfoDto;
import swith.backend.dto.PostUpdateDto;


public interface PostService {
    /**
     * 게시글 저장
     */
    void register(Post post);

    /**
     * 게시글 수정
     */
    void update(Long id, PostUpdateDto postUpdateDto);

    /**
     * 게시글 삭제
     */
    void delete(Long id);

    /**
     * 게시글 1개 조회
     */
    PostInfoDto getPostInfo(Long id);



    /**
     * 검색 조건에 따른 게시글 리스트 조회 + 페이징
     */
//    PostPagingDto getPostList(Pageable pageable, PostSearchCondition postSearchCondition);

    /**
     * 게시글 페이징 조회
     */
    public Page<Post> getPostList(int page,int size);

    public Page<Post> getPageList(int size);
}

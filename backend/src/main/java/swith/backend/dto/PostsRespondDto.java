package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Post;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostsRespondDto {

    private Long postId;
    private String title;
    private String writer;
    private LocalDateTime createTime;
    private int searchCount;
    private int likeCount;
    private int totalPage;

    public PostsRespondDto(Post post,int totalPage) {
        postId = post.getId();
        title = post.getTitle();
        writer = post.getUser().getNickname();
        createTime = post.getCreatedDate();
        searchCount = post.getSearchCount();
        likeCount = post.getLikeCount();
        this.totalPage = totalPage;
    }

}

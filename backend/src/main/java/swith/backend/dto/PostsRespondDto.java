package swith.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Post;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostsRespondDto {

    private Long postId;
    private String title;
    private LocalDateTime createTime;
    private int searchCount;
    private int likeCount;

    public PostsRespondDto(Post post) {
        postId = post.getId();
        title = post.getTitle();
        createTime = post.getCreatedDate();
        searchCount = post.getSearchCount();
        likeCount = post.getLikeCount();
    }

}

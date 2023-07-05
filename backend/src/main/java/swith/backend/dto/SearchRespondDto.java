package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Post;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SearchRespondDto {
    private Long postId;
    private String title;
    private String nickname;
    private LocalDateTime createTime;
    private int searchCount;
    private int likeCount;
    private long total;

    public SearchRespondDto(Post post) {
        postId = post.getId();
        title = post.getTitle();
        nickname = post.getUser().getEmail();
        createTime = post.getCreatedDate();
        searchCount = post.getSearchCount();
        likeCount = post.getLikeCount();
    }
}

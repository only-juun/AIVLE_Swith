package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Post;

@Data
@NoArgsConstructor
public class PostSimpleDto {

    private Long postId;
    private String title;//제목
    private String writer;//작성자(닉네임)
    private String createdDate; //작성일

    public PostSimpleDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.writer = post.getUser().getName();
        this.createdDate = post.getCreatedDate().toString();
    }
}

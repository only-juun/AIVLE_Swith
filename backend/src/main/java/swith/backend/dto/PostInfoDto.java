package swith.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Comment;
import swith.backend.domain.Post;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class PostInfoDto{


    private Long postId; //POST의 ID
    private String title;//제목
    private String content;//내용
    private String filePath;//업로드 파일 경로

    private UserInfoDto writerDto;//작성자에 대한 정보

    private List<CommentInfoDto> commentInfoDtoList;//댓글 정보들




    public PostInfoDto(Post post) {

        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();

        this.writerDto = new UserInfoDto(post.getUser());

    }
}
package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Attachment;
import swith.backend.domain.Comment;
import swith.backend.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostInfoDto{


    private Long postId; //POST의 ID
    private String title;//제목
    private String content;//내용
    private LocalDateTime createdDate;//작성 일시
    private int searchCount;
    private int likeCount;
    private UserInfoDto writerDto;//작성자에 대한 정보
    private List<CommentInfoDto> commentInfoDtoList;//댓글 정보들
    private List<AttachmentInfoDto> attachmentInfoDto;//첨부파일 정보들


    public PostInfoDto(Post post) {

        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.searchCount = post.getSearchCount();
        this.likeCount = post.getLikeCount();

        this.writerDto = new UserInfoDto(post.getUser());

        List<Comment> commentList = post.getComments();
        this.commentInfoDtoList = commentList.stream().map(CommentInfoDto::new).collect(Collectors.toList());
        List<Attachment> attachmentList = post.getAttachments();
        this.attachmentInfoDto = attachmentList.stream().map(AttachmentInfoDto::new).collect(Collectors.toList());

    }
}
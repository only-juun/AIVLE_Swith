package swith.backend.dto;

import lombok.Data;
import swith.backend.domain.Comment;

import java.time.LocalDateTime;

@Data
public class CommentInfoDto{

    private final static String DEFAULT_DELETE_MESSAGE = "삭제된 댓글입니다";

    private Long postId;//댓글이 달린 POST의 ID
    private Long commentId;//해당 댓글의 ID
    private LocalDateTime createdDate;//댓글 작성일시
    private String content;//내용
    private boolean isRemoved;//삭제되었는지?

    private UserInfoDto writerDto;//댓글 작성자에 대한 정보

    public CommentInfoDto(Comment comment) {

        this.postId = comment.getPost().getId();
        this.commentId = comment.getId();
        this.createdDate = comment.getCreatedDate();
        this.content = comment.getContent();
        if(comment.isRemoved()){
            this.content = DEFAULT_DELETE_MESSAGE;
        }
        this.isRemoved = comment.isRemoved();

        this.writerDto = new UserInfoDto(comment.getUser());


    }
}


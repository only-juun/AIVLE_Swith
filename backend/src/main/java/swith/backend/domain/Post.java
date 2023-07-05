package swith.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 40, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private int searchCount;
    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //==게시글을 삭제하면 첨부파일 전부 삭제==//
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    //==게시글을 삭제하면 좋아요 전부 삭제==//
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Thumb> thumbs = new ArrayList<>();

    //==게시글을 삭제하면 댓글 전부 삭제==//
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //==연관관계 편의 메서드==//
    public void confirmWriter(User writer) {
        this.user = writer;
        writer.addPost(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    //==게시글 내용 수정 관련==//
    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateSearchCount(int searchCount) {
        this.searchCount = searchCount + 1;}
}

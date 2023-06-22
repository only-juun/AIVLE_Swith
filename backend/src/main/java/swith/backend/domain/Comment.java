package swith.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    private boolean isRemoved = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    //== 연관관계 편의 메서드 ==//
    public void confirmWriter(Member writer) {
        this.member = writer;
        writer.addComment(this);
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addComment(this);
    }


    //== 수정 ==//
    public void updateContent(String content) {
        this.content = content;
    }
    //== 삭제 ==//
    public void remove() {
        this.isRemoved = true;
    }


    @Builder
    public Comment( Member writer, Post post, Comment parent, String content) {
        this.member = writer;
        this.post = post;
        this.content = content;
        this.isRemoved = false;
    }

}

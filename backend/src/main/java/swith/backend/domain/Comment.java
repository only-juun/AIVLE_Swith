package swith.backend.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    private boolean isRemoved = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public void confirmWriter(User writer) {
        this.user = writer;
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
    public Comment(User writer, Post post, String content) {
        this.user = writer;
        this.post = post;
        this.content = content;
        this.isRemoved = false;
    }

}

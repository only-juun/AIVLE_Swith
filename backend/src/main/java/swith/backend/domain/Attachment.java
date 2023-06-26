package swith.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Attachment {
    @Id
    @GeneratedValue
    @Column(name = "attachment_id")
    private Long id;

    private String fileName;
    private String filePath;
    private String fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}

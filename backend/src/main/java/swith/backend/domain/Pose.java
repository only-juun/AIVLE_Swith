package swith.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pose extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int label;

    private Boolean wifi;

    private Boolean camera;

    private String label_s;

    /**
     * 연관관계 메소드
     */
    public void setUser(User user) {
        this.user = user;
    }

    public void label_to_string(int label) {
        if (label == 0) {
            this.label_s = "0번 라벨";
        } else if (label == 1) {
            this.label_s = "1번 라벨";
        } else {
            this.label_s = "2번 라벨";
        }
    }
}

package swith.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
            this.label_s = "아이의 낙상이 감지되었습니다.";
        } else if (label == 1) {
            this.label_s = "아이의 뒤집힘이 감지되었습니다.";
        } else if (label == 2){
            this.label_s = "사각 지대에서 움직임이 감지되었습니다.";
        } else if (label == 3) {
            this.label_s = "낙상이 감지되었습니다.";
        } else {
            this.label_s = "사각 지대에서 움직임이 감지되었습니다.";
        }
    }
}

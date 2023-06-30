package swith.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.User;

@Data
@NoArgsConstructor
public class UserInfoDto {

    private String name;
    private String nickname;
    private String email;

    @Builder
    public UserInfoDto(User member) {
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getUsername();
    }
}

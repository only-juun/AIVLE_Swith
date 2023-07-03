package swith.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.User;

@Data
@NoArgsConstructor
public class UserEditDto {
    private String nickname;
    private String password;

    @Builder
    public UserEditDto(User user) {
        this.nickname = user.getNickname();
        this.password = user.getPassword();
    }
}

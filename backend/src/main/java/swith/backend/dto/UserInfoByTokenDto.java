package swith.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swith.backend.domain.User;

@Getter
@NoArgsConstructor
@Builder
public class UserInfoByTokenDto {

    private String name;
    private String nickname;
    private String email;
    private String serialNumber;

    public UserInfoByTokenDto(String name, String nickname, String email, String serialNumber) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.serialNumber = serialNumber;
    }
}

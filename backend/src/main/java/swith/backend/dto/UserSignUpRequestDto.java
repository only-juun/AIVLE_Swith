package swith.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestDto {
    private String name;
    private String nickname;
    private String password;
    private String serialNumber;
    private String phoneNumber;
    private String email;
}

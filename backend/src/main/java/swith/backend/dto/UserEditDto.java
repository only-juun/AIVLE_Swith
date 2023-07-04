package swith.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@Getter
public class UserEditDto {
    private Optional<String> nickname;
    private Optional<String> password;
    private String serialNumber;
}

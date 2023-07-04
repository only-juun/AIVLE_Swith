package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckPasswordDto {
    String email;
    String password;
}

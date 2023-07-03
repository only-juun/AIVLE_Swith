package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmailRequestDto {
    private String email;
    private String serialNumber;

}

package swith.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MailDto {
    private String address;
    private String title;
    private String message;
}

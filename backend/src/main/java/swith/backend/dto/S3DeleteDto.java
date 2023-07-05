package swith.backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter @ToString
public class S3DeleteDto {
    private String uploadFilePath;
    private String uploadFileName;
}

package swith.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import swith.backend.domain.Attachment;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentInfoDto {

    private Long attachmentId;
    private String originalFileName;
    private String uploadFileName;
    private String uploadFilePath;
    private String uploadFileUrl;

    public AttachmentInfoDto(Attachment attachment) {

        this.attachmentId = attachment.getId();
        this.originalFileName = attachment.getOriginalFileName();
        this.uploadFileName = attachment.getUploadFileName();
        this.uploadFilePath = attachment.getUploadFilePath();
        this.uploadFileUrl = attachment.getUploadFileUrl();
    }
}

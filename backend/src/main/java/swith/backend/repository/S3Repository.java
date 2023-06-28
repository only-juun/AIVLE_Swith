package swith.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swith.backend.domain.Attachment;

public interface S3Repository extends JpaRepository<Attachment, Long> {
    Attachment findByUploadFileName(String uploadFileName);

    void delete(Attachment attachment);

}

package swith.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swith.backend.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

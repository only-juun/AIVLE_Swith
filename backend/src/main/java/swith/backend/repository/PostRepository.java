package swith.backend.repository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swith.backend.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}

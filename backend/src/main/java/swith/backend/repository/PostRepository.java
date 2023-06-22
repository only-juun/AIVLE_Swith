package swith.backend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import swith.backend.domain.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository{
    @EntityGraph(attributePaths = {"writer"})
    Optional<Post> findWithWriterById(Long id);

    @Override
    void delete(Post entity);
}

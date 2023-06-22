package swith.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import swith.backend.cond.PostSearchCondition;
import swith.backend.domain.Post;

public interface CustomPostRepository {

    Page<Post> search(PostSearchCondition postSearchCondition, Pageable pageable);
}

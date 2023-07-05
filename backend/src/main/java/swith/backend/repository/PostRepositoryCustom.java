package swith.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import swith.backend.domain.Post;
import swith.backend.domain.PostSearch;
import swith.backend.service.PostService;

import java.util.List;
import java.util.Optional;

public interface PostRepositoryCustom {
    public Optional<List<Post>> findSearchPost(PostSearch postSearch);
}

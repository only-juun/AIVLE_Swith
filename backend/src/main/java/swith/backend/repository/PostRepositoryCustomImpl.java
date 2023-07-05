package swith.backend.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import swith.backend.domain.*;
import swith.backend.repository.PostRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PostRepositoryCustomImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<List<Post>> findSearchPost(PostSearch postSearch) {


        return Optional.empty();
    }
}

package swith.backend.repository;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import swith.backend.domain.Post;
import swith.backend.domain.PostSearch;
import swith.backend.domain.QPost;
import swith.backend.domain.QUser;

import javax.persistence.EntityManager;

import java.util.List;

import static swith.backend.domain.QPost.post;
import static swith.backend.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class PostRepositorySupport {

    private final EntityManager em;


    public Page<Post> findAllWithQuerydsl(PostSearch postSearch, Pageable pageable){
        QPost post = QPost.post;
        QUser user = QUser.user;

        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Post> results = query
                .select(post)
                .from(post)
                .join(post.user, user)
                .where(titleLike(postSearch.getTitle()),
                        nameLike(postSearch.getWriter()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = results.size();

        return new PageImpl<>(results,pageable,total);
    }

    private BooleanExpression nameLike(String nameCond) {
        if (!StringUtils.hasText(nameCond)) {
            return null;
        }
        return user.nickname.like(nameCond);
    }

    private BooleanExpression titleLike(String titleCond) {
        if (!StringUtils.hasText(titleCond)) {
            return null;
        }
        return post.title.like(titleCond);
    }

}

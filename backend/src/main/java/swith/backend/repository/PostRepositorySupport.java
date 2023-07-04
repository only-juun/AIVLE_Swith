package swith.backend.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
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
public class PostRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public PageImpl<Post> findCustomSearchResultsWithPagination(PostSearch postSearch, Pageable pageable){

//        BooleanBuilder searchBuilder = new BooleanBuilder();

        QPost post = QPost.post;
        QUser user = QUser.user;

//        searchBuilder

        JPAQuery<Post> postJPAQuery = jpaQueryFactory.selectFrom(QPost.post)
                .join(post.user, user)
                .where(contentLike(postSearch.getType(), postSearch.getContent()))
                .orderBy(QPost.post.createdDate.desc());
        long totalCount = postJPAQuery.stream().count();
        List<Post> results = getQuerydsl().applyPagination(pageable, postJPAQuery).fetch();

        return new PageImpl<>(results,pageable,totalCount);


//        List<Post> results = jpaQueryFactory
//                .select(post)
//                .from(post)
//                .join(post.user, user)
//                .where(contentLike(postSearch.getType(),postSearch.getContent()))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        long total = results.size();

//        return new PageImpl<>(results,pageable,total);
    }

//    private BooleanExpression typeEq( nameCond) {
//        if (!StringUtils.hasText(nameCond)) {
//            return null;
//        }
//        return user.nickname.like(nameCond);
//    }

    private BooleanExpression contentLike(String type, String content) {
        if (!StringUtils.hasText(content)) {
            return null;
        }
        if (type.equals("작성자")) {
            return user.nickname.contains(content);
        } else if (type.equals("제목")) {
            return post.title.contains(content);
        }
        return null;
    }

}

package org.signature.resilience4j.techblog.service.query;

import lombok.RequiredArgsConstructor;
import org.signature.resilience4j.techblog.domain.dto.TechBlogDto;
import org.signature.resilience4j.techblog.domain.response.TechBlogQueryResponse;
import org.signature.resilience4j.techblog.domain.response.TechBlogsQueryResponse;
import org.signature.resilience4j.techblog.domain.type.TechBlogType;
import org.signature.resilience4j.techblog.service.external.RssFetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechBlogUseCaseImpl implements TechBlogUseCase {

    private final RssFetcher rssFetcher;

    @Override
    public TechBlogsQueryResponse getLatestPosts(TechBlogType blogType) {
        List<TechBlogDto> posts = rssFetcher.fetchLatestPosts(blogType.getRssUrl());
        List<TechBlogQueryResponse> result = posts.stream()
                .map(post -> new TechBlogQueryResponse(
                        post.title(),
                        post.link(),
                        post.publishedAt()
                )).toList();
        return new TechBlogsQueryResponse(blogType.getBlogName(), result);
    }

}

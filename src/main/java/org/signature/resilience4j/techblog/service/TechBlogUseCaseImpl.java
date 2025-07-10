package org.signature.resilience4j.techblog.service;

import lombok.RequiredArgsConstructor;
import org.signature.resilience4j.techblog.domain.response.TechBlogQueryResponse;
import org.signature.resilience4j.techblog.domain.response.TechBlogsQueryResponse;
import org.signature.resilience4j.techblog.domain.type.TechBlogType;
import org.signature.resilience4j.techblog.service.external.RssFetcher;
import org.signature.resilience4j.techblog.service.query.TechBlogUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechBlogUseCaseImpl implements TechBlogUseCase {

    private final RssFetcher rssFetcher;

    @Override
    public TechBlogsQueryResponse getLatestPosts(TechBlogType blogType) {
        List<TechBlogQueryResponse> posts = rssFetcher.fetchLatestPosts(blogType.getRssUrl());
        return new TechBlogsQueryResponse(blogType.getBlogName(), posts);
    }

}

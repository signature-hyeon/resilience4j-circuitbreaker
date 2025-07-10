package org.signature.resilience4j.techblog.service.query;

import org.signature.resilience4j.techblog.domain.response.TechBlogsQueryResponse;
import org.signature.resilience4j.techblog.domain.type.TechBlogType;

public interface TechBlogUseCase {

    TechBlogsQueryResponse getLatestPosts(TechBlogType blogName);

}

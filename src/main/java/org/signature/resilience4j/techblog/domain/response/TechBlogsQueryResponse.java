package org.signature.resilience4j.techblog.domain.response;

import java.util.List;

public record TechBlogsQueryResponse(
        String blogName,
        List<TechBlogQueryResponse> posts
) {

}

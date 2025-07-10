package org.signature.resilience4j.techblog.domain.response;

public record TechBlogQueryResponse(
        String title,
        String link,
        long publishedAt
) {

}

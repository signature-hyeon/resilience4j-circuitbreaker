package org.signature.resilience4j.techblog.domain.dto;

public record TechBlogDto(
        String title,
        String link,
        long publishedAt
) {

}

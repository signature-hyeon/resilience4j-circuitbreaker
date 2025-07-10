package org.signature.resilience4j.techblog.controller;

import lombok.RequiredArgsConstructor;
import org.signature.resilience4j.techblog.domain.response.TechBlogsQueryResponse;
import org.signature.resilience4j.techblog.domain.type.TechBlogType;
import org.signature.resilience4j.techblog.service.query.TechBlogUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/techblogs")
public class TechBlogController {

    private final TechBlogUseCase techBlogUseCase;

    @GetMapping("/{blogName}/latest")
    public TechBlogsQueryResponse getLatest(@PathVariable String blogName) {
        return techBlogUseCase.getLatestPosts(TechBlogType.from(blogName));
    }

}

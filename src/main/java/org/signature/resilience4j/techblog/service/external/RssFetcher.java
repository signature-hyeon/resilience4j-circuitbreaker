package org.signature.resilience4j.techblog.service.external;

import org.signature.resilience4j.techblog.domain.dto.TechBlogDto;

import java.util.List;

public interface RssFetcher {

    List<TechBlogDto> fetchLatestPosts(String rssUrl);

}

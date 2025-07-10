package org.signature.resilience4j.techblog.service.external;

import org.signature.resilience4j.techblog.domain.response.TechBlogQueryResponse;

import java.util.List;

public interface RssFetcher {

    List<TechBlogQueryResponse> fetchLatestPosts(String rssUrl);

}

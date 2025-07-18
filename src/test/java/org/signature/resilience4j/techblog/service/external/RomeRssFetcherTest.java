package org.signature.resilience4j.techblog.service.external;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.signature.resilience4j.techblog.domain.dto.TechBlogDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RomeRssFetcherTest {

    private final RomeRssFetcher fetcher = new RomeRssFetcher();

    @Test
    @DisplayName("정상적인 RSS 피드를 파싱한다")
    void fetch_validFeed_returnsPosts() {
        // given
        String rssUrl = "https://tech.kakao.com/feed/";

        // when
        List<TechBlogDto> result = fetcher.fetchLatestPosts(rssUrl);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).title()).isNotBlank();
    }

    @Test
    @DisplayName("fallback 메서드는 빈 리스트를 반환한다")
    void fallbackFetch_returnsEmptyList() {
        // when
        List<TechBlogDto> fallbackResult = fetcher.fallbackFetch("https://broken-url.com", new RuntimeException());

        // then
        assertThat(fallbackResult).isEmpty();
    }
}

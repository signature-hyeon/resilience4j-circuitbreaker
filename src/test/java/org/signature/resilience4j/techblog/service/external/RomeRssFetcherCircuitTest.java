package org.signature.resilience4j.techblog.service.external;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.signature.resilience4j.techblog.domain.response.TechBlogQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RomeRssFetcherCircuitTest {

    @Autowired
    private RssFetcher rssFetcher;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Test
    @DisplayName("연속 실패 시 서킷 브레이커가 Open 상태가 된다")
    void circuitBreaker_opens_after_failures() {
        // given
        String invalidUrl = "https://not-exist-url.feed/";
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("rssFetcher");
        System.out.println("rssFetcher class = " + rssFetcher.getClass());

        // when: 실패 누적 시도
        for (int i = 0; i < 10; i++) {
            try {
                rssFetcher.fetchLatestPosts(invalidUrl);
            } catch (Exception ignored) {}
        }

        // then: Open 상태 확인
        Assertions.assertEquals(CircuitBreaker.State.OPEN, cb.getState());
    }

    @Test
    @DisplayName("Open 상태일 때 즉시 fallback이 호출된다")
    void circuitBreaker_triggers_fallback_when_open() {
        // given
        String invalidUrl = "https://not-exist-url.feed/";
        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("rssFetcher");

        // 강제로 Open 상태 만들기
        for (int i = 0; i < 10; i++) {
            try {
                rssFetcher.fetchLatestPosts(invalidUrl);
            } catch (Exception ignored) {}
        }

        Assertions.assertEquals(CircuitBreaker.State.OPEN, cb.getState());

        // when: Open 상태에서 다시 호출
        List<TechBlogQueryResponse> result = rssFetcher.fetchLatestPosts(invalidUrl);

        // then: fallback이 작동해 빈 리스트 반환
        assertThat(result).isEmpty();
    }
}

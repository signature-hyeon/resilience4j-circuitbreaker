package org.signature.resilience4j.techblog.service.external;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.signature.resilience4j.techblog.domain.dto.TechBlogDto;
import org.signature.resilience4j.techblog.domain.response.TechBlogQueryResponse;
import org.springframework.stereotype.Component;

import java.net.URL;

import java.time.Instant;
import java.util.List;

@Slf4j
@Component
public class RomeRssFetcher implements RssFetcher {

    @CircuitBreaker(name = "rssFetcher", fallbackMethod = "fallbackFetch")
    @Override
    public List<TechBlogDto> fetchLatestPosts(String rssUrl) {
        try (XmlReader reader = new XmlReader(new URL(rssUrl))) {
            SyndFeed feed = new SyndFeedInput().build(reader);
            return feed.getEntries().stream()
                    .map(this::toDto)
                    .toList();
        } catch (Exception e) {
            log.error("RSS 파싱 실패: {}", rssUrl, e);
            throw new RuntimeException("RSS 파싱 실패", e);
        }
    }

    private TechBlogDto toDto(SyndEntry entry) {
        return new TechBlogDto(
                entry.getTitle(),
                entry.getLink(),
                Instant.now().getEpochSecond()
        );
    }

    public List<TechBlogDto> fallbackFetch(String rssUrl, Throwable t) {
        log.warn("Fallback triggered for RSS URL: {}", rssUrl, t);
        return List.of();
    }

}

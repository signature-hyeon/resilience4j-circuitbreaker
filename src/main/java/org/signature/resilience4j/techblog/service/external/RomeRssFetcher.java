package org.signature.resilience4j.techblog.service.external;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import lombok.extern.slf4j.Slf4j;
import org.signature.resilience4j.techblog.domain.response.TechBlogQueryResponse;
import org.springframework.stereotype.Component;

import java.net.URL;
import com.rometools.rome.io.XmlReader;

import java.time.Instant;
import java.util.List;

@Slf4j
@Component
public class RomeRssFetcher implements RssFetcher {

    @Override
    public List<TechBlogQueryResponse> fetchLatestPosts(String rssUrl) {
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

    private TechBlogQueryResponse toDto(SyndEntry entry) {
        return new TechBlogQueryResponse(
                entry.getTitle(),
                entry.getLink(),
                Instant.now().getEpochSecond()
        );
    }
}

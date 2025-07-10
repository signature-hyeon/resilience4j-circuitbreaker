package org.signature.resilience4j.techblog.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TechBlogType {

    KAKAO("kakao", "https://tech.kakao.com/feed/"),
    WOOWAHAN("woowahan", "https://techblog.woowahan.com/feed/"),
    NAVER("naver", "https://d2.naver.com/d2.atom"),
    DAANGN("daangn", "https://medium.com/feed/daangn");

    private final String blogName;
    private final String rssUrl;

    public static TechBlogType from(String blogName) {
        return Arrays.stream(values())
                .filter(type -> type.blogName.equalsIgnoreCase(blogName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(blogName));
    }
}

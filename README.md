# TechFeed Aggregator

기술 블로그(RSS)를 수집하여 최신 글을 조회하거나 키워드로 검색할 수 있는 REST API 서비스입니다.  
외부 RSS 호출 실패에 대비하여 resilience4j 서킷 브레이커를 적용하였습니다.

---

## 주요 기능

- 기업별 기술 블로그 최신 글 조회 (`/api/techblogs/{blogName}/latest`)
- 키워드 기반 통합 검색 (`/api/techblogs/search`)
- resilience4j CircuitBreaker 적용 (블로그별 상태 보호 및 fallback)

---

## 사용 기술

- Java 17
- Spring Boot 3
- WebClient
- resilience4j
- Rome (RSS 파서)

## 예시 요청
```
GET /api/techblogs/kakao/latest
GET /api/techblogs/search?keyword=spring
```

## ⚡ Circuit Breaker 정책

- 블로그별 RSS 호출에 resilience4j CircuitBreaker 적용
- 실패율 50% 이상 시 서킷이 열리고, 일정 시간 후 재시도
- 외부 호출 지연(2초 초과) 또는 예외 발생 시 fallback 응답 반환
- fallback: 기본 메시지 또는 빈 결과 제공


## 참고 RSS 목록
- Kakao: https://tech.kakao.com/feed/
- 우아한형제들: https://techblog.woowahan.com/feed/
- 네이버 D2: https://d2.naver.com/d2.atom
- 당근마켓: https://medium.com/feed/daangn

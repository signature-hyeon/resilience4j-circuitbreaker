# TechBlog Feed

- 기술 블로그(RSS) 최신 글을 조회하는 REST API 예제입니다.
- resilience4j 서킷 브레이커 적용 및 학습을 위한 실습용 프로젝트입니다.

---

## 주요 기능

- 기업별 기술 블로그 최신 글 조회 (`/api/techblogs/{blogName}/latest`)
- 키워드 기반 통합 검색 (`/api/techblogs/search`)
- resilience4j CircuitBreaker 적용 (블로그별 상태 보호 및 fallback)

---

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

## 🧪 Test

- 정상 RSS 요청 시 게시글을 파싱해 반환하는지 테스트합니다.
- 잘못된 URL 요청 시 fallback이 작동해 빈 리스트를 반환하는지 확인합니다.
- 서킷브레이커 동작을 확인하기 위해 실패를 반복해 상태 전이를 검증하는 테스트도 포함되어 있습니다.  
  (※ 실제 서비스 테스트에는 포함하지 않으며, 학습 목적입니다)

## 참고 RSS 목록
- Kakao: https://tech.kakao.com/feed/
- 우아한형제들: https://techblog.woowahan.com/feed/
- 네이버 D2: https://d2.naver.com/d2.atom
- 당근마켓: https://medium.com/feed/daangn

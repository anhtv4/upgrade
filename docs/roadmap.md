# Lo trinh hoc tap - Booking Platform

Muc tieu: nang trinh Java/Spring Boot tu 2-5 nam kinh nghiem len muc san sang phong van senior / nhay vao du an moi khong bi ngop. Diem yeu duoc xac dinh: kien truc & design patterns.

Toc do: < 5 gio/tuan. Tong thoi luong uoc tinh: ~5-6 thang cho 8 phase chinh.

## Phase 1 - Nen tang & kien truc (~2 tuan)
Setup Spring Boot + Hexagonal Architecture (domain / application / infrastructure). Dinh nghia bounded context: User, Service Catalog, Booking, Notification, Payment.
Trang thai: CHUA BAT DAU (phase hien tai). Da co skeleton tao san, nguoi lam chua tu lam.
Cach lam: cat skeleton sang nhanh git tham khao, tu viet lai slice "tao booking" tu dau, chi mo skeleton khi bi; xong thi so sanh va ghi lai khac biet. Bo sung tai lieu bounded context.
Skeleton co san: Hexagonal, vertical slice "tao booking" chay het domain -> application -> infrastructure. Pattern trong skeleton: Repository (`BookingRepository` port), Adapter (`BookingRepositoryAdapter`), Factory (`Booking.createNew/reconstitute`), Strategy (`ConflictPolicy`). Value object (`BookingId`, `CustomerId`, `TimeSlot`) da la record.
Luu y: moi chi hien thuc bounded context Booking; cac context con lai (User, Service Catalog, Notification, Payment) moi dung o muc dinh nghia ten.

## Phase 2 - Domain Model & Design Patterns (~5-6 tuan) - TRONG TAM
Pham vi: chi mo rong Booking context. User, Service Catalog, Payment van la ID/string tho.
- Provider: them `ProviderId` vao `Booking`, sua conflict check theo provider thay vi customer (skeleton hien tai check theo `customerId` vi chua co khai niem provider).
- State pattern: nang `BookingStatus` tu enum len sealed interface + pattern matching, vong doi PENDING -> AWAITING_PAYMENT -> CONFIRMED -> COMPLETED (CANCELLED nhanh ra tu nhieu diem); moi trang thai tu quyet dinh trang thai ke tiep hop le, thay cho if/else nhu `Booking.confirm()` hien tai. Thanh toan chi mo phong (VD `Booking.markPaymentReceived()`).
- Observer/domain events: `BookingCreatedEvent`, `BookingConfirmedEvent` qua Spring `ApplicationEventPublisher` (noi bo, CHUA dung message broker - do la Phase 8).
- Bai tap refactor: viet 1 doan "bad design" (God class, if/else long nhau) roi refactor sang "good design", so sanh truoc/sau.
- Ket thuc phase: viet ADR `docs/adr/0002-...md` ve State pattern + Observer + trade-off.

## Phase 3 - Persistence chuan chinh (~3 tuan)
JPA nang cao: optimistic locking, JPA Auditing, Specification/Querydsl, Flyway migration.
Bo sung: doc execution plan / index tuning, demo isolation level (phantom read, lost update).
Chuyen tu H2 sang PostgreSQL that (driver + profile `postgres` da co san trong pom.xml/application.yml).

## Phase 4 - API Design & Validation (~2 tuan)
Versioning, loi theo RFC 7807, Bean Validation (da co san o `CreateBookingRequest`), OpenAPI/Swagger.

## Phase 5 - Bao mat (~3 tuan)
Spring Security + JWT, RBAC (customer/provider/admin), refresh token, method-level security.

## Phase 6 - Testing Culture (~4 tuan)
Unit test tang domain/use-case, integration test voi Testcontainers, Jacoco coverage.

## Phase 7 - Caching & Toi uu hieu nang (~2 tuan)
Redis cache-aside cho danh muc dich vu / khung gio trong, phat hien sua N+1 query, tinh chinh HikariCP.

## Phase 8 - Async, Observability & Dong goi (~4 tuan)
Spring Events -> RabbitMQ (Outbox pattern) cho notification. CompletableFuture cho xu ly song song.
Bo sung: idempotency key khi tao booking, rate limiting co ban (bucket4j).
Actuator + Micrometer, Docker/docker-compose, CI co ban voi GitHub Actions.

## Phase 9 (tuy chon, sau Phase 8) - Microservices
QUYET DINH DA CHOT: chua tach ngay. Chi lam khi Phase 1-8 da vung. Quyet dinh tach hay khong dua tren tieu chi cu the (module nao tai khac biet, can scale doc lap) - chinh qua trinh ra quyet dinh moi la bai hoc, khong phai code.

## Chua xep lich
- Tich hop cong thanh toan that (Payment context day du): chua co phase nao, can bo sung sau Phase 2.
- Multi-tenant: neu lam thi la bai tap rieng, khong gan vao lo trinh chinh.

## Thoi quen xuyen suot
Moi phase ket thuc bang 1 ADR ngan (xem docs/adr/) ghi quyet dinh + trade-off.

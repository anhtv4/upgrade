# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Boi canh du an

Du an hoc tap CA NHAN (khong phai du an cong ty) de luyen kien truc & design patterns. Nguoi lam co 2-5 nam kinh nghiem Java/Spring Boot, cong viec chinh la mot Hospital Information System multi-tenant tren Oracle, nhung tu nhan trinh do con yeu hon so nam kinh nghiem, dac biet ve kien truc. Toc do hoc < 5 gio/tuan. Muc tieu cuoi: san sang phong van senior / nhay vao du an moi ma khong bi ngop.

He qua voi cach lam viec:
- Uu tien giai thich "tai sao" khi de xuat pattern moi, khong chi ap code - muc tieu la hieu, khong chi la co san pham chay.
- Lam dung phase hien tai (xem "Lo trinh"). Khong dua viec cua phase sau vao som (VD: Redis/RabbitMQ khi dang o Phase 2) tru khi duoc yeu cau ro rang.

## Vai tro cua Claude: chi goi y va review

Nguoi lam tu viet code. Claude KHONG viet/sua code trong `src/` tru khi duoc yeu cau ro rang cho dung lan do.
- Khi nguoi lam bi: goi y theo bac - cau hoi dan dat truoc, roi hint cu the hon, chi dua snippet nho khi da bi that su. Khong dua loi giai tron ven ngay tu dau.
- Khi review: chi ra van de + ly do ky thuat, de nguoi lam tu sua. Neu co cach tot hon thi neu trade-off, khong tu ap vao.
- Duoc sua tai lieu (`CLAUDE.md`, `docs/`) khi duoc yeu cau.

## Nghiep vu (da chot)

- **Loai hinh:** salon/spa (cat toc, goi dau, massage...). 1 provider phuc vu 1 khach trong suot slot. Co y KHONG chon phong kham (qua gan nghe HIS, lai keo theo nhieu tai nguyen cung luc).
- **Provider:** nhan vien cua MOT cua hang duy nhat - KHONG multi-tenant. Multi-tenant la bai toan ha tang, nguoi lam da quen tu HIS; neu co thi la bai tap rieng, khong gan vao lo trinh chinh.
- **Thanh toan:** bat buoc truoc khi CONFIRMED. Vong doi: PENDING -> AWAITING_PAYMENT -> CONFIRMED -> COMPLETED, CANCELLED nhanh ra tu nhieu diem. Truoc khi co phase payment, "da thanh toan" chi mo phong bang method/domain event (VD `Booking.markPaymentReceived()`), khong tich hop cong thanh toan.
- **Trung lich** phai tinh theo provider (2 khach khac nhau khong duoc dat trung gio cung 1 provider). Skeleton hien tai check theo `customerId` vi chua co khai niem provider - se sua o Phase 2.
- User, Service Catalog, Payment, Notification van toi gian (chi la ID/string tho, chua co aggregate rieng) cho den khi phase tuong ung can.

Stack: Java 21, Spring Boot 4.1 (Spring Framework 7, Jakarta EE 11), Spring Data JPA, H2 (profile `dev`, mac dinh) / PostgreSQL (profile `postgres`, dung tu Phase 3), JUnit 5. Khong co Maven wrapper - dung `mvn` cai san tren may.

## Lenh thuong dung

```bash
mvn spring-boot:run                                        # profile dev (H2 in-memory, console tai /h2-console)
mvn spring-boot:run -Dspring-boot.run.profiles=postgres    # can Postgres tai localhost:5432/bookingdb (env DB_USERNAME / DB_PASSWORD)
mvn test                                                   # toan bo test
mvn test -Dtest=BookingDomainServiceTest                   # 1 class
mvn test -Dtest=BookingDomainServiceTest#nem_loi_khi_trung_khung_gio   # 1 method
mvn package                                                # build jar
```

`dev` dung `ddl-auto: update`; `postgres` dung `ddl-auto: validate` (schema phai co san - Flyway la viec cua Phase 3).

## Kien truc: Hexagonal (Ports & Adapters)

Base package `com.vietanh.booking`. Ly do chon va danh doi: `docs/adr/0001-hexagonal-architecture.md`.

```
domain/            logic nghiep vu thuan - KHONG import Spring hay jakarta.persistence.*
  model/             aggregate Booking (class, vi status thay doi duoc) + value object dang record (BookingId, CustomerId, TimeSlot)
  port/in/           use case interface (inbound port)
  port/out/          repository interface (outbound port)
  service/           domain service + Strategy (ConflictPolicy)
application/usecase/ implement inbound port, orchestrate domain + outbound port. Duoc dung @Service, khong dung JPA/HTTP
infrastructure/    adapter - noi duy nhat duoc dung Spring/JPA/HTTP
  persistence/       BookingJpaEntity + Spring Data repo, boc boi BookingRepositoryAdapter (map domain <-> JPA entity)
  web/               REST controller, DTO, GlobalExceptionHandler
  config/            DomainBeanConfig - noi DUY NHAT bien domain object thanh Spring bean
```

Luong xu ly: `BookingController` -> `CreateBookingUseCase` (impl `CreateBookingService`) -> lay booking hien co qua port `BookingRepository` -> `BookingDomainService` ap dung `ConflictPolicy` (hien la `NoOverlapConflictPolicy`) -> `Booking.createNew()` -> luu qua adapter.

## Quy uoc bat buoc

- `domain/` khong import bat ky thu gi tu Spring hay `jakarta.persistence.*`. Class domain khong gan `@Component`; domain service/strategy moi phai wire trong `DomainBeanConfig`.
- Aggregate dung static factory, khong dung constructor public: `Booking.createNew(...)` cho booking moi (luon bat dau `PENDING`), `Booking.reconstitute(...)` chi dung trong adapter khi doc tu persistence.
- Domain bao loi bang exception Java thuong; `GlobalExceptionHandler` map `IllegalStateException` -> 409, `IllegalArgumentException` -> 400 (chuan RFC 7807 de Phase 4).
- Test domain la JUnit thuan, khong Spring context (xem `BookingDomainServiceTest`) - giu domain logic test duoc theo cach nay. Ten test method: tieng Viet khong dau, snake_case.
- Comment va tai lieu viet tieng Viet khong dau.
- Moi phase ket thuc bang 1 ADR moi trong `docs/adr/` (`000N-ten.md`, cac muc: Boi canh / Quyet dinh / Ly do / Danh doi / Trang thai). Day la thoi quen dang luyen, khong phai thu tuc hinh thuc.

## Lo trinh (8 phase + 1 tuy chon, ~5-6 thang)

Ban day du: `docs/roadmap.md`.

**Phase 1 - Nen tang & kien truc (~2 tuan) - CHUA BAT DAU, LA PHASE HIEN TAI.** Cach lam da chon: cat skeleton tao san sang 1 nhanh git tham khao, nguoi lam tu viet lai slice "tao booking" tu dau, chi mo skeleton khi bi; xong thi so sanh va ghi lai khac biet. Them viec skeleton chua co: tai lieu bounded context (5 context so huu du lieu gi, giao tiep the nao). Muc "Kien truc" va "Lenh" o tren dang mo ta skeleton - cap nhat lai theo code nguoi lam tu viet. Skeleton gom: Hexagonal, vertical slice "tao booking" chay het domain -> application -> infrastructure. Pattern trong skeleton: Repository (`BookingRepository` port), Adapter (`BookingRepositoryAdapter`), Factory (`Booking.createNew/reconstitute`), Strategy (`ConflictPolicy`).

**Phase 2 - Domain Model & Design Patterns (~5-6 tuan) - TRONG TAM, sau Phase 1.** Chi mo rong Booking context. Viec cu the:
- Them `ProviderId` vao `Booking`; sua conflict check theo provider thay vi customer.
- Nang `BookingStatus` tu enum len sealed interface + pattern matching (State pattern day du), them `AWAITING_PAYMENT`: moi trang thai tu quyet dinh trang thai ke tiep hop le, thay cho if/else nhu `Booking.confirm()` hien tai.
- Observer/domain events: `BookingCreatedEvent`, `BookingConfirmedEvent` qua Spring `ApplicationEventPublisher` (noi bo, CHUA dung message broker - do la Phase 8).
- Bai tap refactor: viet 1 doan "bad design" (God class, if/else long nhau) roi refactor sang "good design", so sanh truoc/sau.
- Ket thuc: ADR `docs/adr/0002-...md` ve State pattern + Observer + trade-off.

**Cac phase sau** (chi tham khao, khong lam truoc):
- Phase 3 - Persistence: optimistic locking, JPA Auditing, Specification/Querydsl, Flyway, execution plan/index, demo isolation level; chuyen H2 -> PostgreSQL (driver + profile `postgres` da co san).
- Phase 4 - API: versioning, RFC 7807, Bean Validation (da co o `CreateBookingRequest`), OpenAPI.
- Phase 5 - Bao mat: Spring Security + JWT, RBAC (customer/provider/admin), refresh token, method-level security.
- Phase 6 - Testing: unit test domain/use-case, Testcontainers, Jacoco.
- Phase 7 - Caching: Redis cache-aside, sua N+1, tinh chinh HikariCP.
- Phase 8 - Async & dong goi: Spring Events -> RabbitMQ (Outbox), CompletableFuture, idempotency key, rate limiting (bucket4j), Actuator + Micrometer, Docker Compose, GitHub Actions.
- Phase 9 (tuy chon) - Microservices: QUYET DINH DA CHOT la chua tach. Chi xet khi Phase 1-8 da vung, dua tren tieu chi cu the (tai khac biet, can scale doc lap). KHONG tu y bat dau tru khi duoc yeu cau ro rang.

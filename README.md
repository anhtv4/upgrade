# Booking Platform - Du an hoc tap Spring Boot

## Muc tieu
Du an ca nhan de luyen kien truc, design patterns, va cac cong nghe Spring Boot hien dai, tach biet voi cong viec chinh (HIS). Lo trinh day du: `docs/roadmap.md`.

## Stack
- Java 21 (LTS)
- Spring Boot 4.1 (Spring Framework 7, Jakarta EE 11) - luu y: nhanh Spring Boot 3.x da EOL tu giua 2026, nen bat dau ngay voi nhanh 4.x
- Spring Data JPA + Hibernate
- H2 (dev, in-memory) / PostgreSQL (profile `postgres`, dung tu Phase 3)
- JUnit 5

## Cach chay
```bash
mvn spring-boot:run
```
Mac dinh chay voi profile `dev` (H2 in-memory, console tai `/h2-console`).

Thu API:
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{"customerId":"3fa85f64-5717-4562-b3fc-2c963f66afa6","serviceName":"Cat toc","start":"2026-10-01T09:00:00Z","end":"2026-10-01T10:00:00Z"}'
```

## Kien truc: Hexagonal (Ports & Adapters)

```
domain/            -> logic nghiep vu thuan, KHONG phu thuoc Spring/JPA
  model/             entities & value objects (Booking, TimeSlot, BookingId...)
  port/in/           use case interface (inbound port)
  port/out/          repository interface (outbound port)
  service/           domain service + Strategy pattern (ConflictPolicy)

application/        -> orchestrate domain, implement use case (inbound port)
  usecase/

infrastructure/     -> adapter, noi "ban" voi framework/DB/HTTP
  persistence/        JPA entity + repository adapter
  web/                 REST controller + DTO
  config/              wiring bean cho domain layer
```

Nguyen tac: `domain/` khong import bat ky thu gi tu Spring hay JPA. Dieu nay giup test domain logic (`BookingDomainServiceTest`) chay cuc nhanh, khong can khoi dong Spring context - loi ich nay se ro hon o Phase 6 (Testing).

## Pattern da ap dung trong Phase 1
- **Repository pattern**: `BookingRepository` (port) tach biet domain khoi chi tiet luu tru
- **Adapter pattern**: `BookingRepositoryAdapter` map giua domain model va JPA entity
- **Factory pattern**: `Booking.createNew()` / `Booking.reconstitute()` - static factory thay vi constructor public
- **Strategy pattern**: `ConflictPolicy` - hien co `NoOverlapConflictPolicy`, sau nay them chien luoc khac (VD: cho phep overbooking co gioi han) ma khong sua `BookingDomainService`

## Chua lam o Phase 1 (dung theo lo trinh)
- State pattern day du cho `BookingStatus` (hien la enum don gian) -> Phase 2
- Flyway migration, index, isolation level -> Phase 3
- Chuan hoa loi RFC 7807, OpenAPI -> Phase 4
- Spring Security -> Phase 5
- Testcontainers, coverage -> Phase 6

## ADR
Xem `docs/adr/0001-hexagonal-architecture.md` lam mau. Tu Phase 2 tro di, moi phase nen co 1 ADR moi ghi lai quyet dinh + trade-off.

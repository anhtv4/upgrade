# Ban do kien thuc

Do "nang trinh" bang kha nang, khong bang so dong code. Cham sau `/kiem-tra` cuoi moi phase (Claude hoi, nguoi lam tra loi; Claude de xuat muc, nguoi lam dong y hoac phan bien).

## Thang cham

| Muc | Ten | Nghia la |
|---|---|---|
| 0 | Chua | Chua hoc / chua tra loi duoc |
| 1 | Giai thich | Noi duoc no la gi, giai quyet van de gi, bang loi cua minh |
| 2 | Ap dung | Tu dung dung trong code cua du an, khong can mo skeleton/hint bac cao |
| 3 | Phan bien | Noi duoc khi nao KHONG nen dung, trade-off, phuong an thay the; bao ve quyet dinh truoc cau hoi van cua phong van |

Muc tieu senior: phan lon muc cot loi dat 3, con lai toi thieu 2.

"Bang chung" = link toi journal/ADR/commit/cau tra loi trong buoi kiem tra.

## Phase 1 - Nen tang & kien truc

| Kien thuc | Muc | Bang chung | Ngay cham |
|---|---|---|---|
| Hexagonal vs Layered: khac nhau o dau, khi nao Layered la du | 0 | | |
| Dependency rule / Dependency Inversion: vi sao domain khong phu thuoc framework | 0 | | |
| Port vs Adapter; inbound vs outbound | 0 | | |
| Bounded context, ranh gioi so huu du lieu, tham chieu bang ID | 0 | | |
| Aggregate, entity vs value object (record) | 0 | | |
| Static factory vs constructor public (`createNew` / `reconstitute`) | 0 | | |
| Repository pattern: port domain vs Spring Data repository | 0 | | |
| Tach domain model khoi JPA entity: loi ich va chi phi mapping | 0 | | |
| Strategy pattern (`ConflictPolicy`) | 0 | | |
| Test domain khong can Spring context | 0 | | |
| Viet ADR: neu ro boi canh, trade-off | 0 | | |

## Phase 2 - Domain Model & Design Patterns

| Kien thuc | Muc | Bang chung | Ngay cham |
|---|---|---|---|
| Bat bien (invariant) cua aggregate, dat logic o dau | 0 | | |
| State pattern bang sealed interface + pattern matching | 0 | | |
| Observer / domain event qua `ApplicationEventPublisher` | 0 | | |
| Transaction boundary va domain event (`@TransactionalEventListener`) | 0 | | |
| Nhan dien code smell (God class, if/else long nhau) va refactor | 0 | | |
| SOLID ap vao code thuc te (khong chi dinh nghia) | 0 | | |

## Phase 3 tro di

Them bang khi bat dau phase tuong ung (Claude de xuat danh sach dua tren `docs/roadmap.md`, nguoi lam chot).

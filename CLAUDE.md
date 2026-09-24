# CLAUDE.md

Huong dan cho Claude Code khi lam viec trong repo nay. File nay tu du (self-contained): moi phien, tren moi may, chi can file nay + `docs/` la du ngu canh.

## Pham vi va uu tien quy tac

- Day la du an hoc tap CA NHAN, doc lap voi cong viec chinh (HIS). Neu co quy tac global (`~/.claude/CLAUDE.md`) xung dot voi file nay, **file nay thang**.
- Bo qua cac quy tac global chi danh cho cong viec: Oracle, IntelliJ MCP DB, DB DEV dung chung, du lieu benh nhan, VNPT HIS, JasperReports/FreeMarker. Du an nay dung H2 in-memory (sau la PostgreSQL local) cua rieng nguoi lam.
- Nguon su that ve tien do la cac file trong repo (xem "Theo doi tien do"), KHONG phai auto-memory cua Claude (chi nam tren 1 may, khong theo git).

## Boi canh nguoi hoc

Nguoi lam co 2-5 nam kinh nghiem Java/Spring Boot, cong viec chinh la Hospital Information System multi-tenant tren Oracle (kien truc layered truyen thong), nhung tu nhan trinh do con yeu hon so nam kinh nghiem, dac biet ve kien truc & design patterns. Toc do hoc < 5 gio/tuan. Muc tieu cuoi: san sang phong van senior / nhay vao du an moi ma khong bi ngop.

## Vai tro cua Claude: mentor, khong phai nguoi code thay

Muc tieu la nang kha nang danh gia ky thuat cua nguoi lam theo thoi gian, khong chi hoan thanh task.

- **Nguoi lam tu viet code.** Claude KHONG viet/sua code trong `src/` tru khi duoc yeu cau ro rang cho dung lan do (co cau hinh `ask` trong `.claude/settings.json` lam lop chan thu 2).
- **Khi nguoi lam bi:** goi y theo bac (chi tiet trong skill `/goi-y`): cau hoi dan dat -> khai niem/huong -> hint cu the -> snippet nho. Khong dua loi giai tron ven tu dau. Ghi lai da dung bac may.
- **Khi review:** chi ra van de + ly do ky thuat cu the, de nguoi lam tu sua. Co cach tot hon thi neu trade-off, khong tu ap vao.
- **Truoc khi theo 1 huong tiep can** (cua nguoi lam hoac cua Claude): noi ro no giai quyet duoc gi, KHONG giai quyet duoc gi, rui ro/tac dung phu.
- **Phan bien chu dong:** khong lang le dong y voi cach chua toi uu. Task co hoc thi lam thang, khong phan bien guong ep.
- **Giai thich "tai sao"** khi de xuat pattern - muc tieu la hieu, khong chi co san pham chay. Lien he voi kinh nghiem HIS (layered) khi giup de hieu su khac biet.
- **Dung phase hien tai.** Khong dua viec cua phase sau vao som (VD Redis/RabbitMQ khi dang Phase 2) tru khi duoc yeu cau ro rang.
- **Duoc sua tai lieu** (`CLAUDE.md`, `docs/`, `.claude/`) khi duoc yeu cau hoac trong cac skill tien do.
- **Goi y lam viec voi AI:** khi phu hop, them 1 dong 💡 ngan cuoi cau tra loi (VD `/compact` khi hoi thoai dai, plan mode truoc thay doi phuc tap, `/model` phu hop do kho, cach viet prompt ro hon). Khong lap lai trong 1 phien, khong chen khi dang debug gap.

## Theo doi tien do

| File | Noi dung | Cap nhat |
|---|---|---|
| `docs/progress.md` | Phase/buoc hien tai, checklist, buoc tiep theo, cau hoi dang treo, diem yeu dang theo doi | Cuoi moi buoi (`/ket-thuc`) |
| `docs/journal/YYYY-MM-DD.md` | Nhat ky tung buoi: muc tieu, da lam, kho khan + bac hint da dung, rut ra | Cuoi moi buoi (`/ket-thuc`) |
| `docs/competencies.md` | Ban do kien thuc, cham 3 muc (giai thich / ap dung / phan bien) kem bang chung | Cuoi moi phase (`/kiem-tra`) |
| `docs/roadmap.md` | Lo trinh 9 phase + trang thai tung phase | Khi doi phase |
| `docs/adr/` | ADR, danh so tuan tu toan repo (khong gan so voi phase) | Cuoi moi phase |

Quy trinh 1 buoi: `/bat-dau` -> lam viec (`/goi-y` khi bi) -> `/ket-thuc`. Cuoi phase: `/kiem-tra`.

Du nguoi lam khong goi `/bat-dau`, Claude van phai doi chieu yeu cau voi `docs/progress.md` (duoc nap ben duoi). Neu thay progress lech voi thuc te (code/git), neu ra va de nghi cap nhat - khong tu im lang sua.

Tien do hien tai:

@docs/progress.md

## Nghiep vu (da chot)

Yeu cau day du theo giong khach hang + map sang phase: `docs/yeu-cau.md`.

- **Loai hinh:** salon/spa (cat toc, goi dau, massage...). 1 provider phuc vu 1 khach trong suot slot. Co y KHONG chon phong kham (qua gan nghe HIS, lai keo theo nhieu tai nguyen cung luc).
- **Provider:** nhan vien cua MOT cua hang duy nhat - KHONG multi-tenant. Multi-tenant la bai toan ha tang, nguoi lam da quen tu HIS; neu co thi la bai tap rieng, khong gan vao lo trinh chinh.
- **Thanh toan:** bat buoc truoc khi CONFIRMED. Vong doi: PENDING -> AWAITING_PAYMENT -> CONFIRMED -> COMPLETED, CANCELLED nhanh ra tu nhieu diem. Truoc khi co phase payment, "da thanh toan" chi mo phong bang method/domain event (VD `Booking.markPaymentReceived()`), khong tich hop cong thanh toan.
- **Trung lich** phai tinh theo provider (2 khach khac nhau khong duoc dat trung gio cung 1 provider). Phase 1 duoc phep check theo `customerId` (nhu skeleton) vi chua co khai niem provider - sua o Phase 2.
- **Kenh dat lich:** chi khach tu dat qua web/app/API. Nhan vien/quan ly dat ho (khach goi dien, den truc tiep) nam ngoai pham vi (chot 2026-09-24).
- **Huy va hoan tien (F9):** khach huy lich da thanh toan truoc gio hen >= 1 tieng -> hoan tien, sat hon -> khong hoan. Quan ly huy -> luon hoan tien. Nhan vien khong duoc huy (chot 2026-09-24).
- **Chong spam (N5):** giu slot 3 lan lien tiep trong 1 tieng khong thanh toan -> chan dat lich 1 ngay (chot 2026-09-24).
- **Dang nhap:** khach va nhan vien deu co tai khoan, cung quy tac bao mat. Du lieu dang nhap tach khoi ho so nhan vien/khach (chot 2026-09-24).
- User, Service Catalog, Payment, Notification van toi gian (chi la ID/string tho, chua co aggregate rieng) cho den khi phase tuong ung can.

## Stack va lenh

Java 21, Spring Boot 4.1 (Spring Framework 7, Jakarta EE 11), Spring Data JPA, H2 (profile `dev`, mac dinh) / PostgreSQL (profile `postgres`, dung tu Phase 3), JUnit 5. Khong co Maven wrapper - dung `mvn` cai san tren may.

```bash
mvn spring-boot:run                                        # profile dev (H2 in-memory, console tai /h2-console)
mvn spring-boot:run -Dspring-boot.run.profiles=postgres    # can Postgres tai localhost:5432/bookingdb (env DB_USERNAME / DB_PASSWORD)
mvn test                                                   # toan bo test
mvn test -Dtest=TenClassTest                               # 1 class
mvn test -Dtest=TenClassTest#ten_method                    # 1 method
mvn package                                                # build jar
```

`dev` dung `ddl-auto: update`; `postgres` dung `ddl-auto: validate` (schema phai co san - Flyway la viec cua Phase 3).

## Git

- `main`: code nguoi lam tu viet. Bat dau tu commit xoa code skeleton.
- `skeleton-reference`: skeleton tao san, CHI de tham khao/so sanh. Khong merge vao `main`.
  - Xem 1 file: `git show skeleton-reference:<duong-dan>`
  - So sanh cuoi phase: `git diff skeleton-reference main -- src/`
- Khi nguoi lam bi, KHONG chu dong mo/trich skeleton - di theo bac hint truoc; skeleton la bac cuoi, va nguoi lam tu quyet dinh mo.

## Kien truc muc tieu: Hexagonal (Ports & Adapters)

Base package `com.vietanh.booking`. Ly do chon va danh doi: `docs/adr/0001-hexagonal-architecture.md`. Day la dich den, nguoi lam co the lam khac neu co ly do - khi do Claude hoi ly do va de xuat ghi lai (journal hoac ADR). Cap nhat muc nay theo code thuc te khi xong Phase 1.

```
domain/            logic nghiep vu thuan - KHONG import Spring hay jakarta.persistence.*
  model/             aggregate + value object (record)
  port/in/           use case interface (inbound port)
  port/out/          repository interface (outbound port)
  service/           domain service + strategy
application/       implement inbound port, orchestrate domain + outbound port. Duoc dung @Service, khong dung JPA/HTTP
infrastructure/    adapter - noi duy nhat duoc dung Spring/JPA/HTTP
  persistence/       JPA entity + Spring Data repo, boc boi adapter (map domain <-> JPA entity)
  web/               REST controller, DTO, exception handler
  config/            noi DUY NHAT bien domain object thanh Spring bean
```

## Quy uoc bat buoc

- `domain/` khong import bat ky thu gi tu Spring hay `jakarta.persistence.*`. Class domain khong gan `@Component`; domain service/strategy wire trong config cua `infrastructure/`.
- Aggregate dung static factory, khong dung constructor public: `createNew(...)` cho doi tuong moi, `reconstitute(...)` chi dung trong adapter khi doc tu persistence.
- Domain bao loi bang exception Java thuong; exception handler map `IllegalStateException` -> 409, `IllegalArgumentException` -> 400 (chuan RFC 7807 de Phase 4).
- Test domain la JUnit thuan, khong Spring context. Ten test method: tieng Viet khong dau, snake_case.
- Comment va tai lieu viet tieng Viet khong dau.
- Moi phase ket thuc bang 1 ADR moi trong `docs/adr/` (`000N-ten.md`, cac muc: Boi canh / Quyet dinh / Ly do / Danh doi / Trang thai). Day la thoi quen dang luyen, khong phai thu tuc hinh thuc.

## Lo trinh

Ban day du va trang thai tung phase: `docs/roadmap.md`. Phase 9 (microservices): QUYET DINH DA CHOT la chua tach, KHONG tu y bat dau tru khi duoc yeu cau ro rang.

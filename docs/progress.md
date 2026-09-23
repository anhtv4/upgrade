# Tien do

> File nay duoc nap tu dong vao moi phien (qua `CLAUDE.md`). Giu NGAN GON - chi trang thai hien tai. Lich su chi tiet nam o `docs/journal/`.

Cap nhat lan cuoi: 2026-09-23

## Hien tai

- **Phase:** 1 - Nen tang & kien truc (bat dau 2026-09-23, du kien ~2 tuan)
- **Buoc dang lam:** 3 - Tai lieu bounded context
- **Buoc tiep theo (cu the):** Tao `docs/bounded-contexts.md`, lam lan luot: (1) moi context so huu du lieu gi - bat dau bang cau hoi "Provider thuoc context nao - Booking hay context rieng? Tai sao?"; (2) tham chieu bang ID hay object; (3) giao tiep dong bo hay event. Het gio sau (2) thi de (3) buoi sau.

## Checklist Phase 1

- [x] 1. Setup git: nhanh `skeleton-reference` (skeleton tham khao) + `main` sach, push len GitHub (2026-09-23)
- [x] 2. Doi default branch tren GitHub sang `main` (nguoi lam tu lam tren web) (2026-09-23)
- [ ] 3. Tai lieu bounded context `docs/bounded-contexts.md`: 5 context (User, Service Catalog, Booking, Notification, Payment), moi context so huu du lieu gi, giao tiep the nao, tham chieu nhau bang ID hay object
- [ ] 4. `BookingPlatformApplication` - app rong chay duoc
- [ ] 5. Domain model: value object (record) + aggregate `Booking` voi static factory
- [ ] 6. Domain logic: port in/out, domain service, Strategy cho conflict check + unit test JUnit thuan
- [ ] 7. Application: implement use case "tao booking"
- [ ] 8. Infrastructure: JPA entity + repository adapter, controller + DTO, exception handler, bean config
- [ ] 9. Chay end-to-end (curl tao booking, thu trung lich -> 409)
- [ ] 10. So sanh voi skeleton (`git diff skeleton-reference main -- src/`), ghi khac biet + ly do vao journal
- [ ] 11. ADR ket thuc phase (VD: ranh gioi bounded context) + cap nhat muc "Kien truc" trong `CLAUDE.md` theo code thuc te
- [ ] 12. `/kiem-tra` Phase 1 -> cap nhat `docs/competencies.md`

## Cau hoi / quyet dinh dang treo

- (chua co)

## Diem yeu dang theo doi

Quan sat qua nhieu buoi (Claude cap nhat khi thay lap lai). Dung de chon trong tam goi y va cau hoi kiem tra.

- (chua co du lieu - moi bat dau)

## Nhat ky cac buoi

- 2026-09-23 - [Setup du an va he thong theo doi](journal/2026-09-23.md)
- 2026-09-23 - [Doi default branch GitHub sang main](journal/2026-09-23-2.md)

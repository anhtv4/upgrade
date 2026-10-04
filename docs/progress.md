# Tien do

> File nay duoc nap tu dong vao moi phien (qua `CLAUDE.md`). Giu NGAN GON - chi trang thai hien tai. Lich su chi tiet nam o `docs/journal/`.

Cap nhat lan cuoi: 2026-10-04

## Hien tai

- **Phase:** 1 - Nen tang & kien truc (bat dau 2026-09-23, keo len ~3 tuan -> den ~2026-10-14, chot 2026-10-04)
- **Buoc dang lam:** 4 - App rong chay duoc
- **Buoc tiep theo (cu the):** (1) Tao `BookingPlatformApplication` trong `com.vietanh.booking` (hien `src/` moi co `application.yml`), `mvn spring-boot:run` chay len profile `dev`, mo duoc `/h2-console`. (2) Sang buoc 5: value object (record) + aggregate `Booking` voi static factory. Tai lieu con treo (muc duoi) hoan thien dan khi code, khong quay lai lam rieng.

## Checklist Phase 1

- [x] 1. Setup git: nhanh `skeleton-reference` (skeleton tham khao) + `main` sach, push len GitHub (2026-09-23)
- [x] 2. Doi default branch tren GitHub sang `main` (nguoi lam tu lam tren web) (2026-09-23)
- [x] 3. Tai lieu bounded context `docs/bounded-contexts.md` (2026-10-04 - dong theo han tai lieu, cac cau con treo hoan thien khi code)
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

- Cau 3 Payment (phat su kien thay vi goi thang): da chot, nhung nguoi lam moi "hieu chung chung" -> lam ro khi code buoc 7-8. `/kiem-tra` hoi lai.
- Bang chan spam trong Booking: ten ("Han che dat lich"?) + bo dem luu rieng hay dem tu booking -> chot o buoc 5.
- Booking `status` gop "giu slot/cho thanh toan" trong khi vong doi chot `PENDING -> AWAITING_PAYMENT`: gop hay tach? (lien quan tinh huong tien ve sau khi da huy giu slot) -> buoc 5.
- F16 (ai tao/sua/huy lich, luc nao) nam o dau khi Log khong phai context? "Ai thao tac" dinh danh bang ID Account, Provider hay Customer?
- F4 (ca lam, ngay nghi): giu o Phase 2 hay dua vao "Chua can"?
- Notification co can `ID booking` khong (noi dung da copy tu Booking luc gui)?

## Diem yeu dang theo doi

Quan sat qua nhieu buoi (Claude cap nhat khi thay lap lai). Dung de chon trong tam goi y va cau hoi kiem tra.

- Thiet ke bat dau tu bang DB thay vi tu trach nhiem/hanh vi: coi moi bang la 1 context, gom theo danh tu, gui du lieu vao bang co san `status`. Bang chung: [2026-09-24](journal/2026-09-24.md), [2026-09-24 buoi 2](journal/2026-09-24-2.md), [2026-09-24 buoi 3](journal/2026-09-24-3.md) (them `locked` vao cung field `status`), [2026-09-28](journal/2026-09-28.md), [2026-09-29](journal/2026-09-29.md), [2026-10-02](journal/2026-10-02.md) (ket luan dau "tach Shift/Calendar" vi thay 2 bang), [2026-10-04](journal/2026-10-04.md) (dat co chan spam o Account vi "Booking khong co truong nao"; nham lich hen nam o WorkSchedule). Tien bo: 2026-10-02 tu rut ra "tach bang khong co nghia la tach context"; 2026-10-04 tu nhan "dang noi context, chua noi bang" va tu de xuat bang rieng trong Booking.
- Phep thu ranh gioi: nham **thao tac du lieu** (quan ly bam tren man hinh) voi **thay doi quy tac** (dev sua code); tra loi mau thuan voi F14 vua chot (doi gio ca -> lich da xep cung doi). Bang chung: [2026-10-02](journal/2026-10-02.md) (bac 3, "9-11h"), [2026-10-04](journal/2026-10-04.md) (bac 2, "sua 2 cho", "sua ca truc tuan nay"). Cach tap: truoc khi dem, hoi "dev co phai sua dong code nao khong?".
- Ly do chung chung / tu khoa thay vi tinh huong cu the. Bang chung: [2026-09-29](journal/2026-09-29.md) (4 lan), [2026-10-02](journal/2026-10-02.md) ("lien ket chat hanh vi"), [2026-10-04](journal/2026-10-04.md) ("anh huong do chinh xac du lieu", ten "bang theo doi dat lich"). Cach tap: moi ly do phai kem 1 tinh huong "neu X doi thi...".
- Nho Claude viet/tong hop ket luan thay vi tu viet bang loi cua minh -> de "hieu khi nghe" ma chua tu dien dat duoc. Bang chung: [2026-09-29](journal/2026-09-29.md), [2026-10-02](journal/2026-10-02.md), [2026-10-04](journal/2026-10-04.md) (cau 3 Payment, ket luan chong spam - buoi thu 3 lien tiep). Cach tap: doc lai ban Claude viet, tu noi lai 1-2 cau; `/kiem-tra` se hoi lai. Khi chua hieu: viet "hieu den day: ..., vuong o: ..." de Claude nham dung cho.

## Nhat ky cac buoi

- 2026-09-23 - [Setup du an va he thong theo doi](journal/2026-09-23.md)
- 2026-09-23 - [Doi default branch GitHub sang main](journal/2026-09-23-2.md)
- 2026-09-24 - [Yeu cau khach hang va ban nhap bounded context](journal/2026-09-24.md)
- 2026-09-24 - [Y nghia bounded context, tra loi "ai sua, vi sao sua"](journal/2026-09-24-2.md)
- 2026-09-24 - [Tach Account khoi Provider, chot huy/hoan tien](journal/2026-09-24-3.md)
- 2026-09-28 - [Chot role o Account, tach Payment khoi Booking](journal/2026-09-28.md)
- 2026-09-29 - [Viet lai Payment, bang tham chieu ID/copy](journal/2026-09-29.md)
- 2026-10-02 - [Gom Shift + Calendar thanh WorkSchedule](journal/2026-10-02.md)
- 2026-10-04 - [Goi thang hay phat su kien, dong buoc 3](journal/2026-10-04.md)

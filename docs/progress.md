# Tien do

> File nay duoc nap tu dong vao moi phien (qua `CLAUDE.md`). Giu NGAN GON - chi trang thai hien tai. Lich su chi tiet nam o `docs/journal/`.

Cap nhat lan cuoi: 2026-09-29

## Hien tai

- **Phase:** 1 - Nen tang & kien truc (bat dau 2026-09-23, du kien ~2 tuan)
- **Buoc dang lam:** 3 - Tai lieu bounded context
- **Buoc tiep theo (cu the):** Trong `docs/bounded-contexts.md` (Payment viet lai + bang ID/copy da xong 2026-09-29): (1) gom nhom Shift + Calender - gop hay tach, gan Provider hay vung rieng; xu ly luon Calender chua co ngay truc + F4; (2) viet muc "Giao tiep dong bo hay event", bat dau tu cau 3 Payment con bo ngo: "neu Payment goi thang `booking.confirm()` thi Payment phai biet gi ve Booking? Them the thanh vien cung thanh toan qua Payment thi sao?"; (3) dong buoc 3.

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

- F16 (ai tao/sua/huy lich, luc nao) nam o dau khi Log khong phai context? "Ai thao tac" (khac `ID provider` = tho phuc vu) dinh danh bang ID Account, Provider hay Customer?
- F4 (ca lam, ngay nghi): giu o Phase 2 hay dua vao "Chua can"? (de xuat trong `yeu-cau.md`, roadmap chua ghi)
- Shift + Calender: gop 1? Gan Provider hay la vung rieng ("nhan vien ranh luc nao")? Calender hien chua co ngay truc.
- Notification co can `ID booking` khong (noi dung da copy tu Booking luc gui)?
- Vi sao Payment phat su kien thay vi goi thang Booking (cau 3 phan Payment, nguoi lam ghi "chua hieu").

## Diem yeu dang theo doi

Quan sat qua nhieu buoi (Claude cap nhat khi thay lap lai). Dung de chon trong tam goi y va cau hoi kiem tra.

- Thiet ke bat dau tu bang DB thay vi tu trach nhiem/hanh vi: coi moi bang la 1 context, gom theo danh tu ("deu la nguoi" -> User). Nguoi lam tu nhan "hieu sai y nghia context". Bang chung: [2026-09-24](journal/2026-09-24.md), [2026-09-24 buoi 2](journal/2026-09-24-2.md) (van ghi "Thong tin X", dung "He thong sua" thay cho su kien nghiep vu), [2026-09-24 buoi 3](journal/2026-09-24-3.md) ("dung chung 1 bang account", them gia tri `locked` vao cung field `status` thay vi nhan ra 2 trach nhiem doc lap), [2026-09-28](journal/2026-09-28.md) (gop "doi mat khau + doi so dien thoai" vi deu la "thay doi thong tin"; "lien quan tien -> Payment" du quy tac huy can du lieu cua Booking; "Lam gi: luu thong tin nhan vien"). Tien bo: da dung su kien nghiep vu cho Notification, tu ket luan duoc tach dang nhap; 2026-09-28 tu dua lap luan dung cho `role` sau cau hoi dan dat, nhan ra "khong hoan -> khong co giao dich -> Payment khong luu". Nguoi lam tu nhan (2026-09-28): quen "danh gia no la gi roi thiet ke DB", nen bi roi. [2026-09-29](journal/2026-09-29.md): van "2 context nhiem vu khac nhau" (gom theo chu de), "khong mat data khi thay doi cac bang khac"; nhung da tu ap dung cau hoi "cu hay moi" cho bang ID/copy, tu tranh bay Calender -> Shift.
- Ly do chung chung / tu khoa thay vi tinh huong cu the ("lam loang, phinh context", "giam rang buoc, mo rong", "thong tin quan trong", "lay tt moi nhat"). Bang chung: [2026-09-29](journal/2026-09-29.md) (4 lan trong 1 buoi). Cach tap: moi ly do phai kem 1 tinh huong "neu X doi thi...".

## Nhat ky cac buoi

- 2026-09-23 - [Setup du an va he thong theo doi](journal/2026-09-23.md)
- 2026-09-23 - [Doi default branch GitHub sang main](journal/2026-09-23-2.md)
- 2026-09-24 - [Yeu cau khach hang va ban nhap bounded context](journal/2026-09-24.md)
- 2026-09-24 - [Y nghia bounded context, tra loi "ai sua, vi sao sua"](journal/2026-09-24-2.md)
- 2026-09-24 - [Tach Account khoi Provider, chot huy/hoan tien](journal/2026-09-24-3.md)
- 2026-09-28 - [Chot role o Account, tach Payment khoi Booking](journal/2026-09-28.md)
- 2026-09-29 - [Viet lai Payment, bang tham chieu ID/copy](journal/2026-09-29.md)

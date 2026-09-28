# Tien do

> File nay duoc nap tu dong vao moi phien (qua `CLAUDE.md`). Giu NGAN GON - chi trang thai hien tai. Lich su chi tiet nam o `docs/journal/`.

Cap nhat lan cuoi: 2026-09-28

## Hien tai

- **Phase:** 1 - Nen tang & kien truc (bat dau 2026-09-23, du kien ~2 tuan)
- **Buoc dang lam:** 3 - Tai lieu bounded context
- **Buoc tiep theo (cu the):** Trong `docs/bounded-contexts.md`: (1) doc lai phan Payment (Claude viet theo lua chon trac nghiem buoi 2026-09-28), dien dat lai bang loi cua minh; (2) dien bang "Tham chieu bang ID hay copy" - bat dau tu: Booking can gia dich vu tu Service Catalog, giu ID hay copy gia tai thoi diem dat? (quan ly doi gia sau khi khach da dat thi sao?); (3) gom nhom context (Shift + Calender) roi dong buoc 3.

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
- Shift + Calender: gop 1? Gan Provider hay la vung rieng ("nhan vien ranh luc nao")?

## Diem yeu dang theo doi

Quan sat qua nhieu buoi (Claude cap nhat khi thay lap lai). Dung de chon trong tam goi y va cau hoi kiem tra.

- Thiet ke bat dau tu bang DB thay vi tu trach nhiem/hanh vi: coi moi bang la 1 context, gom theo danh tu ("deu la nguoi" -> User). Nguoi lam tu nhan "hieu sai y nghia context". Bang chung: [2026-09-24](journal/2026-09-24.md), [2026-09-24 buoi 2](journal/2026-09-24-2.md) (van ghi "Thong tin X", dung "He thong sua" thay cho su kien nghiep vu), [2026-09-24 buoi 3](journal/2026-09-24-3.md) ("dung chung 1 bang account", them gia tri `locked` vao cung field `status` thay vi nhan ra 2 trach nhiem doc lap), [2026-09-28](journal/2026-09-28.md) (gop "doi mat khau + doi so dien thoai" vi deu la "thay doi thong tin"; "lien quan tien -> Payment" du quy tac huy can du lieu cua Booking; "Lam gi: luu thong tin nhan vien"). Tien bo: da dung su kien nghiep vu cho Notification, tu ket luan duoc tach dang nhap; 2026-09-28 tu dua lap luan dung cho `role` sau cau hoi dan dat, nhan ra "khong hoan -> khong co giao dich -> Payment khong luu". Nguoi lam tu nhan (2026-09-28): quen "danh gia no la gi roi thiet ke DB", nen bi roi.

## Nhat ky cac buoi

- 2026-09-23 - [Setup du an va he thong theo doi](journal/2026-09-23.md)
- 2026-09-23 - [Doi default branch GitHub sang main](journal/2026-09-23-2.md)
- 2026-09-24 - [Yeu cau khach hang va ban nhap bounded context](journal/2026-09-24.md)
- 2026-09-24 - [Y nghia bounded context, tra loi "ai sua, vi sao sua"](journal/2026-09-24-2.md)
- 2026-09-24 - [Tach Account khoi Provider, chot huy/hoan tien](journal/2026-09-24-3.md)
- 2026-09-28 - [Chot role o Account, tach Payment khoi Booking](journal/2026-09-28.md)

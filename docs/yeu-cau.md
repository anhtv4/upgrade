# Yeu cau khach hang - "Salon May"

> Tai lieu nay viet theo giong KHACH HANG: chi mo ta nghiep vu, khong noi ve ky thuat hay cach chia bounded context (do la viec cua nguoi lam). Muc cuoi map yeu cau -> phase de biet khi nao dung toi.
> Sua khi nghiep vu thay doi; quyet dinh nghiep vu moi thi ghi them vao `CLAUDE.md` muc "Nghiep vu (da chot)".

## Boi canh

Chung toi la **mot salon** (cat toc, goi dau, massage, cham soc da), khoang **10 nhan vien**, mo cua 8:00-20:00 hang ngay. Hien khach goi dien dat lich, le tan ghi so, nen:
- Hay **xep trung** 2 khach vao cung 1 nhan vien.
- Khach dat roi **khong den**, mat slot cua khach khac.
- Cuoi tuan dong khach, le tan khong xu ly kip.

## Nguoi dung

| Vai tro | Lam gi |
|---|---|
| Khach hang | Xem dich vu, dat lich, thanh toan, huy lich **cua minh** |
| Nhan vien | Xem lich lam **cua minh**, danh dau hoan thanh, sua thong tin ca nhan |
| Quan ly | Quan ly dich vu/gia, nhan vien, ca lam/ngay nghi; xem va huy **moi** lich |

## Yeu cau chuc nang

**Dat lich**
- F1. Khach xem danh sach dich vu: ten, gia, thoi luong (VD "Cat + goi - 45 phut - 150.000d").
- F2. Khach dat lich: chon 1 dich vu, chon **nhan vien**, chon gio bat dau. Gio ket thuc = gio bat dau + thoi luong dich vu.
- F3. **Mot nhan vien chi phuc vu 1 khach tai 1 thoi diem.** Khong cho 2 lich chong gio tren cung 1 nhan vien. 2 nhan vien khac nhau thi cung gio van duoc.
- F4. Chi dat duoc trong gio mo cua, trong ca lam cua nhan vien, khong vao ngay nghi cua nhan vien. Khong dat gio trong qua khu.
- F5. **Gia chot tai thoi diem dat.** Quan ly doi gia sau do thi lich da dat van giu gia cu.
- F6. Khach xem duoc cac khung gio con trong cua 1 nhan vien trong 1 ngay.

**Thanh toan va vong doi lich**
- F7. Khach phai **thanh toan truoc** thi lich moi duoc xac nhan. Chua tra tien thi lich o trang thai cho.
- F8. Qua **15 phut** ke tu khi dat ma chua thanh toan thi lich tu huy, slot duoc tra lai.
- F9. Khach huy duoc lich cua minh (truoc khi thanh toan, hoac sau khi da xac nhan). Quan ly huy duoc moi lich, bat cu luc nao. Nhan vien khong duoc huy lich.
  - Lich da thanh toan, khach huy **truoc gio hen it nhat 1 tieng** -> duoc hoan tien. Huy sat hon 1 tieng -> van huy duoc nhung **khong hoan tien**. Quan ly huy lich da thanh toan -> **luon hoan tien** (chot 2026-09-24).
- F10. Lam xong dich vu, nhan vien danh dau **hoan thanh**. Lich da hoan thanh hoac da huy thi khong doi duoc nua.

**Thong bao**
- F11. Khach nhan thong bao (email/SMS) khi: dat thanh cong, da xac nhan (sau thanh toan), bi huy.
- F12. Gui thong bao cham hoac loi **khong duoc** lam hong/cham viec dat lich.

**Quan ly**
- F13. Quan ly them/sua/ngung dich vu va gia.
- F14. Quan ly them/sua nhan vien, ca lam, ngay nghi.
- F15. Quan ly tim lich theo ngay, nhan vien, khach, trang thai.
- F16. Biet **ai** tao/sua/huy 1 lich va **luc nao**.

## Yeu cau phi chuc nang

- N1. Gio cao diem cuoi tuan nhieu khach dat **cung luc cung slot** -> chi 1 nguoi duoc, khong bao gio ra 2 lich trung.
- N2. Mang cham, khach bam "Dat" 2 lan hoac app tu gui lai -> chi tao **1** lich.
- N3. Danh sach dich vu va khung gio trong duoc xem rat nhieu, it thay doi -> phai nhanh.
- N4. Sap co app mobile va co the co doi tac goi API -> API on dinh, co tai lieu, doi API khong lam vo client cu.
- N5. Chan bot/spam dat lich hang loat. VD: 1 khach giu slot 3 lan lien tiep trong 1 tieng ma khong thanh toan -> chan dat lich 1 ngay va bao cho khach (chot 2026-09-24).
- N6. Chu salon muon biet he thong co dang loi khong, so lich moi ngay.
- N7. Khach chi thay du lieu cua minh; thong tin dang nhap phai an toan.

## Chua can (ngoai pham vi)

Nhieu chi nhanh, chon phong/giuong, 1 lich nhieu dich vu, doi gio lich da dat, khuyen mai/tich diem, tich hop cong thanh toan that (chi mo phong "da thanh toan"), nhan vien/quan ly dat ho khach goi dien hoac den truc tiep (chot 2026-09-24: chi khach tu dat qua web/app/API).

## Map yeu cau -> phase

Cot "Ky nang" la ky nang se luyen, KHONG phai loi giai bat buoc.

| Yeu cau | Phase | Ky nang luyen |
|---|---|---|
| F2, F3 (tam check theo khach) | 1 | Hexagonal, aggregate, Strategy |
| F3 theo nhan vien, F5 | 2 | Domain model, value object, bat bien |
| F4 | 2 | Nhieu rule kiem tra -> ket hop Strategy *(de xuat bo sung, roadmap chua ghi)* |
| F7, F9, F10 | 2 | State pattern |
| F8 | 2 (mo phong) / 8 | Scheduled job, domain event |
| F11 (dong bo, noi bo) | 2 | Observer / domain event |
| F1, F13, F14 | 2-3 | Context rieng hay khong - quyet dinh cua nguoi lam |
| F15, F16, N1 | 3 | Specification/Querydsl, JPA Auditing, optimistic locking, isolation |
| N4, validation F4 | 4 | Versioning, RFC 7807, Bean Validation, OpenAPI |
| Vai tro, F9 "cua minh", N7 | 5 | Spring Security, JWT, RBAC, method-level security |
| Toan bo | 6 | Unit/integration test, Testcontainers |
| F6, N3 | 7 | Redis cache-aside, N+1 |
| F12, N2, N5, N6 | 8 | RabbitMQ + Outbox, idempotency key, rate limiting, Actuator/Micrometer |

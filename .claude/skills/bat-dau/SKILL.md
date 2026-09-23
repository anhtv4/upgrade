---
name: bat-dau
description: Mo dau 1 buoi hoc trong du an booking-platform - doc tien do, tom tat buoi truoc, doi chieu voi git, de xuat muc tieu buoi nay. Dung khi nguoi lam go /bat-dau hoac noi "bat dau buoi hoc", "hom nay lam gi".
---

# Bat dau buoi hoc

Muc tieu: trong < 2 phut, nguoi lam biet minh dang o dau va buoi nay lam gi. Tra loi NGAN.

## Buoc

1. Doc `docs/progress.md` (da nap qua CLAUDE.md, doc lai neu can) va file journal moi nhat trong `docs/journal/`.
2. Doi chieu voi thuc te:
   - `git status`, `git log --oneline -5` tren nhanh hien tai.
   - Neu dang KHONG o `main` -> nhac.
   - Neu code/commit cho thay tien do khac voi `progress.md` (VD buoc da xong nhung chua tick, hoac co thay doi chua commit tu buoi truoc) -> neu ro su lech, hoi nguoi lam truoc khi cap nhat.
   - Neu buoi truoc khong co journal (quen `/ket-thuc`) -> nhac va de nghi ghi bu ngan.
3. Trinh bay:
   - **Lan truoc:** 1-2 dong tu journal (lam gi, dung o dau).
   - **On nhanh:** 1 cau hoi ngan ve kien thuc buoi truoc (lay tu "Rut ra" cua journal). Doi nguoi lam tra loi truoc khi di tiep; neu tra loi chua vung thi giai thich ngan, ghi nho de dua vao "Diem yeu dang theo doi" khi `/ket-thuc`. Bo qua neu buoi truoc chi la setup/viec co hoc.
   - **Buoi nay:** de xuat 1 muc tieu vua suc 1 buoi (~1-2 gio) tu "Buoc tiep theo". Neu buoc qua lon, de xuat chia nho.
   - **Cau hoi treo:** nhac neu co muc lien quan.
4. Hoi nguoi lam: dong y muc tieu hay muon doi? Co bao nhieu thoi gian buoi nay?

## Khong lam

- Khong viet code, khong dua loi giai cho buoc tiep theo.
- Khong tu sua `progress.md` o buoc nay tru khi nguoi lam dong y sua su lech.

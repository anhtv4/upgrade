---
name: ket-thuc
description: Ket thuc 1 buoi hoc trong du an booking-platform - soan journal cua buoi, cap nhat docs/progress.md, commit va push sau khi nguoi lam duyet. Dung khi nguoi lam go /ket-thuc hoac noi "ket thuc buoi", "hom nay den day thoi".
---

# Ket thuc buoi hoc

Muc tieu: nguoi lam chi mat ~2 phut duyet; Claude soan san. Moi thong tin phai lay tu hoi thoai + git, KHONG bia (khong ro thi de trong hoac hoi).

## Buoc

1. Thu thap:
   - Hoi thoai cua buoi nay: muc tieu, da lam gi, kho khan, bac hint da dung (xem `/goi-y`), khai niem da giai thich, cau hoi con treo.
   - `git status`, `git diff --stat`, `git log` tu dau buoi.
2. Hoi nguoi lam 2 cau NGAN (gop 1 lan):
   - Buoi nay mat khoang bao lau?
   - Tu thay dieu quan trong nhat rut ra la gi? (de nguoi lam tu dien dat - day la phan hoc chinh; Claude bo sung neu thieu)
3. Soan `docs/journal/YYYY-MM-DD.md` (trung ngay thi them hau to `-2`) theo mau:

   ```
   # YYYY-MM-DD - <tieu de ngan>

   **Phase:** N | **Thoi luong:** ~X gio

   ## Muc tieu buoi
   ## Da lam
   ## Kho khan / bac hint da dung
   (moi van de: mo ta ngan + bac hint cao nhat da dung 1-4 + co mo skeleton khong)
   ## Rut ra
   (loi cua nguoi lam truoc, Claude bo sung sau)
   ## Cau hoi con treo
   ## Buoc tiep theo
   ```

4. Cap nhat `docs/progress.md`:
   - Tick checklist, cap nhat "Buoc dang lam", "Buoc tiep theo" (phai CU THE, du de buoi sau vao viec ngay), ngay cap nhat.
   - "Cau hoi / quyet dinh dang treo": them/xoa.
   - "Diem yeu dang theo doi": chi them khi thay lap lai (>= 2 lan) hoac nguoi lam tu nhan; ghi kem bang chung (link journal). Xoa khi da vuot qua.
   - Them dong vao "Nhat ky cac buoi".
   - Giu file NGAN - chi trang thai hien tai.
5. Neu buoc vua xong la buoc cuoi phase -> nhac chay `/kiem-tra` va viet ADR truoc khi sang phase moi.
6. Cho nguoi lam xem ban soan (tom tat thay doi, khong can dump toan bo). Sua theo gop y.
7. Commit sau khi nguoi lam dong y:
   - Chi tu commit tai lieu (`docs/`, `CLAUDE.md`, `.claude/`), message dang `docs: journal YYYY-MM-DD, cap nhat tien do`.
   - Neu `src/` co thay doi chua commit: HOI nguoi lam muon tu commit code hay gop vao. Khuyen nguoi lam tu commit code voi message cua minh (luyen viet commit message).
   - Push len `origin` nhanh hien tai.

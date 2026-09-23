---
name: goi-y
description: Goi y theo bac khi nguoi lam bi trong du an booking-platform - cau hoi dan dat truoc, loi giai sau cung. Dung khi nguoi lam go /goi-y, hoac noi "bi roi", "khong biet lam sao", "goi y di", "cho hint".
---

# Goi y theo bac

Nguyen tac: nguoi lam tu tim ra loi giai thi moi nho lau. Moi lan chi len 1 bac, roi dung lai cho nguoi lam thu.

## Truoc khi goi y

- Neu chua ro: hoi nguoi lam dang muon lam gi, da thu gi, ket qua mong doi vs thuc te (loi, output). Doc code lien quan trong `src/` neu can.
- Neu nguoi lam ghi `/goi-y <so>` (VD `/goi-y 3`) -> nhay thang toi bac do.
- Neu lan nay la tiep noi cung 1 van de -> len bac ke tiep, khong lap lai bac cu.

## Cac bac

1. **Cau hoi dan dat** - 1-2 cau hoi khien nguoi lam tu nhin ra huong. VD: "Neu mai doi H2 sang MongoDB, class nao phai sua?" Khong neu dap an.
2. **Khai niem / huong** - goi ten khai niem hoac pattern lien quan, giai thich ngan no giai quyet gi, chi ra file/tang nen dat logic. Chua noi code cu the.
3. **Hint cu the** - mo ta cach lam bang loi hoac pseudo-code, chi ra chu ky method/ten type can co. Van de nguoi lam go code.
4. **Snippet nho** - doan code toi thieu (vai dong) cho DUNG diem bi, kem giai thich tung dong. Khong viet ca class/file. Sau do de nghi nguoi lam tu viet phan con lai.

Skeleton (`git show skeleton-reference:...`) la "bac 5" - chi khi nguoi lam TU yeu cau mo. Khi mo: chi xem dung phan lien quan, va hoi "khac gi voi cach ban dang nghi?".

## Ghi nhan

Ghi nho (trong hoi thoai) van de + bac cao nhat da dung + co mo skeleton khong, de `/ket-thuc` dua vao journal. Neu cung 1 loai van de can bac >= 3 lan thu 2 -> ung vien cho "Diem yeu dang theo doi".

## Khong lam

- Khong sua file trong `src/` (ke ca o bac 4 - dua snippet trong chat, nguoi lam tu dan).
- Khong nhay bac vi nguoi lam co ve voi, tru khi ho yeu cau ro.

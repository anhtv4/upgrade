---
name: kiem-tra
description: Phong van thu kieu senior cuoi moi phase cua du an booking-platform, cham muc do trong docs/competencies.md. Dung khi nguoi lam go /kiem-tra hoac noi "kiem tra phase", "phong van thu", "cham diem kien thuc".
---

# Kiem tra cuoi phase (phong van thu)

Muc tieu: danh gia trung thuc kha nang giai thich / ap dung / phan bien - giong phong van senior that, dua tren chinh code cua nguoi lam. Khoang 15-20 phut.

## Chuan bi

1. Xac dinh phase can kiem tra (mac dinh: phase hien tai trong `docs/progress.md`; hoac tham so VD `/kiem-tra 1`).
2. Doc bang phase do trong `docs/competencies.md`, journal cua phase, ADR, va code lien quan tren `main`.
3. Uu tien muc: cot loi cua phase + "Diem yeu dang theo doi" trong `progress.md`.

## Cach hoi

- Hoi TUNG CAU MOT, doi tra loi roi moi hoi tiep. Khong dua dap an truoc.
- Moi muc kien thuc dan len theo 3 tang:
  1. Giai thich: "X la gi, giai quyet van de gi?"
  2. Ap dung: chi vao code that cua nguoi lam - "Tai sao o day ban dat logic trong Y ma khong phai Z?", "Neu them yeu cau W thi sua o dau?"
  3. Phan bien: "Khi nao KHONG nen dung X?", "Phong van vien noi X la over-engineering cho app CRUD - ban tra loi sao?", so sanh voi cach lam o HIS (layered).
- Dung o tang nguoi lam bat dau lung tung; khong can hoi het moi muc - chon 5-7 muc quan trong nhat.
- Sau moi cau: nhan xet ngan (dung/thieu gi), khong giang dai. Giang giai de danh cho phan tong ket.

## Tong ket

1. Bang de xuat cham: moi muc -> muc 0-3 + ly do 1 dong (trich cau tra loi lam bang chung).
2. Nguoi lam dong y hoac phan bien tung muc. Phan bien co ly thi dieu chinh - bao ve quan diem cung la ky nang dang luyen.
3. Cap nhat `docs/competencies.md` (muc, bang chung, ngay). Muc < 2 o kien thuc cot loi -> dua vao "Diem yeu dang theo doi" trong `progress.md`.
4. Neu nhieu muc cot loi < 2: de xuat on lai truoc khi sang phase moi (cu the: doc gi, lam bai tap nho nao). Nguoi lam quyet dinh.
5. Goi y 2-3 cau hoi phong van that nguoi lam nen tu luyen noi thanh loi.
6. Ghi tom tat buoi kiem tra vao journal (qua `/ket-thuc` hoac ngay tai day neu nguoi lam muon).

## Khong lam

- Khong cham rong tay de dong vien, khong cham kho de de. Muc 3 chi khi thuc su bao ve duoc trade-off.
- Khong hoi kien thuc cua phase chua hoc.

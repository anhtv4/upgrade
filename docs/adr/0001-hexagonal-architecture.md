# ADR 0001: Chon Hexagonal Architecture cho du an hoc tap

## Boi canh
Diem yeu tu nhan: kien truc & design patterns, du da co 2-5 nam code Spring Boot tren he thong HIS (layered truyen thong: Controller -> Service -> Repository, phu thuoc chat vao JPA/Hibernate xuyen suot cac tang).

## Quyet dinh
Dung Hexagonal Architecture (Ports & Adapters) thay vi Layered truyen thong:
- domain/ khong phu thuoc Spring/JPA
- Giao tiep qua interface (port), implement o application/ (inbound) va infrastructure/ (outbound)

## Ly do
- Buoc phai tach business logic khoi framework - cho dang yeu nhat
- Domain logic test duoc ma khong can Spring context -> nen tang tot cho Phase 6 (Testing)
- De thay ro boundary giua "quyet dinh nghiep vu" va "chi tiet ky thuat" (DB, HTTP)

## Danh doi
- Nhieu file/interface hon layered truyen thong cho cung 1 tinh nang nho - chap nhan duoc vi muc tieu la hoc, khong phai toc do giao hang
- Can ky luat giu domain/ sach (khong lo tay import jakarta.persistence.* vao entity domain)

## Trang thai
Da ap dung tu Phase 1.

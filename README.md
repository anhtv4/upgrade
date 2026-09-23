# Booking Platform - Du an hoc tap Spring Boot

## Muc tieu
Du an ca nhan de luyen kien truc, design patterns, va cac cong nghe Spring Boot hien dai, tach biet voi cong viec chinh (HIS). Nghiep vu: dat lich salon/spa (1 cua hang, khong multi-tenant).

Muc tieu cuoi: san sang phong van senior / nhay vao du an moi khong bi ngop.

## Trang thai
Dang o Phase 1 - tu viet lai tu dau tren nhanh `main`. Chi tiet: `docs/progress.md`.

## Stack
- Java 21 (LTS)
- Spring Boot 4.1 (Spring Framework 7, Jakarta EE 11) - nhanh Spring Boot 3.x da EOL tu giua 2026
- Spring Data JPA + Hibernate
- H2 (dev, in-memory) / PostgreSQL (profile `postgres`, dung tu Phase 3)
- JUnit 5

## Cach chay
```bash
mvn spring-boot:run   # profile dev (H2 in-memory, console tai /h2-console)
mvn test
```

## Nhanh git
- `main` - code tu viet.
- `skeleton-reference` - skeleton tao san, chi de tham khao va so sanh cuoi Phase 1.

## Tai lieu
| File | Noi dung |
|---|---|
| `docs/roadmap.md` | Lo trinh 9 phase |
| `docs/progress.md` | Tien do hien tai, buoc tiep theo |
| `docs/journal/` | Nhat ky tung buoi hoc |
| `docs/competencies.md` | Ban do kien thuc + muc do dat duoc |
| `docs/adr/` | Architecture Decision Records |
| `CLAUDE.md` | Huong dan cho Claude Code (vai tro mentor, quy uoc) |

## Lam viec voi Claude Code
Claude dong vai tro mentor: goi y va review, khong viet code thay. Lenh trong `.claude/skills/`:

| Lenh | Khi nao |
|---|---|
| `/bat-dau` | Dau buoi: tom tat tien do, on nhanh, chot muc tieu buoi |
| `/goi-y` | Khi bi: goi y theo bac (cau hoi -> khai niem -> hint -> snippet). `/goi-y 3` de nhay bac |
| `/ket-thuc` | Cuoi buoi: soan journal, cap nhat tien do, commit + push |
| `/kiem-tra` | Cuoi phase: phong van thu, cham `docs/competencies.md` |

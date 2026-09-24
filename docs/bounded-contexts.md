# Bounded Contexts

> Nghiep vu goc: `docs/yeu-cau.md`. Tai lieu nay tra loi: moi context lam gi, so huu du lieu gi, lien he voi nhau the nao.

## Tong quan

| Context         | Trach nhiem (1 cau)                                  |
|-----------------|------------------------------------------------------|
| Provider        | Thông tin nhân viên                                  |
| Service Catalog | Chứa thông tin loại dịch vụ: gội đầu, làm móng,... . |
| Booking         | Chứa thông tin đặt lịch                              |
| Customer        | Thông tin khách                                      |
| Notification    | Thông tin thông báo                                  |
| Shift           | Thông tin ca trực (danh mục)                         |
| Calender        | Lịch trực của nhân viên                              |
| Log             | Thông tin các thay đổi như nghỉ, hủy lịch, đổi giá   |


## Design:
- Service Catalog: ID, name, duration, price
- Provider: ID, fullname, phone number, username, password, role (staff - 1, manager - 2), status (active, off)
- Customer: ID, fullname, phone, mail, status, notify-chanel (cấu hình nhận thông báo, 1 = phone, 2 = mail, 0 = không nhận)
- Shift: ID, time start, time end, name
- Calender: ID, ID shift, ID provider, status (on-word, dayoff), because (ý là lý do nghỉ chẳng hạn)
- Booking: ID, ID provider, ID customer, time start, time end, price, status (holding, đã thanh toán, đã hoàn thành (dịch vụ), đã hủy, hoàn tiền), cause
- Notification: ID, ID customer, context, status (not send, sended, fail), note
- Log: ID, user-create, time-create, catalog, context, note

Đây là thiết kế của tôi, có thể chính tả ngữ pháp chưa chuẩn

## Trả lời câu hỏi (Ai sửa, vì sao):
- Service Catalog:
  - Ai sửa: Quản lý
  - Thay đổi vì: Salon thay đổi danh mục, giá dịch vụ
- Provider:
  - Hệ thống, quản lý, nhân viên
  - Thay đổi nhân viên, mới vào hoặc nghỉ làm. Nhân viên tự cập nhật thông tin
- Customer:
  - Hệ thống, khách hàng
  - Khách hàng đăng ký tài khoản hoặc update thông tin
- Shift: 
  - Quản lý, hệ thống
  - Thay đổi thông tin ca làm
- Calender:
  - Quản lý
  - Thay đổi thông tin trực các ca, nhân viên nghỉ trực hoặc đổi ca
- Booking:
  - Khách hàng: Đặt lịch, hủy lịch
  - Quản lý: hủy lịch
- Notifycation:
  - Hệ thống
  - Gửi thông tin thông báo cho khách hàng
- Log:
  - Hệ thống
  - Theo dõi thay đổi của các thao tác

## Quan he giua cac context

### Tham chieu bang ID hay object / copy du lieu

| Context dung | Can gi | Tu context | Cach giu (ID / copy tai thoi diem ...) | Ly do |
|-----------|---|---|---|---|
|           | | | | |

### Giao tiep dong bo hay event

(buoi sau)

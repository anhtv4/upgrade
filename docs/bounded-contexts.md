# Bounded Contexts

> Nghiep vu goc: `docs/yeu-cau.md`. Tai lieu nay tra loi: moi context lam gi, so huu du lieu gi, lien he voi nhau the nao.

## Tong quan

| Context         | Trach nhiem (1 cau)                                  |
|-----------------|------------------------------------------------------|
| Provider        | Hồ sơ nhân viên: đang làm hay đã nghỉ, vai trò ở salon |
| Account         | Đăng nhập cho cả nhân viên và khách (username, password, khóa/mở) |
| Service Catalog | Chứa thông tin loại dịch vụ: gội đầu, làm móng,... . |
| Booking         | Chứa thông tin đặt lịch                              |
| Customer        | Thông tin khách                                      |
| Notification    | Thông tin thông báo                                  |
| Shift           | Thông tin ca trực (danh mục)                         |
| Calender        | Lịch trực của nhân viên                              |


## Design:
- Service Catalog: ID, name, duration, price
- Provider: ID, ID account, fullname, phone number, role (staff - 1, manager - 2; câu hỏi treo: role thuộc Provider hay Account?), status (đang làm, đã nghỉ)
- Account: ID, username, password, status (bình thường, khóa tạm, vô hiệu)
- Customer: ID, ID account, fullname, phone, mail, status, notify-chanel (cấu hình nhận thông báo, 1 = phone, 2 = mail, 0 = không nhận)
- Shift: ID, time start, time end, name
- Calender: ID, ID shift, ID provider, status (on-word, dayoff), because (ý là lý do nghỉ chẳng hạn)
- Booking: ID, ID provider, ID customer, time start, time end, price, status (holding, đã thanh toán, đã hoàn thành (dịch vụ), đã hủy, hoàn tiền), cause
- Notification: ID, ID customer, context, status (not send, sended, fail), note

Đây là thiết kế của tôi, có thể chính tả ngữ pháp chưa chuẩn

## Trả lời câu hỏi (Ai sửa, vì sao):
- Service Catalog:
  - Ai sửa: Quản lý
  - Thay đổi vì: Salon thay đổi danh mục, giá dịch vụ
- Provider (hồ sơ nhân viên):
  - Quản lý: tuyển nhân viên mới (tạo hồ sơ), cho nghỉ việc (chuyển "đã nghỉ", KHÔNG xóa vì lịch cũ vẫn tham chiếu), đổi vai trò
  - Nhân viên: tự cập nhật tên, số điện thoại
- Account (đăng nhập, dùng chung cho nhân viên và khách):
  - Quản lý: tạo tài khoản + mật khẩu lần đầu cho nhân viên; mở khóa, cấp lại mật khẩu
  - Khách hàng: tự đăng ký tài khoản
  - Chính chủ: đổi mật khẩu
  - Nhập sai mật khẩu nhiều lần -> khóa tạm. Nhân viên nghỉ việc -> vô hiệu tài khoản
  - Quy tắc bảo mật giống nhau cho nhân viên và khách -> chỉ viết 1 lần ở đây
  - Vì sao tách khỏi Provider: "còn làm cho salon không" và "đăng nhập được không" thay đổi độc lập (VD đã nghỉ + đang khóa tạm) -> 1 field status không biểu diễn được
  - Tạo nhân viên mới = tạo hồ sơ Provider + tạo Account; lỗi thì rollback cả 2. Chỉ làm được vì đang monolith, chung DB/transaction
- Customer:
  - Khách hàng
  - Khách hàng tự cập nhật thông tin cá nhân, cấu hình nhận thông báo (tài khoản đăng nhập thuộc Account)
- Shift: 
  - Quản lý
  - Khởi tạo hoặc thay đổi thông tin ca làm
- Calender:
  - Quản lý
  - Thay đổi thông tin trực các ca, nhân viên nghỉ trực hoặc đổi ca
- Booking:
  - Khách hàng, Quản lý, nhân viên
  - Khách hàng đặt lịch trong thời gian hoạt động. Có thể hủy trước 1 tiếng so với thời gian đặt, sau 1 tiếng không hoàn tiền.
  - Nhân viên không thể hủy. Quản lý có thể hủy bất cứ lúc nào đi kèm thông báo cho khách
  - Nếu khách đặt lịch, giữ slot cho khách. Trong 15 phút không thanh toán, hủy bỏ giữ slot. Giữ slot liên tục 3 lần trong 1 tiếng mà không thanh toán, chặn trong 1 ngày, gửi thông báo.
  - Khi khách thanh toán, đổi trạng thái, khóa slot, thông báo cho khách
  - Khi hủy slot trước 1 tiếng, hoàn tiền về cho khách
- Notification:
  - Hệ thống
  - Gửi thông tin thông báo cho khách hàng khi 1 trạng thái mới được ghi nhận.

## Quan he giua cac context

### Tham chieu bang ID hay object / copy du lieu

| Context dung | Can gi | Tu context | Cach giu (ID / copy tai thoi diem ...) | Ly do |
|-----------|---|---|---|---|
|           | | | | |

### Giao tiep dong bo hay event

(buoi sau)

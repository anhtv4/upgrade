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
| Payment         | Thực hiện giao dịch thu/hoàn tiền và theo dõi kết quả giao dịch |


## Design:
- Service Catalog: ID, name, duration, price, status (đang bán, ngừng bán - xóa mềm vì Booking giữ ID dịch vụ)
- Provider: ID, ID account, fullname, phone number, status (đang làm, đã nghỉ)
- Account: ID, username, password, status (bình thường, khóa tạm, vô hiệu), role
- Customer: ID, ID account, fullname, phone, mail, status, notify-chanel (cấu hình nhận thông báo, 1 = phone, 2 = mail, 0 = không nhận)
- Shift: ID, time start, time end, name
- Calender: ID, ID shift, ID provider, time start, time end (copy từ Shift lúc xếp lịch), status (on-word, dayoff), because (ý là lý do nghỉ chẳng hạn)
- Booking: ID, ID provider, ID customer, ID service catalog, time start, time end (= time start + duration copy lúc đặt), price (copy lúc đặt), status (giữ slot/chờ thanh toán, đã xác nhận, đã hoàn thành (dịch vụ), đã hủy), số tiền được hoàn (quyết định của Booking khi hủy), cause
- Notification: ID, ID customer, gửi tới (sđt/mail copy lúc gửi), context (nội dung, copy từ Booking lúc gửi), status (not send, sended, fail), note
- Payment: ID, ID booking, loại (thu / hoàn), số tiền, mã giao dịch cổng, status (đang xử lý, thành công, thất bại)

Đây là thiết kế của tôi, có thể chính tả ngữ pháp chưa chuẩn

## Trả lời câu hỏi (Ai sửa, vì sao):
- Service Catalog:
  - Ai sửa: Quản lý
  - Thay đổi vì: Salon thay đổi danh mục, giá dịch vụ
- Provider (hồ sơ nhân viên):
  - Quản lý: tuyển nhân viên mới (tạo hồ sơ), cho nghỉ việc (chuyển "đã nghỉ", KHÔNG xóa vì lịch cũ vẫn tham chiếu)
  - Nhân viên: tự cập nhật tên, số điện thoại
  - Làm gì: Nhân viên này còn nhận khách được không. Nơi định danh 'ai' tương tác với các context khác như ai nghỉ, ai đang làm 
  ở lịch trưc, ai là người phụ trách ca đó. Ai là người đã đánh hoàn thành ở trạng thái đặt lịch
  - Không làm: không liên quan tới thanh toán, lịch nghỉ
  - Tham chiếu: Booking, Calendar dùng ID của Provider. Provider dùng ID của Account
- Account (đăng nhập, dùng chung cho nhân viên và khách):
  - Quản lý: tạo tài khoản + mật khẩu lần đầu cho nhân viên; mở khóa, cấp lại mật khẩu, đổi role
  - Khách hàng: tự đăng ký tài khoản
  - Chính chủ: đổi mật khẩu
  - Nhập sai mật khẩu nhiều lần -> khóa tạm. Nhân viên nghỉ việc -> vô hiệu tài khoản
  - Quy tắc bảo mật giống nhau cho nhân viên và khách -> chỉ viết 1 lần ở đây
  - Vì sao tách khỏi Provider: "còn làm cho salon không" và "đăng nhập được không" thay đổi độc lập (VD đã nghỉ + đang khóa tạm) -> 1 field status không biểu diễn được
  - Tạo nhân viên mới = tạo hồ sơ Provider + tạo Account; lỗi thì rollback cả 2. Chỉ làm được vì đang monolith, chung DB/transaction
  - Làm gì: Thông tin đăng nhập, bảo mật
  - Không làm: Không can thiệp vào logic các chức năng khác, ví dụ không biết lịch trực, thanh toán như nào
  - Tham chiếu: Không tham chiếu
  - Role: 
    - Khi quản lý thăng chức, sẽ là quyết định bảo mật. vì app của chúng ta không có chức nănng nào liên quan tới vai 
    trò của nhân sự cả. Vì thế khi đổi role chỉ đơn giản là đổi quyền thao tác tới hệ thống
    - Với role của khách hàng, có thể để mặc định = CUSTOMER lúc tạo, chúng ta sẽ không can thiệp gì, chỉ để
    tường minh nó không liên quan tới role của nhân viên hay quản lý. Nếu sau này làm chức năng liên quan tới vai trò 
    của nhân viên thì sẽ tạo thêm 1 cột nữa để làm nhiệm vụ này, tránh lẫn lộn với role bên account. ví dụ như chức vụ.
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
- Payment:
  - Bằng lời của tôi:
    1. 
      - đổi cổng vnpay -> momo: sửa payment, booking không phải đụng
      - đổi chính sách hủy 1h -> 2h: sửa booking, không sửa payment
      - hai thay đổi này không xảy ra cùng lúc -> tách
    2. Booking giữ lại trạng thái của đơn khi hoàn tiền (đã hủy) và quyết định hoàn bao nhiêu. Còn payment giữ trạng thái hoàn tiền (thành công, thất bại)
      - booking tính ra con số 200k, nằm ở booking. tính được 200k dựa trên giá tiền đã chốt khi đặt và thời gian hủy lịch so với giờ hẹn
    3. chưa hiểu: ...
  
| Sự kiện          | Ai kích hoạt                                        | Quy tắc                                                           | Quy tắc đổi khi nào (lý do)                                                                      |
|------------------|-----------------------------------------------------|-------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| Khách trả tiền   | Khách sau khi giữ slot, trong vòng 15 phút          | Trả đủ tiền dịch vụ -> lịch chuyển "đã xác nhận" (F7)             | Đổi cổng/phương thức thanh toán -> Payment. Đổi thời gian giữ slot -> Booking                    |
| Hoàn tiền        | Khách hủy trước giờ hẹn >= 1 tiếng, hoặc quản lý hủy | Booking quyết định số tiền hoàn, Payment thực hiện giao dịch hoàn | Đổi chính sách (1h -> 2h, hoàn 1 phần) -> Booking. Đổi cách thực hiện (cổng, thử lại) -> Payment |
| Không hoàn tiền  | Khách hủy trước giờ hẹn < 1 tiếng                   | Không có giao dịch -> Payment không ghi gì                        | Đổi chính sách -> Booking. Payment không liên quan                                               |

  - Ghi chú:
    - Quyết định vs thực hiện: Booking quyết định có hoàn không, hoàn bao nhiêu (vì Booking nắm giờ hẹn + ai hủy). Payment thực hiện và giữ trạng thái giao dịch. VD cổng từ chối hoàn -> Booking vẫn đúng (đã hủy, được hoàn X), Payment ghi "thất bại" để xử lý tiếp.
    - Không hoàn tiền -> không có giao dịch -> Payment không lưu gì.
    - Payment phát sự kiện "đã nhận tiền" -> Booking chuyển "đã xác nhận". Payment không cần biết ai đang nghe (sau này có thêm thứ khác cần thanh toán thì Payment không phải sửa). Cơ chế truyền (trong process hay broker) quyết định ở phase sau.
    - Trạng thái chỉ đổi qua hành động nghiệp vụ (đặt, trả tiền, hủy, bấm hoàn thành), không có thao tác sửa status trực tiếp. "Hoàn thành" vẫn do người bấm.
    - "Ai thao tác" (người bấm hủy/hoàn thành) khác với `ID provider` (thợ phục vụ). Định danh bằng gì -> F16, còn treo.
  - **Kết luận: tách Payment thành context riêng.** Lý do: quy tắc của Payment (cổng, giao dịch, đối soát) và quy tắc đặt/hủy lịch thay đổi độc lập - đổi chính sách hủy chỉ sửa Booking, đổi cổng chỉ sửa Payment.

## Quan he giua cac context

### Tham chieu bang ID hay object / copy du lieu

**Câu hỏi quyết định:** sau khi sự kiện xảy ra (đặt lịch, xếp lịch, gửi thông báo, giao dịch), nếu dữ liệu gốc bị sửa thì muốn thấy giá trị **mới nhất** hay giá trị **đúng như lúc xảy ra**?
- Muốn giá trị mới nhất -> giữ **ID**, cần thì hỏi context gốc.
- Muốn giá trị như lúc xảy ra -> **copy** vào context của mình, tại đúng thời điểm đó (phải ghi rõ "lúc nào").

**Quy tắc chung rút ra:**
1. **ID + copy thường đi cùng nhau:** giữ ID để biết "là cái nào", copy để giữ "giá trị lúc đó". VD Booking giữ ID dịch vụ + copy giá, thời lượng.
2. **Giữ ID thì context gốc chỉ được xóa mềm:** Service Catalog -> "ngừng bán", Provider -> "đã nghỉ", Account -> "vô hiệu". Xóa cứng thì dữ liệu cũ trỏ vào ID không còn tồn tại.

| Context dùng | Cần gì | Từ context | Cách giữ | Lý do (tình huống) |
|---|---|---|---|---|
| Booking | dịch vụ nào (tên) | Service Catalog | ID | Hiển thị thông tin dịch vụ hiện tại. Dịch vụ ngừng bán thì xóa mềm, lịch cũ vẫn hiển thị được |
| Booking | giá | Service Catalog | copy lúc đặt | Khách đã trả 100k, quản lý tăng giá lên 150k -> lịch đã đặt vẫn là 100k. Số tiền hoàn khi hủy cũng tính trên giá này |
| Booking | thời lượng | Service Catalog | copy lúc đặt (thành `time end`) | Đổi gội đầu 30 -> 45 phút không được kéo dài các lịch đã đặt |
| Booking | khách | Customer | ID | Khách đổi SĐT sau khi đặt -> nhân viên cần gọi số mới |
| Booking | thợ phục vụ | Provider | ID | Thợ đổi tên/SĐT -> hiển thị mới. Thợ nghỉ thì "đã nghỉ", không xóa |
| Calender | ca nào | Shift | ID | Biết lịch trực thuộc ca nào |
| Calender | giờ bắt đầu/kết thúc ca | Shift | copy lúc xếp lịch | Ca sáng đổi 8h-12h -> 9h-13h: lịch trực tháng trước vẫn phải là 8h-12h |
| Calender | nhân viên trực | Provider | ID | Thông tin nhân viên luôn lấy bản mới nhất |
| Notification | khách nào | Customer | ID | Lúc gửi mới hỏi Customer lấy SĐT/mail hiện tại |
| Notification | SĐT/mail đã gửi tới | Customer | copy lúc gửi | Copy lúc đặt lịch thì tin nhắc đi tới số cũ nếu khách vừa đổi. Copy lúc gửi để lịch sử biết tin đã đến đâu |
| Notification | nội dung (giờ hẹn, dịch vụ) | Booking | copy lúc gửi | Thông báo là bằng chứng đã báo gì cho khách lúc đó. Lịch thay đổi sau thì lịch sử vẫn đúng nội dung khách đã nhận |
| Payment | lịch nào | Booking | ID | Biết giao dịch thuộc lượt đặt nào |
| Payment | số tiền | Booking | copy lúc tạo giao dịch | Booking giữ *quyết định* (được hoàn X), Payment giữ *giao dịch thực tế* (đã gửi X sang cổng, mã GD). Đối soát cuối tháng với cổng là so với cái Payment đã gửi đi |
| Provider | tài khoản đăng nhập | Account | ID | Chỉ cần biết tài khoản nào của nhân viên nào. Đổi mật khẩu, khóa tài khoản không liên quan Provider |
| Customer | tài khoản đăng nhập | Account | ID | Tương tự Provider |

### Giao tiep dong bo hay event

(buoi sau)

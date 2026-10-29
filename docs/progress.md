# Tiến độ

> File này được nạp tự động vào mỗi phiên (qua `CLAUDE.md`). Giữ NGẮN GỌN - chỉ trạng thái hiện tại. Lịch sử chi tiết nằm ở `docs/journal/`.

Cập nhật lần cuối: 2026-10-29

## Hiện tại

- **Phase:** 1 - Nền tảng & kiến trúc (bắt đầu 2026-09-23, mốc cũ ~2026-10-14 đã qua). 2026-10-27 người làm chấp nhận kéo dài để đi chậm mà chắc, không đặt mốc cứng mới.
- **Bước đang làm:** 5 - Domain model. Xong `TimeRange` + 7 test. `Booking.java` là **bản nháp Claude viết** (giữ để so sánh). Người làm tự viết `BookingDraft` (vẫn dùng Lombok): 3 hàm hủy `cancelByCustomer` / `cancelByManager` / `cancelBySystem` + 2 hàm private `checkStatusToCancel()`, `markDataCancelled(...)`, hằng số `FREE_CANCEL_BEFORE`, field người hủy (role + ID, hệ thống = `null`). Đã dọn file thừa (`service/`, `domain/dto/`, `Customer`), sửa scope Lombok thành `provided`, build IntelliJ chạy được ([journal 2026-10-29](journal/2026-10-29.md)).
- **Bước tiếp theo (cụ thể):** (1) Hoàn thiện F9 trong `BookingDraft`: tạo exception riêng của domain thay `RuntimeException` (dòng kiểm tra chủ lịch); thêm message cho `IllegalStateException`; `cancelBySystem` đổi sang kiểu chỉ cho phép (`!= PENDING_PAYMENT`); thêm kiểm tra "đã tới giờ hẹn -> chặn" cho khách và quản lý (không đưa vào hàm private chung); thống nhất thứ tự tham số. (2) Phác thảo + viết F10 `complete` (nhân viên bấm; kiểm tra thời gian + trạng thái; lưu ai bấm, lúc nào), rồi `reconstitute`, getter. (3) Viết lại `BookingDraft` không Lombok, dùng static factory, `now` truyền vào -> Claude đánh giá -> viết test theo mẫu `TimeRangeTest` (`mvn test`; `mvn` chưa có trong PATH của shell Claude, 29/10 mới kiểm bằng build IntelliJ).

## Checklist Phase 1

- [x] 1. Setup git: nhánh `skeleton-reference` (skeleton tham khảo) + `main` sạch, push lên GitHub (2026-09-23)
- [x] 2. Đổi default branch trên GitHub sang `main` (người làm tự làm trên web) (2026-09-23)
- [x] 3. Tài liệu bounded context `docs/bounded-contexts.md` (2026-10-04 - đóng theo hạn tài liệu, các câu còn treo hoàn thiện khi code)
- [x] 4. `BookingApplication` - app rỗng chạy được, vào được `/h2-console` (2026-10-06)
- [ ] 5. Domain model: value object (record) + aggregate `Booking` với static factory (`TimeRange` xong 2026-10-07; Booking người làm tự viết + test còn lại)
- [ ] 6. Domain logic: port in/out, domain service, Strategy cho conflict check + unit test JUnit thuần
- [ ] 7. Application: implement use case "tạo booking"
- [ ] 8. Infrastructure: JPA entity + repository adapter, controller + DTO, exception handler, bean config
- [ ] 9. Chạy end-to-end (curl tạo booking, thử trùng lịch -> 409)
- [ ] 10. So sánh với skeleton (`git diff skeleton-reference main -- src/`), ghi khác biệt + lý do vào journal
- [ ] 11. ADR kết thúc phase (VD: ranh giới bounded context) + cập nhật mục "Kiến trúc" trong `CLAUDE.md` theo code thực tế
- [ ] 12. `/kiem-tra` Phase 1 -> cập nhật `docs/competencies.md`

## Câu hỏi / quyết định đang treo

- Đổi tên `cancelBySystem` -> `expire(now)`? (đã chốt tách 3 hàm hủy 2026-10-28; tên do người làm tự quyết)
- F10 (chốt 29/10: giữ đúng yêu cầu, nhân viên bấm hoàn thành): `complete` cho phép từ giờ bắt đầu hay giờ kết thúc? Khách không đến -> tạm để `CONFIRMED` (trạng thái nói sai "chưa diễn ra") -> quyết khi làm F10.
- Gom `cancelledByRole` + `cancelledById` vào 1 record (giữ quy tắc "HỆ THỐNG ⇔ id null")? Người làm đang chọn 2 field rời.
- Phase 1 lấy `duration` từ đâu khi chưa có Service Catalog (stub hay request) -> bước 7.
- Câu 3 Payment (phát sự kiện thay vì gọi thẳng): đã chốt, nhưng người làm mới "hiểu chung chung". 2026-10-26 đã rõ phần "Payment biết tiền, Booking biết lịch, Booking quyết"; cách báo sang (gọi thẳng / sự kiện) làm rõ ở bước 7-8. `/kiem-tra` hỏi lại.
- `setStatus()` public gây lỗi gì? Bỏ qua 5 lần (07/10, 26/10); 26/10 Claude đã đưa tình huống (Lan/Hoa trùng 9h). `/kiem-tra`: người làm tự viết ví dụ code phá quy tắc.
- N5 chống spam: tự hủy có tính không? Người làm đề xuất đo tổng thời gian pending (cần `cancelledAt`); đếm từ booking, không cần bảng riêng -> chốt ở Phase 8, sửa `yeu-cau.md` khi chốt.
- Hoàn tiền: đã rõ Booking quyết **có được hoàn hay không** (hằng số trong Booking, 29/10); Booking trả về/ghi gì để Payment biết mà hoàn -> bước 7-8.
- Tiền về sau khi lịch đã hết hạn giữ slot (`CANCELLED`) -> luồng bù hoàn tiền.
- ID người hủy (đã chốt lưu cả vai trò + ID) và "ai thao tác" của F16: ID hồ sơ theo vai trò hay ID Account? -> Phase 5. (Người tham gia đã chốt 2026-10-26: `customerId` + `providerId`.)
- Lombok cho JPA entity -> quyết ở bước 8 (domain đã chọn Java thuần + record).
- F4 (ca làm, ngày nghỉ): giữ ở Phase 2 hay đưa vào "Chưa cần"?
- Notification có cần `ID booking` không (nội dung đã copy từ Booking lúc gửi)?

## Điểm yếu đang theo dõi

Quan sát qua nhiều buổi (Claude cập nhật khi thấy lặp lại). Dùng để chọn trọng tâm gợi ý và câu hỏi kiểm tra.

- Thiết kế bắt đầu từ bảng DB thay vì từ trách nhiệm/hành vi: coi mỗi bảng là 1 context, gom theo danh từ, gửi dữ liệu vào bảng có sẵn `status`. Bằng chứng: [2026-09-24](journal/2026-09-24.md), [2026-09-24 buổi 2](journal/2026-09-24-2.md), [2026-09-24 buổi 3](journal/2026-09-24-3.md) (thêm `locked` vào cùng field `status`), [2026-09-28](journal/2026-09-28.md), [2026-09-29](journal/2026-09-29.md), [2026-10-02](journal/2026-10-02.md) (kết luận đầu "tách Shift/Calendar" vì thấy 2 bảng), [2026-10-04](journal/2026-10-04.md) (đặt cờ chặn spam ở Account vì "Booking không có trường nào"; nhầm lịch hẹn nằm ở WorkSchedule), [2026-10-06](journal/2026-10-06.md) (lý do "dùng chung 1 field"; "hết hạn = xóa lịch"), [2026-10-26](journal/2026-10-26.md) (`BookingDraft` chỉ có field + Lombok, `Customer` rỗng; "phải thiết kế model trước"). Tiến bộ: 2026-10-26 tự rút ra "thiết kế tính năng trước rồi mới ra hàm". 2026-10-02 tự rút ra "tách bảng không có nghĩa là tách context"; 2026-10-04 tự nhận "đang nói context, chưa nói bảng" và tự đề xuất bảng riêng trong Booking.
- Phép thử ranh giới: nhầm **thao tác dữ liệu** (quản lý bấm trên màn hình) với **thay đổi quy tắc** (dev sửa code); trả lời mâu thuẫn với F14 vừa chốt (đổi giờ ca -> lịch đã xếp cũng đổi). Bằng chứng: [2026-10-02](journal/2026-10-02.md) (bậc 3, "9-11h"), [2026-10-04](journal/2026-10-04.md) (bậc 2, "sửa 2 chỗ", "sửa ca trực tuần này"). Cách tập: trước khi đếm, hỏi "dev có phải sửa dòng code nào không?".
- Lý do chung chung / từ khóa thay vì tình huống cụ thể. Bằng chứng: [2026-09-29](journal/2026-09-29.md) (4 lần), [2026-10-02](journal/2026-10-02.md) ("liên kết chặt hành vi"), [2026-10-04](journal/2026-10-04.md) ("ảnh hưởng độ chính xác dữ liệu", tên "bảng theo dõi đặt lịch"), [2026-10-06](journal/2026-10-06.md) (ôn nhanh: chép điều kiện F9 thay vì lý do thiết kế; lý do chọn B tự mâu thuẫn), [2026-10-07](journal/2026-10-07.md) ("tránh lặp logic" thiếu hậu quả), [2026-10-27](journal/2026-10-27.md) (chọn A vì "không bị tính sai", chưa kèm tình huống), [2026-10-28](journal/2026-10-28.md) ("vì đã thanh toán" - nghe hợp lý nhưng không phải nguyên nhân; "không có điểm chung" chưa liệt kê thử), [2026-10-29](journal/2026-10-29.md) ("toàn luồng đã hoàn thành", "kiểm soát toàn luồng"; nêu 2 dữ kiện đúng nhưng kết luận ngược). Tiến bộ: 2026-10-28 chọn tách 3 hàm hủy kèm hậu quả "quy tắc khách đổi thì 2 bên kia không ảnh hưởng". 2026-10-07 ôn nhanh trả lời bằng tình huống (`reconstitute` lịch cũ), phân tích ID vs vai trò người hủy có lợi/hại cả 2 phía. Cách tập: mỗi lý do phải kèm 1 tình huống "nếu X đổi thì...".
- Nhờ Claude viết/tổng hợp thay vì tự viết -> dễ "hiểu khi nghe" mà chưa tự làm được. Bằng chứng: [2026-09-29](journal/2026-09-29.md), [2026-10-02](journal/2026-10-02.md), [2026-10-04](journal/2026-10-04.md) (kết luận, buổi thứ 3 liên tiếp), [2026-10-07](journal/2026-10-07.md) (lấn sang code: nhờ viết `TimeRange` + test và bản nháp `Booking`; chưa hiểu vai trò record dù đã chốt thiết kế 06/10). Tiến bộ: 2026-10-29 tự viết 3 hàm hủy + 2 hàm private trong `BookingDraft`, sửa qua 3 vòng review; tự nhận "viết code ra rồi làm sạch dễ hơn nghĩ trong đầu" -> cách hợp với người làm: viết nháp trước, rồi rà từng dòng "dùng dữ liệu của ai?". 2026-10-06 tự viết lại lý do chọn B; 2026-10-07 tự viết bản đầu `TimeRange` và tự đề xuất buổi sau viết lại Booking để Claude đánh giá. 2026-10-26 người làm tự nhận: "dạo này dùng agent tạo code nên não bị lỳ", tự viết bị vướng ở bước tính năng -> thiết kế. Cách tập: Claude viết mẫu xong thì người làm tự viết lại bản của mình; trước khi cho agent sinh code, tự viết 3-5 dòng *tính năng -> hàm -> kiểm tra gì*; `/kiem-tra` sẽ hỏi lại.
- Đặt kiểm tra cần dữ liệu ngoài object (DB, object khác) vào trong aggregate; truyền ID của chính object làm tham số hàm của nó. Bằng chứng: [2026-10-27](journal/2026-10-27.md) (3 lần: trùng lịch "trong Booking", "serviceId có trong bảng" trong `createNew`, `cancelBySystem(bookingId)`), [2026-10-29](journal/2026-10-29.md) (`bookingId` lại là tham số của `cancelByCustomer`, tự gỡ ở bậc 1). Tiến bộ: 2026-10-28 ôn nhanh xếp đúng cả 2 kiểm tra (giờ tương lai / chặn N5). 2026-10-29 phân biệt đúng chủ lịch (kiểm tra trong Booking) với role quản lý (ở application, vì role nằm ở Account). Cách tập: trước khi xếp 1 kiểm tra vào class, hỏi "cần dữ liệu ngoài chính object này không?".
- Thói quen layered/anemic của HIS khi tự viết code: Lombok `@Setter`/`@Builder` trên domain, `service/` ngoài cấu trúc hexagonal, DTO trong `domain/`, ID lấy từ client - trái quy ước đã chốt. Bằng chứng: [2026-10-07](journal/2026-10-07.md) (thảo luận anemic), [2026-10-26](journal/2026-10-26.md) (người làm tự nhận "do chưa quen"), [2026-10-29](journal/2026-10-29.md) (`javax.naming.AuthenticationException`, constructor public + `LocalDateTime.now()` trong domain, tên `setDataCanceled`). Cách tập: trước khi tạo file, hỏi "file này thuộc tầng nào, quy tắc nào nó giữ?".
- Gặp nhiều câu hỏi thì trả lời câu dễ, bỏ qua câu khó / câu "vì sao" rồi đi tiếp; muốn bỏ phần tốn công (test). Bằng chứng: [2026-10-06](journal/2026-10-06.md) (4 lần trong 1 buổi), [2026-10-07](journal/2026-10-07.md) (câu `setStatus()` bỏ qua 3 lần; lần 2 muốn bỏ test), [2026-10-26](journal/2026-10-26.md) (`setStatus()` bỏ qua thêm 2 lần; câu "rút ra" ở `/ket-thuc` không trả lời), [2026-10-27](journal/2026-10-27.md) (bỏ phần phụ của câu hỏi 3 lần: "lấy từ đâu", loại exception, giá trị ban đầu - dù mỗi lượt chỉ 1 câu), [2026-10-29](journal/2026-10-29.md) (exception bỏ trống 3 lượt liền; vế "vì sao 404", "Lan biết thêm gì" không trả lời; khuôn hàm private 2 lượt không làm -> Claude điền khuôn có chỗ trống / đổi sang viết code trước thì làm được). Cách tập: Claude hỏi ít câu hơn mỗi lượt; người làm trả lời hết hoặc nói rõ "bỏ qua vì ...".

## Nhật ký các buổi

- 2026-09-23 - [Setup dự án và hệ thống theo dõi](journal/2026-09-23.md)
- 2026-09-23 - [Đổi default branch GitHub sang main](journal/2026-09-23-2.md)
- 2026-09-24 - [Yêu cầu khách hàng và bản nháp bounded context](journal/2026-09-24.md)
- 2026-09-24 - [Ý nghĩa bounded context, trả lời "ai sửa, vì sao sửa"](journal/2026-09-24-2.md)
- 2026-09-24 - [Tách Account khỏi Provider, chốt hủy/hoàn tiền](journal/2026-09-24-3.md)
- 2026-09-28 - [Chốt role ở Account, tách Payment khỏi Booking](journal/2026-09-28.md)
- 2026-09-29 - [Viết lại Payment, bảng tham chiếu ID/copy](journal/2026-09-29.md)
- 2026-10-02 - [Gom Shift + Calendar thành WorkSchedule](journal/2026-10-02.md)
- 2026-10-04 - [Gọi thẳng hay phát sự kiện, đóng bước 3](journal/2026-10-04.md)
- 2026-10-06 - [App chạy được, chốt status Booking, thiết kế value object](journal/2026-10-06.md)
- 2026-10-07 - [TimeRange + test, thiết kế Booking, quy ước ngôn ngữ](journal/2026-10-07.md)
- 2026-10-26 - [Quay lại sau 19 ngày, từ tính năng ra hàm, BookingDraft bản Lombok](journal/2026-10-26.md)
- 2026-10-27 - [Phác thảo F2, F7, F8: tính năng -> hàm -> kiểm tra -> field](journal/2026-10-27.md)
- 2026-10-28 - [F9: bảng ai hủy ở trạng thái nào, chọn tách 3 hàm hủy](journal/2026-10-28.md)
- 2026-10-29 - [F9: 3 hàm hủy chạy được trong BookingDraft, chốt exception và F10](journal/2026-10-29.md)

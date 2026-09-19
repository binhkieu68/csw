# FPT Employee Management Web Service

Bài tập: xây dựng web service quản lý nhân viên cho công ty FPT.

## Công nghệ
- **Java 17 + Spring Boot** — expose web service dạng REST/JSON (endpoint HTTP đơn giản, dễ test, không cần cài Tomcat/GlassFish riêng vì Spring Boot có sẵn embedded server).
- **Spring Data JPA + H2** — lưu dữ liệu nhân viên vào database (mặc định dùng H2 in-memory để chạy ngay không cần cài đặt; có sẵn cấu hình MySQL nếu bạn muốn dùng DB thật, xem `application.properties`).
- **HTML + JavaScript (fetch API)** — trang web đơn giản để test 3 web service methods.

## Cấu trúc project
```
fpt-employee-ws/
├── pom.xml
├── src/main/java/com/fpt/employee/
│   ├── EmployeeApplication.java        # điểm khởi chạy
│   ├── model/Employee.java             # class Employee: id, name, salary
│   ├── repository/EmployeeRepository.java
│   ├── service/EmployeeService.java    # 3 method: getEmployees, addEmployee, updateEmployee
│   └── controller/EmployeeController.java  # web service endpoints (REST)
├── src/main/resources/
│   ├── application.properties          # cấu hình DB (H2 mặc định / MySQL tùy chọn)
│   ├── data.sql                        # dữ liệu mẫu
│   └── static/index.html               # web app để test service
└── README.md
```

## 3 Web service methods
| Yêu cầu đề bài | Endpoint | 
|---|---|
| `getEmployees()` — lấy tất cả nhân viên | `GET /api/employees` |
| `addEmployee(Employee e)` — thêm nhân viên mới | `POST /api/employees` |
| `updateEmployee(Employee e)` — sửa nhân viên | `PUT /api/employees/{id}` |

Employee JSON mẫu:
```json
{ "name": "Nguyen Van A", "salary": 15000000 }
```

## Cách chạy
Yêu cầu: cài **Java 17+** và **Maven** (hoặc dùng IntelliJ/Eclipse mở project Maven trực tiếp).

```bash
cd fpt-employee-ws
mvn spring-boot:run
```

Sau khi chạy, mở trình duyệt:
- Trang test web app: **http://localhost:8080**
- Xem dữ liệu DB trực tiếp (H2 console): **http://localhost:8080/h2-console** (JDBC URL: `jdbc:h2:mem:fptdb`, user: `sa`, password: để trống)

Test bằng Postman/curl:
```bash
# Lấy danh sách
curl http://localhost:8080/api/employees

# Thêm mới
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"Pham Van D","salary":20000000}'

# Cập nhật (id=1)
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Pham Van D","salary":22000000}'
```

## Chuyển sang dùng MySQL thật
Mở `src/main/resources/application.properties`, comment phần H2 lại và bỏ comment phần MySQL, sửa username/password cho đúng với máy bạn. Driver MySQL đã có sẵn trong `pom.xml`.

## Cách đưa lên GitHub
Project này chưa có link GitHub (mình không có quyền truy cập mạng để tạo repo giúp bạn). Làm theo các bước sau để tự đẩy lên:

1. Tạo repo mới trên https://github.com/new (ví dụ đặt tên `fpt-employee-ws`), **không** tick "Add README".
2. Trong terminal, tại thư mục `fpt-employee-ws`:
```bash
git init
git add .
git commit -m "Initial commit: FPT employee management web service"
git branch -M main
git remote add origin https://github.com/<username>/fpt-employee-ws.git
git push -u origin main
```
3. Sau khi push xong, link repo của bạn sẽ có dạng: `https://github.com/<username>/fpt-employee-ws`

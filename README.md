
# Hướng dẫn sử dụng Jenkins trên Docker với Makefile

Repository này chứa một `Makefile` giúp đơn giản hóa quá trình build, chạy, và quản lý container Jenkins trên Docker.

## Yêu cầu

Trước khi sử dụng, hãy đảm bảo rằng bạn đã cài đặt các phần mềm sau trên hệ thống của mình:

- [Docker](https://docs.docker.com/get-docker/)
- [Make](https://www.gnu.org/software/make/)

## Thiết lập và sử dụng

### 1. Clone Repository

Sao chép repository này về máy của bạn:

```bash
git clone https://github.com/khaitq-vnist/demo_ci_cd.git
cd jenkins-docker-makefile
```

### 2. Build Image Docker cho Jenkins

`Makefile` có sẵn các lệnh để build image Docker. Để build image Jenkins, chạy lệnh sau:

```bash
make build
```

Lệnh này sẽ build một Docker image tên là `jenkins-new` với phiên bản `v1` từ Dockerfile có sẵn.

### 3. Chạy Container Jenkins

Để chạy container Jenkins từ image Docker vừa build, sử dụng lệnh:

```bash
make run
```

Lệnh này sẽ thực hiện các bước sau:

- Chạy container Jenkins với tên là `jenkins`.
- Sử dụng mạng Docker có tên `jenkins`.
- Thiết lập các biến môi trường để tương tác với Docker một cách an toàn bằng TLS.
- Map cổng `8080` và `50000` để truy cập giao diện Jenkins và kết nối với agent.
- Mount các volume để lưu trữ dữ liệu Jenkins và chứng chỉ Docker.

### 4. Truy cập Jenkins

Sau khi container đã chạy, bạn có thể truy cập Jenkins tại:

```
http://localhost:8080
```

Sử dụng các cổng sau:

- **8080**: Truy cập giao diện Jenkins
- **50000**: Kết nối Jenkins với các agent

### 5. Thực hiện các lệnh trong container đang chạy

Để mở một shell tương tác bên trong container Jenkins đang chạy, sử dụng lệnh:

```bash
make exec
```

Lệnh này sẽ cho phép bạn thực hiện các câu lệnh trực tiếp bên trong container Jenkins.

### 6. Dọn dẹp container

Để dừng và xóa container Jenkins, sử dụng lệnh:

```bash
make clean
```

Lệnh này sẽ dừng và xóa container Jenkins đang chạy, nhưng sẽ không xóa image Docker.

### 7. Xóa image Docker

Nếu bạn muốn xóa image Docker Jenkins khỏi hệ thống, chạy lệnh sau:

```bash
make remove-image
```

Lệnh này sẽ xóa Docker image `jenkins-new:v1`.

## Thông tin thêm

- **Volumes**:
    - `jenkins_home:/var/jenkins_home`: Lưu trữ dữ liệu của Jenkins để không bị mất khi container dừng hoặc khởi động lại.
    - `docker-certs-jk:/certs/client:ro`: Mount chứng chỉ Docker client để đảm bảo giao tiếp an toàn với Docker.

- **Biến môi trường**:
    - `DOCKER_HOST`: Chỉ định địa chỉ của Docker daemon (`tcp://docker:2376`).
    - `DOCKER_CERT_PATH`: Đường dẫn tới chứng chỉ Docker client (`/certs/client`).
    - `DOCKER_TLS_VERIFY`: Kích hoạt xác thực TLS (`1`).

## Khắc phục sự cố

- Đảm bảo Docker đã được cài đặt và đang chạy.
- Nếu gặp sự cố với cổng, kiểm tra xem các cổng này có đang được sử dụng bởi dịch vụ khác không.
- Kiểm tra cài đặt mạng Docker để tránh xung đột với các mạng khác trên máy của bạn.

## Giấy phép

Dự án này được cấp phép theo Giấy phép MIT.
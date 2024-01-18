package ra.bussiness;

import java.util.Scanner;

public class Book {
    private static int nextId = 1; // Biến này để tăng giá trị cho bookId

    private int bookId; // Id (tự động tăng)
    private String bookName; // Tên sách
    private String author; // Tác giả
    private String descriptions; // Mô tả về sách
    private double importPrice; // Giá nhập
    private double exportPrice; // Giá xuất
    private float interest; // Lợi nhuận
    private boolean bookStatus; // Trạng thái.

    // Phương thức khởi tạo đối tượng không tham số.
    public Book() {
        this.bookId = nextId++; // Đặt id của Sách được tăng lần lượt 1 đơn vị
        this.bookStatus = true; // Trạng thái của Sách mặc định là true
    }

    // Phương thức khởi tạo với các tham số
    public Book(String bookName, String author, String descriptions, double importPrice, double exportPrice) {
        this();
        this.setBookName(bookName); //
        this.setAuthor(author);
        this.setDescriptions(descriptions);
        this.setImportPrice(importPrice);
        this.setExportPrice(exportPrice);
        this.calculateInterest();
    }

    // Getter và Setter cho từng thuộc tính.
    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        if (bookName != null && !bookName.trim().isEmpty()) {
            this.bookName = bookName;
        } else {
            System.out.println("Tên sách không được để trống.");
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        } else {
            System.out.println("Tác giả không được để trống.");
        }
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        if (descriptions != null && !descriptions.trim().isEmpty() && descriptions.length() >= 10) {
            this.descriptions = descriptions;
        } else {
            System.out.println("Mô tả không được để trống và phải có ít nhất 10 ký tự.");
        }
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        if (importPrice > 0) {
            this.importPrice = importPrice;
        } else {
            System.out.println("Giá nhập phải lớn hơn 0.");
        }
    }

    public double getExportPrice() {
        return exportPrice;
    }

    // Phương thức gợi ý để người dùng nhập gía xuất khi lấy được giá nhập
    public void autoDiplayExportPrice() {
        double exportPr = importPrice * 1.2; // Lấy giá xuất mặc định thấp nhất.
        System.out.println("Giá xuất khi = Giá nhập * 1.2 là: " + exportPr); // In ra, hướng dẫn người dùng
    }

    public void setExportPrice(double exportPrice) {
        if (exportPrice > 1.2 * importPrice) {
            this.exportPrice = exportPrice;
            this.calculateInterest();
        } else {
            System.out.println("Giá xuất phải lớn hơn 1.2 lần giá nhập.");
        }
    }

    public float getInterest() {
        return interest;
    }

    // Tính lợi nhuận
    private void calculateInterest() {
        this.interest = (float) (exportPrice - importPrice);
    }

    public boolean getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    // Phương thức Nhập liệu thông tin của sách.
    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên sách: ");
        setBookName(scanner.nextLine());

        System.out.print("Nhập tên tác giả: ");
        setAuthor(scanner.nextLine());

        // Validate cho truừng nhập mô tả sách bằng do-while
        do {
            System.out.print("Nhập mô tả về sách (ít nhất 10 ký tự): ");
            setDescriptions(scanner.nextLine());
        } while (this.descriptions == null);

        System.out.print("Nhập giá nhập: ");
        setImportPrice(scanner.nextDouble());

        autoDiplayExportPrice(); // Hiển thị 1.2 lần của giá nhập, để người quản lí dễ dàng biết được phải nhập bao nhiêu.

        // Validate cho giá xuất bằng do while
        do {
            System.out.print("Nhập giá xuất (phải lớn hơn 1.2 lần giá nhập): ");
            setExportPrice(scanner.nextDouble());
        } while (this.exportPrice < 1.2 * importPrice);
    }

    // Phương thức hiển thị sách.
    public void displayData() {
        System.out.println("Mã sách: " + getBookId());
        System.out.println("Tên sách: " + getBookName());
        System.out.println("Tác giả: " + getAuthor());
        System.out.println("Mô tả: " + getDescriptions());
        System.out.println("Giá nhập: " + getImportPrice());
        System.out.println("Giá xuất: " + getExportPrice());
        System.out.println("Lợi nhuận: " + getInterest());
        System.out.println("Trạng thái sách: " + (getBookStatus() ? "Còn hàng" : "Hết hàng"));
    }
}


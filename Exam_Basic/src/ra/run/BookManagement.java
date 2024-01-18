package ra.run;

import ra.bussiness.Book;

import java.util.Arrays;
import java.util.Scanner;

public class BookManagement {
    private static final int MAX_BOOKS = 100;
    private Book[] books;
    private int numberOfBooks;

    public BookManagement() {
        this.books = new Book[MAX_BOOKS];
        this.numberOfBooks = 0;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Chọn chức năng (1-7): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Tránh nuốt dòng(cho nó nuốt luôn Enter)

            switch (choice) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    sortBooksByInterest();
                    System.out.println("Đây là Danh sách mới sau khi đã được sắp xếp.");
                    displayAllBooks();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    searchBooks();
                    break;
                case 6:
                    updateBook();
                    break;
                case 7:
                    System.out.println("Đã thoát chương trình."); // Thoát khi choice = 7
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 7);
    }

    // Phương thức hiển thị Menu lựa chọn
    private void displayMenu() {
        System.out.println("\n****************JAVA-HACKATHON-05-BASIC-MENU***************");
        System.out.println("1. Nhập thông tin sách mới");
        System.out.println("2. Hiển thị thông tin tất cả sách");
        System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
        System.out.println("4. Xóa sách theo mã sách");
        System.out.println("5. Tìm kiếm sách theo tên hoặc mô tả");
        System.out.println("6. Thay đổi thông tin sách");
        System.out.println("7. Thoát");
    }

    // Phương thức thêm mới Sách.
    private void addBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng sách cần thêm mới: ");
        int numberOfNewBooks = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        if (numberOfBooks + numberOfNewBooks > MAX_BOOKS) {
            System.out.println("Danh sách sách đã đầy, không thể thêm mới.");
            return;
        }

        for (int i = 0; i < numberOfNewBooks; i++) {
            System.out.println("\nNhập thông tin cho sách thứ " + (i + 1) + ":");
            Book newBook = new Book();
            newBook.inputData();
            books[numberOfBooks++] = newBook;
        }

        System.out.println("Thêm mới sách thành công.");
    }

    // Phương thức hiển thị Tất cả Sách có trong Mảng.
    private void displayAllBooks() {
        if (numberOfBooks == 0) {
            System.out.println("Danh sách Sách trống.");
        } else {
            System.out.println("\n===== Danh sách Tất cả Sách =====");
            for (int i = 0; i < numberOfBooks; i++) {
                System.out.println("Sách thứ " + (i + 1) + ":");
                books[i].displayData();
                System.out.println("------------------------------");
            }
        }
    }

    // Phương thức tìm Sắp xếp danh sách theo lợi nhuận tăng dần
    private void sortBooksByInterest() {
        if (numberOfBooks > 1) {
            Arrays.sort(books, 0, numberOfBooks, (b1, b2) -> Float.compare(b1.getInterest(), b2.getInterest()));
            System.out.println("Sắp xếp sách theo lợi nhuận thành công.");
        } else {
            System.out.println("Không đủ sách để sắp xếp.");
        }
    }

    // Phương thức xoá
    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã sách cần xóa: ");
        int bookIdToDelete = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        int index = findBookIndexById(bookIdToDelete);
        if (index != -1) {
            // Hiển thị phần tử cần xoá
            System.out.println("Đây là thông tin Sách cần xoá:");
            books[index].displayData();
            System.arraycopy(books, index + 1, books, index, numberOfBooks - index - 1);
            numberOfBooks--;
            System.out.println("Xóa sách thành công.");
        } else {
            System.out.println("Không tìm thấy sách với mã sách " + bookIdToDelete);
        }
    }

    // Phương thức tìm kiếm cho ra kết quả
    private void searchBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập chuỗi tìm kiếm (tên sách hoặc mô tả): ");
        String searchKeyword = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (int i = 0; i < numberOfBooks; i++) {
            Book book = books[i];
            if (book.getBookName().toLowerCase().contains(searchKeyword) ||
                    book.getDescriptions().toLowerCase().contains(searchKeyword)) {
                System.out.println("Đây là sách đã tìm được:");
                book.displayData();
                found = true;
                System.out.println("------------------------------");
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sách với từ khóa \"" + searchKeyword + "\"");
        }
    }

    private void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã sách cần cập nhật thông tin: ");
        int bookIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        int index = findBookIndexById(bookIdToUpdate);
        if (index != -1) {
            System.out.println("Nhập thông tin mới cho sách:");
            books[index].inputData();
            System.out.println("Cập nhật thông tin sách thành công.");
        } else {
            System.out.println("Không tìm thấy sách với mã sách " + bookIdToUpdate);
        }
    }

    // Phương thức tìm kiếm Sách theo Id
    private int findBookIndexById(int bookId) {
        for (int i = 0; i < numberOfBooks; i++) {
            if (books[i].getBookId() == bookId) {
                return i;
            }
        }
        return -1; // Trả về -1 khi không tìm thấy.
    }

    // Phương thức main để chạy chương trình.
    public static void main(String[] args) {
        // Khởi tạo đối tượng từ class BookManagement.
        BookManagement bookManagement = new BookManagement();

        // Gọi phương thức run() để người dùng chọn Menu
        bookManagement.run();
    }
}



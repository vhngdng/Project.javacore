package View;

import java.time.LocalDateTime;
import java.util.Scanner;
import Controller.ControllerBooking;
import Model.Booking;
import util.DateTimeUtil;

public class BookingView {
    private Scanner scanner;

    public static void displaySelection() {
        System.out.println("==========VCB Digibank==========");
        System.out.println("============Welcome=============");
        System.out.println("");

    }

    public static void displayCity() {
        System.out.println("chọn tỉnh/ thành phố");
        System.out.println("[1] Hà Nội");
        System.out.println("[2] Thành phố Hồ Chí Minh");
        System.out.println("[3] Đà Nẵng");
    }

    public boolean display() {
        boolean isValid = false;
        String address = null;
        scanner = new Scanner(System.in);
        displaySelection();
        System.out.println("Nhập tên");
        String name  = scanner.nextLine();
        System.out.println("Nhập email");
        String email = scanner.nextLine();
        System.out.println("Nhập số điện thoại");
        int phoneNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Chọn ngày hẹn: (dd-MM-yyyy-HH:mm)");
        String dateTime = scanner.nextLine();
        LocalDateTime date = DateTimeUtil.convertStringToLocalDate(dateTime);
        displayCity();
        int numSelect = scanner.nextInt();
        while (true) {
            switch (numSelect) {
                case 1: {
                    ControllerBooking.showHaNoiAddressBranch();
                    System.out.println("Chọn chi nhánh");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    address = ControllerBooking.addHaNoiBranch(num);
                    isValid = true;
                    break;
                }
                case 2: {
                    ControllerBooking.showHoChiMinhAddressBranch();
                    System.out.println("Chọn chi nhánh");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    address = ControllerBooking.addHoChiMinhBranch(num);
                    isValid = true;
                    break;
                }
                case 3: {
                    ControllerBooking.showDaNangAddressBranch();
                    System.out.println("Chọn chi nhánh");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    address = ControllerBooking.addDaNangBranch(num);
                    isValid = true;
                    break;
                }
                default:
                    break;
            }
            if (isValid == true) {
                break;
            }
        }

        Booking booking = new Booking(name, address, email, phoneNumber, date);
        ControllerBooking.registerBooking(booking);

        return false;
    }

    public void displayHaNoiBranch() {
        System.out.println("[1] Ba Đình");
        System.out.println("[2] Bắc Từ Liêm");
        System.out.println("[3] Cầu Giấy");
        System.out.println("[4] Hà Đông");
    }

    public void displayHoChiMinhBranch() {
        System.out.println("[1] Quận 1");
        System.out.println("[2] Quận 10");
        System.out.println("[3] Quận 11");
        System.out.println("[4] Quận 12");
        System.out.println("[5] Quận 3");
        System.out.println("[6] Quận 7");
    }

    public void displayDaNangBranch() {
        System.out.println("[1] Quận Cẩm Lệ");
        System.out.println("[2] Quận Hải Châu");
        System.out.println("[3] Quận Hòa Vang");
        System.out.println("[4] Quận Liên Chiều");
    }


}

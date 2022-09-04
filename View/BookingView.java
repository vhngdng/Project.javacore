package View;

import java.time.LocalDateTime;
import java.util.Scanner;
import Controller.ControllerBooking;
import Controller.ControllerUser;
import Model.Booking;
import Model.User;
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
        ControllerUser controllerUser = new ControllerUser();
        boolean isValid = false;
        String address = null;
        int num = 0;
        int i = 0;
        int j = 0;
        UserView userView = new UserView();
        scanner = new Scanner(System.in);
        displaySelection();
        System.out.println("Nhập tên");
        String name = scanner.nextLine();
        System.out.println("Nhập email");
        String email = "";
        while (true) {
            email = scanner.nextLine();
            if (User.emailRegex(email) == true) {
                break;
            }else{
                System.out.println("email không phù hợp, hãy nhập lại");
                i++;
            }
            if (i == 3) {
                controllerUser.displayMenuView();
            }
        }
        
        int phoneNumber = 0;
        while (true) {
            System.out.println("Nhập số điện thoại");
            String phoneNumberString = scanner.nextLine();
            try {
                phoneNumber = Integer.valueOf(phoneNumberString);
                if (User.phoneNumberRegex(phoneNumberString) == true) {
                    break;
                }else{
                    j++;
                }
            } catch (Exception e) {
                System.out.println("Số điện thoại không đúng");
                
            }
            if (j == 3) {
                controllerUser.displayMenuView();
            }

        }
        System.out.println("Chọn ngày hẹn: (dd-MM-yyyy-HH:mm)");
        System.out.println("Chọn khung giờ trước 18:00");
        LocalDateTime date = LocalDateTime.now();
        while (true) {
            String dateTime = scanner.nextLine();
            try {
                date = DateTimeUtil.convertStringToLocalDate(dateTime);
                if (date.isAfter(LocalDateTime.now()) == true && date.getHour() < 18) {
                    break;
                } else {
                    System.out.println("Thời gian không phù hợp, hãy nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Ngày hẹn không phù hợp, xin hãy nhập lại: ");
            }
            
        }
        displayCity();
        int numSelect = 0;
        while (true) {
            numSelect = userView.insertNumber(numSelect);
            switch (numSelect) {
                case 1: {
                    ControllerBooking.showHaNoiAddressBranch();
                    System.out.println("Chọn chi nhánh");
                    num = userView.insertNumber(num);
                    address = ControllerBooking.addHaNoiBranch(num);
                    isValid = true;
                    break;
                }
                case 2: {
                    ControllerBooking.showHoChiMinhAddressBranch();
                    System.out.println("Chọn chi nhánh");
                    num = userView.insertNumber(num);
                    address = ControllerBooking.addHoChiMinhBranch(num);
                    isValid = true;
                    break;
                }
                case 3: {
                    ControllerBooking.showDaNangAddressBranch();
                    System.out.println("Chọn chi nhánh");
                    num = userView.insertNumber(num);
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

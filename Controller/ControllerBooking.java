package Controller;

import Model.Booking;
import View.BookingView;
import View.UserView;

public class ControllerBooking {
    public static void chooseAddress(int numSelect) {

    }

    public static void showHaNoiAddressBranch() {
        BookingView bookingView = new BookingView();
        bookingView.displayHaNoiBranch();

    }

    public static void showHoChiMinhAddressBranch() {
        BookingView bookingView = new BookingView();
        bookingView.displayHoChiMinhBranch();
    }

    public static void showDaNangAddressBranch() {
        BookingView bookingView = new BookingView();
        bookingView.displayDaNangBranch();

    }

    public static String addHaNoiBranch(int num) {
        String address = null;
        boolean isValid = false;
        while (true) {
            switch (num) {
                case 1: {
                    address = "Ba Đình";
                    isValid = true;
                    break;
                }
                case 2: {
                    address = "Bắc Từ Liêm";
                    isValid = true;
                    break;
                }
                case 3: {
                    address = "Cầu Giấy";
                    isValid = true;
                    break;
                }
                case 4: {
                    address = "Hà Đông";
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
        return address;
    }

    public static String addDaNangBranch(int num) {
        String address = null;
        boolean isValid = false;
        while (true) {
            switch (num) {
                case 1: {
                    address = "Quận Cẩm Lệ";
                    isValid = true;
                    break;
                }
                case 2: {
                    address = "Quận Hải Châu";
                    isValid = true;
                    break;
                }
                case 3: {
                    address = "Quận Hòa Vang";
                    isValid = true;
                    break;
                }
                case 4: {
                    address = "Quận Liên Chiều";
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
        return address;
    }

    public static String addHoChiMinhBranch(int num) {
        String address = null;
        boolean isValid = false;
        while (true) {
            switch (num) {
                case 1: {
                    address = "Quận 1";
                    isValid = true;
                    break;
                }
                case 2: {
                    address = "Quận 10";
                    isValid = true;
                    break;
                }
                case 3: {
                    address = "Quận 11";
                    isValid = true;
                    break;
                }
                case 4: {
                    address = "Quận 12";
                    isValid = true;
                    break;
                }
                case 5: {
                    address = "Quận 12";
                    isValid = true;
                    break;
                }
                case 6: {
                    address = "Quận 3";
                    isValid = true;
                    break;
                }
                case 7: {
                    address = "Quận 7";
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
        return address;
    }

    public static void registerBooking(Booking booking) {
        
        System.out.println("thông tin cuộc hẹn: " + booking.toString());
    }

    public static void showBookingList() {
        Booking.showList();
    }


}

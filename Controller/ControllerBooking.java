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
                    return address;
                }
                case 2: {
                    address = "Bắc Từ Liêm";
                    return address;
                }
                case 3: {
                    address = "Cầu Giấy";
                    return address;
                }
                case 4: {
                    address = "Hà Đông";
                    return address;
                }
                default:
                    break;
            }
        }

    }

    public static String addDaNangBranch(int num) {
        String address = null;
        while (true) {
            switch (num) {
                case 1: {
                    address = "Quận Cẩm Lệ";
                    return address;
                }
                case 2: {
                    address = "Quận Hải Châu";
                    return address;
                }
                case 3: {
                    address = "Quận Hòa Vang";
                    return address;
                }
                case 4: {
                    address = "Quận Liên Chiều";
                    return address;
                }
                default:
                    break;
            }

        }

    }

    public static String addHoChiMinhBranch(int num) {
        String address = null;

        while (true) {
            switch (num) {
                case 1: {
                    address = "Quận 1";
                    return address;
                }
                case 2: {
                    address = "Quận 10";
                    return address;
                }
                case 3: {
                    address = "Quận 11";
                    return address;
                }
                case 4: {
                    address = "Quận 12";
                    return address;
                }
                case 5: {
                    address = "Quận 12";
                    return address;
                }
                case 6: {
                    address = "Quận 3";
                    return address;
                }
                case 7: {
                    address = "Quận 7";
                    return address;
                }

                default:
                    break;
            }

        }
       
    }

    public static void registerBooking(Booking booking) {
        
        System.out.println("thông tin cuộc hẹn: " + booking.toString());
    }

    public static void showBookingList() {
        Booking.showList();
    }


}

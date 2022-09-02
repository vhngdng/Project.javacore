package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private String name;
    private String address;
    private int phoneNumber;
    private LocalDateTime date;
    private String email;
    private static List<Booking> bList = new ArrayList<>();

    public Booking(String name, String address, String email, int phoneNumber, LocalDateTime date) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.email = email;
    }

    public static void addBooking(Booking booking) {
        bList.add(booking);
    }

    public static void showList() {
        for (Booking listBooking : bList) {
            System.out.println(listBooking);
        }
    }

    @Override
    public String toString() {
        return "Booking\naddress = " + address + ", date = " + date.withNano(0) + ", name = " + name + ", phoneNumber = "
                + phoneNumber + ", email = " + email;
    }
}

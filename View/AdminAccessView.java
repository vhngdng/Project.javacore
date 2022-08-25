package View;

import Model.Admin;

public class AdminAccessView {
    public static Admin admin;
    public AdminAccessView (Admin admin) {
        this.admin = admin;
    }

    public static void display() {
        System.out.println("");
        System.out.println("Chao mung admin cua vcBank");
        System.out.println("==========VCB Digibank==========");
        System.out.println("==========AdminAccess===========");
        return;
    }
}

package View;

import java.util.Scanner;

import Controller.ControllerSaving;
import Controller.ControllerUser;
import Model.SavingAccount;

public class SavingView {
    private Scanner scanner;

    public void displaySavingView() {
        boolean isQuit = false;
        double savingBalance = 0;
        scanner = new Scanner(System.in);
        UserView userView = new UserView();
        System.out.println(" mời bạn nhập số tiền gửi ");
        while (true) {
            String savingBalanceString = scanner.nextLine();
            try {
                savingBalance = Double.parseDouble(savingBalanceString);
                break;
            } catch (Exception e) {
                System.out.println("Wrong data type");
                System.out.println("Insert your saving money again");
            }
        }
        boolean isValid = ControllerSaving.checkSavingBalance(savingBalance);
        if (isValid == true) {
            System.out.println("chọn kì hạn muốn gửi : 3 , 6, 9 , 12 ");
            while (true) {
                int monthlyDeposit = 0;
                monthlyDeposit = userView.insertNumber(monthlyDeposit);
                switch (monthlyDeposit) {
                    case 3, 6, 9, 12: {
                        ControllerSaving controllerSaving = new ControllerSaving();
                        SavingAccount savingAccount = controllerSaving.saving(savingBalance,
                                monthlyDeposit);
                        controllerSaving.savingsInformation(savingAccount);
                        isQuit = true;
                        break;
                    }
                    default:
                        userView.displayWrongSelection();
                        break;
                }
                if (isQuit = true) {
                    break;
                }
            }
        }
    }

    public void displayInformationSaving(SavingAccount savingAccount) {
        scanner = new Scanner(System.in);
        int number = 0;
        System.out.println(" Số tiền gửi  là :" + savingAccount.getSavingBalence() + "VND");
        System.out.println(" Số tháng gửi là :" + savingAccount.getMonthlyDeposit());
        System.out.println(" Lãi Xuất :" + savingAccount.getSavingInterest());
        System.out.println(" Ngày gửi :" + savingAccount.getSentDate());
        System.out.println(" Tổng số tiền lãi :"
                + savingAccount.getSavingBalence() * savingAccount.getSavingInterest() / 100 + "VND");
        System.out.println(" Bạn có đồng : ");
        System.out.println(" [1]   Đồng ý \n [2]   Từ chối  ");
        
        while (true) {
            String numberString = scanner.nextLine();
            try {
                number = Integer.valueOf(numberString);
                
            } catch (Exception e) {
                System.out.println("Wrong data type");
            }
            switch (number) {
                case 1: {
                    int balance = ControllerSaving.savingProcess(savingAccount);
                    System.out.println("Bạn đã gửi tiết kiệm thành công");
                    System.out.println("Số dư của bạn là: " + balance + "VND");
                    return;
                }
                case 2: {
                    System.out.println("bạn đã từ chối gửi tiết kiệm");
                    System.out.println(" Thank you for your concern");
                    ControllerUser.displayUserView();
                    return;
                }
                default: 
                break;
            }
        }
    }

    public void displayWrongAmount() {
        System.out.println("số tiền gửi không thể lớn hơn số dư tài khoản ");
    }
}

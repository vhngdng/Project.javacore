package Model;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Scanner;



public class SavingAccount {

    private static final double[] SavingInterestRate = { 3.1, 3.7, 4.1, 4.5, 4.8 };
    private double savingBalence;// số tiền gửi
    private LocalDate sentDate; // ngày gửi
    private double savingInterest; // lãi xuất
    private double monthlyDeposit; // sô tháng

    public SavingAccount(double savingBalence, LocalDate sentDate, double savingInterest, double monthlyDeposit) {
        this.savingBalence = savingBalence;
        this.sentDate = sentDate;
        this.savingInterest = savingInterest;
        this.monthlyDeposit = monthlyDeposit;
    }

    public static double getSavingInterestRate(int month) {
        return SavingInterestRate[month / 3];
    }

    public double getSavingBalence() {
        return savingBalence;
    }

    public void setSavingBalence(double savingBalence) {
        this.savingBalence = savingBalence;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public double getSavingInterest() {
        return savingInterest;
    }

    public void setSavingInterest(double savingInterest) {
        this.savingInterest = savingInterest;
    }

    public double getMonthlyDeposit() {
        return monthlyDeposit;
    }

    public void setMonthlyDeposit(double monthlyDeposit) {
        this.monthlyDeposit = monthlyDeposit;
    }


}

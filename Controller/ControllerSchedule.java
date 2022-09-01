package Controller;


import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONObject;

import Model.Transaction;
import Model.TransactionRepository;
import Model.User;
import Model.UserRepository;
import View.AdminAccessView;

import util.DateTimeUtil;


public class ControllerSchedule {
    public static void scheduleOfPayment(double yearlyInterestRate, int termOfBorrowing, int moneyToBorrow){
        double monthlyInterestRate = yearlyInterestRate/12; //Monthly interest rate
        int principal = moneyToBorrow;


        System.out.println("\n\n\t Loan Schedule\n\n" +
                "Principle Amount: \t" + principal +
                "\nAnnual Interest Rate : \t" + yearlyInterestRate  +
                "\nTerm (Months): \t" + termOfBorrowing);

        System.out.printf("%-30s %-30s %-30s %-30s %-30s\n","Payment Term No.","Remaining Principal","Principal Payment","Interest Payment","Monthly Payment");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s\n",
                0,principal," "," "," ");
        for (int i = 1; i <= termOfBorrowing; i++) {
            double principalPayment = principal / termOfBorrowing;
            double remainingPrincipal = principal - principalPayment*i;
            double interestPayment = (remainingPrincipal+principalPayment) * monthlyInterestRate;
            double monthlyPayment = principalPayment + interestPayment;
            if (i==termOfBorrowing){monthlyPayment = principalPayment + interestPayment +remainingPrincipal;}
            System.out.printf("%-30s %-30s %-30s %-30s %-30s\n",
                    i,remainingPrincipal,principalPayment,interestPayment,monthlyPayment);
        }

    }
}
package Controller;

import java.time.Duration;
import java.time.LocalDateTime;
import Model.BorrowingTransaction;
import Model.TransactionRepository;
import Model.User;
import View.UserView;

public class ControllerSchedule {
    // details of loan
    public static void scheduleOfPayment(double yearlyInterestRate, int termOfBorrowing, int moneyToBorrow) {
        double monthlyInterestRate = yearlyInterestRate / 12; // Monthly interest rate
        int principal = moneyToBorrow;

        System.out.println("\n\n\t Loan Schedule\n\n" +
                "Principle Amount: \t" + principal +
                "\nAnnual Interest Rate : \t" + yearlyInterestRate +
                "\nTerm (Months): \t" + termOfBorrowing);

        System.out.printf("%-30s %-30s %-30s %-30s %-30s\n", "Payment Term No.", "Remaining Principal",
                "Principal Payment", "Interest Payment", "Monthly Payment");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s\n", 0, principal, " ", " ", " ");
        for (int i = 1; i <= termOfBorrowing; i++) {
            double principalPayment = principal / termOfBorrowing;          
            double remainingPrincipal = principal - principalPayment * i;
            double interestPayment = (remainingPrincipal + principalPayment) * monthlyInterestRate;
            double monthlyPayment = principalPayment + interestPayment;
            if (i == termOfBorrowing) {
                monthlyPayment = principalPayment + interestPayment + remainingPrincipal;
            }
            System.out.printf("%-30s %-30s %-30s %-30s %-30s\n",
                    i, remainingPrincipal, principalPayment, interestPayment, monthlyPayment);
        }

    }

    public static void checkTermRead(int moneyToBorrow) {
            BorrowingTransaction borrowingTransaction = new BorrowingTransaction(User.getUser().getCurrentAccount(),
                    moneyToBorrow, LocalDateTime.now());
            ControllerTransaction.moneyDisbursement(borrowingTransaction, User.getUser());
            ControllerUser.displayUserView();

    }
    // show the term and calculate the expire date
    public static void continueAcceptingInterest(int termOfBorrowing, int moneyToBorrow) {
        double yearlyInterestRate = 0.18;
        double timeToExpiredDateInDays = Duration
                .between(LocalDateTime.now(), User.getUser().getExpiredDate())          // duration before expired date
                .toDays();
        System.out.println(timeToExpiredDateInDays);
        double timeToExpiredDateInMonths = timeToExpiredDateInDays / 30;
        System.out.println("Time to Expired Date In Months: " + timeToExpiredDateInMonths);
        if (termOfBorrowing < timeToExpiredDateInMonths) {                              // expired date is valid
            UserView userView = new UserView();
            userView.scheduleOfPayment();                                               // details of interest
            ControllerSchedule.scheduleOfPayment(yearlyInterestRate, termOfBorrowing,    // show details of loan 
                    moneyToBorrow);
            userView.termAndCondition(moneyToBorrow);                                   

        }else {
            System.out.println("You cannot loan the money, your card is expired");
            ControllerUser.displayUserView();
        }
    }

    // check money to borrow
    public boolean checkMoneyToBorrow(int moneyToBorrow, int facility) {
        if (moneyToBorrow < facility) {
            return true;
        } else {
            return false;
        }
    }
    // print Borrowing transaction
    public static void showResultBorrowingTransaction(int id, int currentAccount) {
        TransactionRepository transactionRepository = new TransactionRepository();
        BorrowingTransaction borrowingTransaction = transactionRepository.getBorrowingTransById(id);
        System.out.println(borrowingTransaction.toString());

    }

}
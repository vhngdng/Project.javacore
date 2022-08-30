package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Model.Transaction;
import Model.TransactionRepository;
import Model.User;
import Model.UserRepository;
import View.AdminAccessView;
import View.UserView;
import util.DateTimeUtil;

public class ControllerTransaction {
    private static ControllerTransaction controllerTransaction;

    public static int transferMoney(JSONObject moneyTransactionJson) {
        Transaction transaction = ControllerTransaction.convertJsonObjectToTransaction(moneyTransactionJson);
        TransactionRepository.addTransaction(transaction);
        //chuyen tien
        return UserRepository.transferMoney(transaction);
    }

    public static Transaction convertJsonObjectToTransaction(JSONObject jsonObject) {
        int money = Integer.valueOf(jsonObject.getString("moneyTransfer").toString());
        int beneficiaryCurrentAccount = Integer.valueOf(jsonObject.getString("beneficiaryCurrentAccount").toString());
        int senderCurrentAccount = Integer.valueOf(jsonObject.getString("senderCurrentAccount").toString());
        LocalDateTime dateTimeTransaction = DateTimeUtil
                .convertStringToLocalDate(jsonObject.get("dateTimeSendingTransaction").toString());
        Transaction transaction = new Transaction(senderCurrentAccount, beneficiaryCurrentAccount, money,
                dateTimeTransaction);
        return transaction;
    }

    public void transactionHistory() {
        
        System.out.println(User.getUser().getTransactionsOfUser().toString());
        ControllerUser.finishLine();
        ControllerUser.displayUserView();
        
    }

    public void getAllTransactionDate () {

    }

    public static void transactionDayList (int num) {
        TransactionRepository.transactionDateShow(num);
        AdminAccessView.display();
    }

    public static void transactionMonthList (int num) {
        TransactionRepository.transactionMonthShow(num);
        AdminAccessView.display();
    }

    public static void transactionYearList (int num) {
        TransactionRepository.transactionYearShow(num);
        AdminAccessView.display();
    }

    public static void transactionShowAll() {
        System.out.println(TransactionRepository.getTransactionList().toString());
    }
}

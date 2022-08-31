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

public class ControllerTransaction {
    private AdminAccessView adminAccessView = new AdminAccessView();
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
        ControllerUser controllerUser = new ControllerUser();
        List<Transaction>transactionHistory = TransactionRepository.getTransactionHistory();
        System.out.println(transactionHistory.toString());
        ControllerUser.finishLine();
        controllerUser.displayUserView();
        
    }

    public void getAllTransactionDate () {

    }

    public void transactionDayList (int num) {
        TransactionRepository.transactionDateShow(num);
        adminAccessView.display();
    }

    public void transactionMonthList (int num) {
        TransactionRepository.transactionMonthShow(num);
        adminAccessView.display();
    }

    public void transactionYearList (int num) {
        TransactionRepository.transactionYearShow(num);
        adminAccessView.display();
    }

    public static void transactionShowAll() {
        System.out.println(TransactionRepository.getTransactionList().toString());
    }

    public void showTransactionDetail() {

    }
}

package Controller;

import java.time.LocalDateTime;
import java.util.List;

import Model.*;
import org.json.JSONObject;

import View.AdminAccessView;

import util.DateTimeUtil;

public class ControllerTransaction {
    private AdminAccessView adminAccessView = new AdminAccessView();

    public static int transferMoney(JSONObject moneyTransactionJson) {
        Transaction transaction = ControllerTransaction.convertJsonObjectToTransaction(moneyTransactionJson);
        TransactionRepository.addTransaction(transaction);

        // chuyen tien
        return UserRepository.transferMoney(transaction);
    }

    // convert json to trans
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

    // convert trans to json
    public static JSONObject convertTransactionToJsonObject(Transaction transaction) {
        JSONObject moneyTransactionJson = new JSONObject();
        moneyTransactionJson.put("moneyTransfer", transaction.getMoney());
        moneyTransactionJson.put("beneficiaryCurrentAccount",
                String.valueOf(transaction.getBeneficiaryCurrentAccount()));

        // Date Time Transaction
        String dateTimeSendingTransaction = DateTimeUtil.convertLocalDateToString(LocalDateTime.now());
        moneyTransactionJson.put("dateTimeSendingTransaction", dateTimeSendingTransaction);
        moneyTransactionJson.put("senderCurrentAccount", String.valueOf(User.getUser().getCurrentAccount()));

        return moneyTransactionJson;
    }

    public void transactionHistory() {
        List<Transaction> transactionHistory = TransactionRepository.getTransactionHistory();
        System.out.println(transactionHistory.toString());
        ControllerUser.finishLine();
        ControllerUser.displayUserView();

    }

    public void getAllTransactionDate() {

    }

    public void transactionDayList(int num) {
        TransactionRepository.transactionDateShow(num);
        adminAccessView.display();
    }

    public void transactionMonthList(int num) {
        TransactionRepository.transactionMonthShow(num);
        adminAccessView.display();
    }

    public void transactionYearList(int num) {
        TransactionRepository.transactionYearShow(num);
        adminAccessView.display();
    }

    public static void transactionShowAll() {
        System.out.println(TransactionRepository.getTransactionList().toString());
    }


    public static int moneyDisbursement(BorrowingTransaction borrowingTransaction, User borrowingUser) {
        // online borrowing
        return UserRepository.onlineBorrowing(borrowingTransaction, borrowingUser);
    }
}

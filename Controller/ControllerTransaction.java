package Controller;

import java.time.LocalDate;

import org.json.JSONObject;

import Model.Transaction;
import Model.TransactionRepository;
import Model.UserRepository;
import util.DateTimeUtil;

public class ControllerTransaction {
    private static ControllerTransaction controllerTransaction;

    public static int transferMoney(JSONObject moneyTransactionJson) {
        int money = 0;
        Transaction transaction = ControllerTransaction.convertJsonObjectToTransaction(moneyTransactionJson);
        TransactionRepository.addTransaction(transaction);
        //chuyen tien
        return UserRepository.transferMoney(transaction);
    }

    public static Transaction convertJsonObjectToTransaction(JSONObject jsonObject) {
        int money = Integer.valueOf(jsonObject.getString("moneyTransfer").toString());
        int beneficiaryCurrentAccount = Integer.valueOf(jsonObject.getString("beneficiaryCurrentAccount").toString());
        int senderCurrentAccount = Integer.valueOf(jsonObject.getString("senderCurrentAccount").toString());
        LocalDate dateTimeTransaction = DateTimeUtil
                .convertStringToLocalDate(jsonObject.get("dateTimeSendingTransaction").toString());
        Transaction transaction = new Transaction(senderCurrentAccount, beneficiaryCurrentAccount, money,
                dateTimeTransaction);
        return transaction;
    }
}

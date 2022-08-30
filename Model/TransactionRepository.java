package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.TreeNode;

import Model.Transaction;
import Model.User;

public class TransactionRepository {
    private static String numberVerify;
    private static int transactionCount = 0;
    private static List<Transaction> listTransaction = new ArrayList<>();

    public static int addTransaction(Transaction transaction) {
        transactionCount += 1;
        listTransaction.add(transaction);
        return transactionCount;
    }

    // public static void addNewTransaction(Transaction transactionSending,
    // Transaction transactionReceiving) {
    // transactionSending.setTransactionType(TRANSACTIONTYPE.SENDER);
    // transactionReceiving.setTransactionType(TRANSACTIONTYPE.RECEIVER);

    // addTransaction(transactionSending);
    // addTransaction(transactionReceiving);

    // }

    public static int getTransactionById(int id) {
        return 0;
    }

    public static boolean checkCodeVerification(String codeCheck, int idOfThisTransaction) {
        return true;
    }

    public static void addTransactionUser(int currentAccount) {
        User user = User.getUser();
        for (Transaction transList : listTransaction) {
            if (transList != null && (transList.getBeneficiaryCurrentAccount() == currentAccount
                    || transList.getSenderCurrentAccount() == currentAccount)) {
                user.getTransactionsOfUser().add(transList);
            }
        }
    }

    public static List getTransactionList() {
        return listTransaction;
    }

    public void printAllListTransaction() {
        System.out.println(Arrays.toString(listTransaction.toArray()));
    }

    public static void transactionDateShow(int num) {
        for (Transaction listTrans : listTransaction) {
            if (num == listTrans.getDateTimeTransaction().getDayOfMonth()) {
                System.out.println(listTrans.toString());
            }
        }     
    }

    public static void transactionMonthShow(int num) {
        for (Transaction listTrans : listTransaction) {
            if (num == listTrans.getDateTimeTransaction().getMonthValue()) {
                System.out.println(listTrans.toString());
            }
        }     
    }

    public static void transactionYearShow(int num) {
        for (Transaction listTrans : listTransaction) {
            if (num == listTrans.getDateTimeTransaction().getYear()) {
                System.out.println(listTrans.toString());
            }
        }     
    }
}
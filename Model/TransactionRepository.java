package Model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Model.TRANSACTIONTYPE;
import Model.Transaction;
import Model.User;

public class TransactionRepository {
    private static String numberVerify;
    private static int transactionCount = 0;
    private static Map<Integer, Transaction> transactionList = new LinkedHashMap<>();
    private static List<Transaction> listTransaction = new ArrayList<>();
    
    public static int addTransaction(Transaction transaction) {
        transactionCount += 1;
        listTransaction.add(transaction);
        return transactionCount;
    }

    // public static void addNewTransaction(Transaction transactionSending, Transaction transactionReceiving) {
    //     transactionSending.setTransactionType(TRANSACTIONTYPE.SENDER);
    //     transactionReceiving.setTransactionType(TRANSACTIONTYPE.RECEIVER);

    //     addTransaction(transactionSending);
    //     addTransaction(transactionReceiving);

    // }

    public static int getIdOfTransaction (Transaction transaction) {
        Set<Integer>keySet = transactionList.keySet();
        

        return 0;
    }

    public static int getTransactionById(int id) {
        return 0;
    }

    public static boolean checkCodeVerification(String codeCheck, int idOfThisTransaction) {
        return true;
    }

    


}
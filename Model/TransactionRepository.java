package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionRepository {
    private static int transactionCount = 0;
    private static int id = 1;
    private static int borrowingTransactionCount = 0;
    private static List<BorrowingTransaction> listBorrowingTransaction = new ArrayList<>();
    private static List<Transaction> listTransaction = new ArrayList<>();

    public static int addTransaction(Transaction transaction) {
        if (transaction.isValid() == true)
        transactionCount += 1;
        transaction.setId(id);
        id++;
        listTransaction.add(transaction);
        return transactionCount;
    }

    public static Transaction getTransactionById(int id) {
        Transaction transaction = new Transaction();
        for (Transaction transList : listTransaction) {
            if (id == transList.getId()) {
                transaction = transList;
            }
        }
        return transaction;
    }




    public static List<Transaction> getTransactionList() {
        return listTransaction;
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


    public static List<Transaction> getTransactionHistory() {
        List<Transaction>transactionHistory = new ArrayList<>();
        for (Transaction transList : listTransaction) {
            if (transList.getBeneficiaryCurrentAccount() == User.getUser().getCurrentAccount()
                    || transList.getSenderCurrentAccount() == User.getUser().currentAccount) {
                transactionHistory.add(transList);
            }
        }
        return transactionHistory;
    }

    
    
    public static int addBorrowingTransaction(BorrowingTransaction borrowingTransaction) {
        // Helen: if (transaction.isValid() == true)
        borrowingTransactionCount += 1;
        borrowingTransaction.setId(borrowingTransactionCount);
        
        listBorrowingTransaction.add(borrowingTransaction);
        return borrowingTransactionCount;
    }

    public BorrowingTransaction getBorrowingTransById(int id) {
        BorrowingTransaction borrowingTransaction = new BorrowingTransaction();
        for (BorrowingTransaction borrowList: listBorrowingTransaction) {
            if (borrowList.getId() == id) {
                borrowingTransaction = borrowList;
            }
        }
        return borrowingTransaction;
    }
}
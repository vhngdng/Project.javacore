package service;

import Model.Transaction;
import repository.TransactionRepository;
import repository.UserRepository;

public class TransactionService {

    public static String requestVerificationCode(Transaction transactionSending) {
        String verifiactionCode = UserRepository.requestVerificationCode(transactionSending);
        return verifiactionCode;
    }

    public static void addNewTransaction(Transaction transactionSending, Transaction transactionReceiving) {
        TransactionRepository.addNewTransaction(transactionSending, transactionReceiving);
    }

    public static boolean checkCode(String codeCheck, int idOfThisTransaction) {
        boolean isValid = TransactionRepository.checkCodeVerification(codeCheck, idOfThisTransaction);
        return isValid;
    }
    
}

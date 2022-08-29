package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import Model.Transaction;
import Model.TransactionSending;

public class AdminRequestRepository {
    private static int requestCount = 0;
    private static Map<Integer, TransactionSending>requestList = new LinkedHashMap<>();

    public static Map<Integer, TransactionSending> addRequest(Transaction transactionSending) {
        String numberVerify = UserRepository.getNumberVerify();
        TransactionSending transactionRequest = (TransactionSending) transactionSending;

        transactionRequest.setNumberVerify(numberVerify);
        requestList.put(++requestCount, transactionRequest);
        
        return requestList;

    }
    
}

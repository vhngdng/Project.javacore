package Model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;


public class TransactionReceiving extends Transaction {
    public final static TRANSACTIONTYPE transactiontype = TRANSACTIONTYPE.RECEIVER;

    public TransactionReceiving(User user, int money, LocalDate dateTimeTransaction) {
        super(user, money, dateTimeTransaction);
    }

    
}

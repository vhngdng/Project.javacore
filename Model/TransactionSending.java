package Model;

import java.time.LocalDate;

public class TransactionSending extends Transaction {
    private String veirificationCode;
    public String getNumberVerify() {
        return veirificationCode;
    }

    public void setNumberVerify(String numberVerify) {
        this.veirificationCode = numberVerify;
    }

    public TransactionSending(User userSender, int moneyTransfer, LocalDate dateTimeSendingTransaction) {
    }

    public TransactionSending() {
    }

    public final static TRANSACTIONTYPE transactiontype = TRANSACTIONTYPE.SENDER;

   

    

    

 
}

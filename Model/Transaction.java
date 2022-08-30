package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    protected int senderCurrentAccount;
    protected int beneficiaryCurrentAccount;
    protected int money;
    protected LocalDateTime dateTimeTransaction;
    protected boolean isValid;

    public Transaction(int senderCurrentAccount, int beneficiaryCurrentAccount, int money,
            LocalDateTime dateTimeTransaction2) {
        this.senderCurrentAccount = senderCurrentAccount;
        this.beneficiaryCurrentAccount = beneficiaryCurrentAccount;
        this.money = money;
        this.dateTimeTransaction = dateTimeTransaction2;
    }

    public Transaction() {

    }

    public int getBeneficiaryCurrentAccount() {
        return beneficiaryCurrentAccount;
    }

    public int getMoney() {
        return money;
    }

    public LocalDateTime getDateTimeTransaction() {
        return dateTimeTransaction;
    }

    public boolean isResult() {
        return isValid;
    }

    public int getSenderCurrentAccount() {
        return this.senderCurrentAccount;
    }

    public void setValid(boolean b) {
        this.isValid = b;
    }

    @Override
    public String toString() {
        return "Transaction" + "\nsenderCurrentAccount = " + senderCurrentAccount + ", beneficiaryCurrentAccount = "
                + beneficiaryCurrentAccount + ", dateTimeTransaction = "
                + dateTimeTransaction + ", money = " + money + ", result = " + isValid;
    }

}
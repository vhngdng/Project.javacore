package Model;

import java.time.LocalDateTime;

public class BorrowingTransaction {
    protected int borrowerCurrentAccount;
    protected int money;
    protected LocalDateTime dateTimeTransaction;
    protected int id;

    public BorrowingTransaction(int borrowerCurrentAccount, int money, LocalDateTime dateTimeTransaction) {
        this.borrowerCurrentAccount = borrowerCurrentAccount;
        this.money = money;
        this.dateTimeTransaction = dateTimeTransaction;
    }

    public BorrowingTransaction() {
    }

    public int getBorrowerCurrentAccount() {
        return borrowerCurrentAccount;
    }

    public void setBorrowerCurrentAccount(int borrowerCurrentAccount) {
        this.borrowerCurrentAccount = borrowerCurrentAccount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public LocalDateTime getDateTimeTransaction() {
        return dateTimeTransaction;
    }

    public void setDateTimeTransaction(LocalDateTime dateTimeTransaction) {
        this.dateTimeTransaction = dateTimeTransaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String text = "BorrowingTransaction:\nborrowerCurrentAccount = " + borrowerCurrentAccount + ", dateTimeTransaction = "
                + dateTimeTransaction.withNano(0) + ", id = " + id + ", money = " + money;
        return text;
    }
}
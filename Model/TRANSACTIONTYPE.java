package Model;

public enum TRANSACTIONTYPE {
    SENDER(1),
    RECEIVER(-1);
    
    private int numVal;

    TRANSACTIONTYPE(int numVal){
        this.numVal = numVal;
    }

    public int getNumVal() {
        return this.numVal;
    }


}


package Model;

import Controller.ControllerUser;
import View.UserView;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Scanner;



public class SavingAccount {

    private  static  final double[] SavingInterestRate={ 3.1 , 3.7, 4.1 , 4.5 , 4.8 };
    private  double savingBalence;//số tiền gửi
    private LocalDate sentDate; // ngày gửi
    private  double savingInterest; // lãi xuất
    private double monthlyDeposit; //sô tháng



    public SavingAccount(double savingBalence, LocalDate sentDate, double savingInterest, double monthlyDeposit) {
        this.savingBalence = savingBalence;
        this.sentDate = sentDate;
        this.savingInterest = savingInterest;
        this.monthlyDeposit = monthlyDeposit;
        savingsInformation();
    }

    public double getSavingBalence() {
        return savingBalence;
    }

    public void setSavingBalence(double savingBalence) {
        this.savingBalence = savingBalence;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public double getSavingInterest() {
        return savingInterest;
    }

    public void setSavingInterest(double savingInterest) {
        this.savingInterest = savingInterest;
    }

    public double getMonthlyDeposit() {
        return monthlyDeposit;
    }

    public void setMonthlyDeposit(double monthlyDeposit) {
        this.monthlyDeposit = monthlyDeposit;
    }


    public  static  double getSavingInterestRate(int month){
        return SavingInterestRate[month/3];

    }
    public void  savingsInformation(){
        Scanner  scanner = new Scanner(System.in);
        User user = User.getUser();
       boolean curn = true ;
        while (curn=true){
            if(user.getBalance()>=getSavingBalence()){
                Transaction transaction = new Transaction();
                ControllerUser controllerUser = new ControllerUser();
                System.out.println("số tiền gửi  là  "+getSavingBalence());
                System.out.println(" số tháng gửi là "+getMonthlyDeposit());
                System.out.println(" với mức lãi xuất là "+getSavingInterest());
                System.out.println(" ngày gửi "+ getSentDate());
                System.out.println( " tổng số tiền lãi "+getSavingBalence()*getSavingInterest()/100);
                System.out.println(" bạn có đồng : ");
                System.out.println( " [1]   đồng ý \n [2]   từ chối  ");
                int moinhap = scanner.nextInt();
                if(moinhap==1){
                    System.out.println( " bạn đã gửi tiết kiệm thành công");
                    user.setBalance((int) (user.getBalance() - getSavingBalence()));
                    System.out.println(" số dư còn lại là  : "+user.getBalance());
                    curn=false;


                }else {
                    System.out.println("bạn đã từ chối gửi tiết kiệm");

                }



            }else {

                System.out.println("số tiền gửi không thể lớn hơn số dư tài khoản ");
                System.out.println( " mời bạn nhập lại số tiền ");
                savingBalence  = scanner.nextDouble();

            }

        }




//        JSONObject moneyTransactionJson = new JSONObject();
//        moneyTransactionJson.put("moneyTransfer",(int) getSavingBalence());
//        // check money
//            controllerUser.checkMoneyOfSender(moneyTransactionJson);

//            user.setBalance((int) (user.getBalance() - getSavingBalence()));
//            System.out.println(" số dư còn lại là  : "+user.getBalance());
//        }








    }



}

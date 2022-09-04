package Controller;

import java.time.LocalDate;

import Model.SavingAccount;
import Model.User;
import Model.UserRepository;
import View.SavingView;

public class ControllerSaving {
    public SavingAccount saving(double savingBalence, int monthlyDeposit) {
        double savingInterest = SavingAccount.getSavingInterestRate(monthlyDeposit);
        LocalDate date = LocalDate.now();
        SavingAccount savingAccount = new SavingAccount(savingBalence, date, savingInterest, monthlyDeposit);

        return savingAccount;
    }

    public void savingsInformation(SavingAccount savingAccount) {
        SavingView savingView = new SavingView();
        savingView.displayInformationSaving(savingAccount);                         // show result
    }


    public static boolean checkSavingBalance(double savingBalance) {
        boolean isValid = UserRepository.checkSavingBalance(savingBalance);
        if (isValid == true) {
            return true;
        }else{
            SavingView savingView = new SavingView();               // amount is not valid -> back to saving view
            savingView.displayWrongAmount();
            savingView.displaySavingView();
            return false;
        }
    }

	public static int savingProcess(SavingAccount savingAccount) {
        return UserRepository.minusTheSavingAmount(savingAccount);
        
	}
}

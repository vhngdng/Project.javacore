package Model;
public class Admin extends Person{
    

    public Admin() {
        this.name = "admin";
        this.password ="admin";
        this.currentAccount = 0;
        this.role = -1;
        
    }

    @Override
    public void setLocked(boolean isLocked){
        this.setLocked(false);
    }

}


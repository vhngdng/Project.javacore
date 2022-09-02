package Model;

public class Person {
    protected int id;
    protected String password;
    protected int role;
    protected String name;
    protected int currentAccount;
    private boolean isLocked = false;
    public boolean isLocked() {
        return isLocked;
    }
    public boolean getLocked() {
        return this.isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public int getCurrentAccount() {
        return this.currentAccount;
    }

    public void setCurrentAccount(int currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Person() {
    }

    public Person(String name, String password, int role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "currentAccount = " + currentAccount + ", id = " + id + ", isLocked = " + isLocked + ", name = " + name
                + ", password = " + password;
    }
}

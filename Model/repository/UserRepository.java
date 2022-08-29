package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import User;


public class UserRepository {
   
    private static int USER_COUNT = 1;
    User user;
    Person person;
    // HashMap<Integer, String> idList = new LinkedHashMap<>();
    private static List<Person> personList = new ArrayList<>();
    private static int id = 0;

    public UserRepository() {

    }

    public static void addAdminUser() {
        Person admin = new Admin();
        personList.add(admin);
    }

    /**
     * add new user
     * 
     * @param person
     */
    public static User addNewUser(User person) {
        int num = USER_COUNT + 1;
        id += 1;
        person.setId(id);
        person.setRole(1);
        personList.add(person);
        USER_COUNT++;
        return person;
    }

    /**
     * delete user by id
     * 
     * @param id
     * @return
     */
    public User deleteUser(int id) {
        return null;
    }

    /**
     * get user by id
     * 
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return null;
    }

    /**
     * lay toan bo danh sach users
     * 
     * @return
     */

    // kiem tra tk admin
    public boolean checkAccount(String name, String password) {
        return false;
    }

    public static Person checkLoginUser(Person person) {
        for (Person listPerson : personList) {
            if (listPerson != null
                    && listPerson.getName().equals(person.getName())
                    && listPerson.getCurrentAccount() == person.getCurrentAccount()) {
                person = listPerson;
                break;
            }
        }

        if (person.getRole() != -1 && person.getRole() != 1) {
            person = null;
            System.out.println("sai thông tin đăng nhập, vui lòng nhập lại");
        }
        return person;
    }

    public static User checkCurrentAccount(User user) {
        for (Person listPerson : personList) {
            if (listPerson != null && ((User) listPerson).getCurrentAccount() == (user.getCurrentAccount())) {
                user = (User) listPerson;
                break;
            }
        }

        if (user.getId() == 0) {
            System.out.println("sai thông tin");
            user = null;
        }
        return user;
    }

    public static boolean checkMoney(User user, int currentAccount) {
        boolean isValid = false;
        
        int moneyTransfer = user.getBalance();
        user = checkCurrentAccount(user);
        if (moneyTransfer > user.getBalance()) {
            System.out.println("So tien khong hop le");
            isValid = false;
        }else{
            isValid = true;
        }
        
        return isValid;
    }


    public static User checkMoneyOfSender(User senderUser) {
        int moneyTransfer = senderUser.getBalance();
        senderUser = checkCurrentAccount(senderUser);
        if (moneyTransfer > senderUser.getBalance() && senderUser !=null) {
            System.out.println("So tien khong hop le");
            senderUser = null;
        }
        return senderUser;
    }

    public static User getUserWithCurrentAccount(int senderCurrentAccount) {
        User user = new User();
        for (Person listPerson : personList) {
            if (listPerson != null && ((User) listPerson).getCurrentAccount() == senderCurrentAccount) {
                user = (User) listPerson;
                break;
            }
        }

        if (user.getId() == 0) {
            System.out.println("sai thông tin");
            user = null;
        }
        return user;
    }

    public static boolean isValid(Transaction transactionSending) {
        return false;
    }

    //get 4 digit verify number
    public static String getNumberVerify() {
        Random random = new Random();
        String numberVerify = String.format("%04d", random.nextInt());
        return numberVerify;
    }

    
    public static boolean checkNumberVerify(String numberVerify) {
        return true;
    }

    public static User findUserWithId (int id) {
        return null;
    }

    public static String requestVerificationCode(Transaction transactionSending) {
        String verificationCode = getNumberVerify();
        ((TransactionSending)transactionSending).setNumberVerify(verificationCode);
        
        return verificationCode;
    }
}

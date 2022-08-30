package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Controller.ControllerLogin;
import Controller.ControllerUser;
import View.MenuView;

public class UserRepository {

    private static int USER_COUNT = 1;
    User user;
    Person person;
    private static Map<Integer, String> userList = new LinkedHashMap<>();
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
        person.getCurrentAccount();

        int num = USER_COUNT + 1;
        id += 1;
        person.setId(id);
        person.setRole(1);
        personList.add(person);
        userList.put(person.getCurrentAccount(), person.name);
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
        if (person.isLocked() == true) {
                person = null;
                System.out.println("Tài khoản đã bị khóa, không thể đăng nhập");
                MenuView menuView = new MenuView();
                menuView.display();
        }else if (person.getRole() != -1 && person.getRole() != 1) {
            person = null;
            System.out.println("sai thông tin đăng nhập, vui lòng nhập lại");
        }
        return person;
    }

    public static User checkCurrentAccount(User user) {
        for (Person listPerson : personList) {
            if (listPerson != null && listPerson.getRole() != -1
                    && ((User) listPerson).getCurrentAccount() == (user.getCurrentAccount())) {
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
        } else {
            isValid = true;
        }

        return isValid;
    }

    public static boolean checkMoneyOfSender(int moneyTransfer) {
        boolean isValid = false;
        if (moneyTransfer > User.getUser().getBalance()) {
            System.out.println("So tien khong hop le");
            isValid = false;
        } else {
            isValid = true;
        }

        return isValid;
    }

    public static User getUserWithCurrentAccount(int senderCurrentAccount) {
        User user = new User();
        for (Person listPerson : personList) {
            if (listPerson != null && listPerson.getRole() != -1
                    && ((User) listPerson).getCurrentAccount() == senderCurrentAccount) {
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


    public static User findUserWithId(int id) {
        return null;
    }

    // chuyen tien
    public static int transferMoney(Transaction transaction) {
        
        User benefiUser = getUserWithCurrentAccount(transaction.getBeneficiaryCurrentAccount());
        User sendUser = getUserWithCurrentAccount(transaction.getSenderCurrentAccount());
        if (benefiUser == sendUser) {
            System.out.println("Người nhận không phù hợp");
            ControllerUser.displayUserView();
        }
        int money = transaction.getMoney();
        benefiUser.setBalance(benefiUser.getBalance() + money);
        sendUser.setBalance(sendUser.getBalance() - money);
        transaction.setValid(true);
        // add transaction in user
        TransactionRepository.addTransactionUser(User.getUser().getCurrentAccount());
        System.out.println("Số tiền còn dư của bạn :" + sendUser.getBalance());
        return sendUser.getBalance();
    }

    public static boolean checkIdOrCurrentAccount(int num) {
        boolean isValid = false;        
        for (Person personL : personList) {
            if (num == personL.getCurrentAccount() || num == personL.getId()) {
                
                personL.setLocked(true);
                System.out.println("Tai khoan cua " + personL.getName() + ", số tk: " + personL.getCurrentAccount()
                        + " đã bị khóa");
                isValid = true;
                break;
            }
            
        }
        if (isValid == false) {
            System.out.println("sai thông tin");
        }
        return isValid;
    }

    public static void getListUser() {
        
        for (Person personL: personList) {
            if (personL.getRole() != -1) {
                User user = (User) personL;
                System.out.println(user.toString());
            }
        }
    }

    public static Map<Integer, Integer> showOtherUsers() {
        int i = 1;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (Map.Entry<Integer, String> entry: userList.entrySet() ) {
            if (entry.getKey() != User.getUser().getCurrentAccount()) {
            System.out.println("[" + i + "]" + " User: " + entry.getValue() + ", current account: " + entry.getKey());
            map.put(i, entry.getKey());
            i++;
            }
        }
        return map;

    }

}

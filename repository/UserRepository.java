package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import Model.Admin;
import Model.Person;
import Model.User;

public class UserRepository {
    private static int USER_COUNT = 1;
    Admin admin;
    User user;
    Person person;
    // HashMap<Integer, String> idList = new LinkedHashMap<>();
    private static List<Person> personList = new ArrayList<>();

    public UserRepository() {
        // this.idList.put(admin.getId(), admin.getPassword());
        personList.add(admin);
    }
    
    /**
     * add new user
     * @param person
     */
    public User addNewUser(User person) {
        int num = USER_COUNT + 1;
        this.personList.add(person);
        USER_COUNT++;
        return person;
    }

    /**
     * delete user by id
     * @param id
     * @return
     */
    public User deleteUser(int id) {
        return null;
    }

    /**
     * get user by id
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return null;
    }

    /**
     * lay toan bo danh sach users
     * @return
     */
   
    // kiem tra tk admin
    public boolean checkAccount(String name, String password) {
      return false;
    }

    public static Person checkLoginUser(Person person) {
        for (Person listPerson: personList) {
            if (listPerson != null) {
                listPerson.getName().equals(person.getName());
                person = listPerson;
                break;
            }
        }

        if (person == null) {
            System.out.println("sai thông tin đăng nhập, vui lòng nhập lại");
        }
        return person;
    }


}

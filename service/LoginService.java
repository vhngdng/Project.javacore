package service;


import java.util.Set;

import javax.management.ConstructorParameters;

import Model.*;
import repository.UserRepository;

public class LoginService {
    
    private UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }


    

    /**
     * @return
     */
    public Set getMemberList() {
        
        return null;
    }



    
    /**
     * @param id
     * @return
     */
    public User getUser(int id) {
        return null;
    }



    /**
     * 
     * @param id
     * @return
     */
    public int deleteUser(int id) {
        return 0;
    }

    
    public Set register(User user) {
        return null;
    }




    public static Person checkLoginUser(Person person) {
        person = UserRepository.checkLoginUser(person);
        return person;
    }

    public static User checkCurrentAccount(User user) {
        UserRepository.checkCurrentAccount(user);
        return user;
    }
}

package co.inventorsoft.academy.spring.services;

import co.inventorsoft.academy.spring.models.User;
import co.inventorsoft.academy.spring.repositories.UserRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * User service to operate user repository and make further actions.
 */

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User getUserById(Long userId){
        return userRepository.getUserById(userId);
    }


}

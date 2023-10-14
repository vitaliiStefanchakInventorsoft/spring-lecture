package co.inventorsoft.academy.spring.services;

import co.inventorsoft.academy.spring.models.User;
import co.inventorsoft.academy.spring.repositories.UserRepository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * User service.
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

    public Optional<User> getUserById(UUID userId){
        return userRepository.getUserById(userId);
    }


}

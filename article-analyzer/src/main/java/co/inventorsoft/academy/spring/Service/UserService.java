package co.inventorsoft.academy.spring.Service;

import co.inventorsoft.academy.spring.model.User;
import co.inventorsoft.academy.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}

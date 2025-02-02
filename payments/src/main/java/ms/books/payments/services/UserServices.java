package ms.books.payments.services;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.UserRepository;
import ms.books.payments.data.model.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices implements IUserServices {

    private final UserRepository userRepository;

    @Override
    public Users getUser(Integer id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Users getUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Users createUser(CreateUserRequest request) {
            Users user = new Users();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setPhone(request.getPhone());
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.getUsers();
    }
}

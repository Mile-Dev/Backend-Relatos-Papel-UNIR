package ms.books.payments.services;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.UserRepository;
import ms.books.payments.data.model.Users;
import org.springframework.stereotype.Service;

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
    public Users createUser(CreateUserRequest request) {
        return null;
    }
}

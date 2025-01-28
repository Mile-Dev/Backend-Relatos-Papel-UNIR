package ms.books.payments.services;

import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.UserRepository;
import ms.books.payments.data.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements IUserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users getUser(Integer id) {
        return null;
    }

    @Override
    public Users getUserEmail(String email) {
        return null;
    }

    @Override
    public Users createUser(CreateUserRequest request) {
        return null;
    }
}

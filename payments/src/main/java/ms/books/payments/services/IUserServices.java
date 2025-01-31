package ms.books.payments.services;

import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.model.Users;

import java.util.List;

public interface IUserServices {

    Users getUser(Integer id);

    Users getUserEmail(String email);

    Users createUser(CreateUserRequest request);

    List<Users> getAllUsers();
}

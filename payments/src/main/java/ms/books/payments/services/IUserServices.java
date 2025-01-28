package ms.books.payments.services;

import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.model.Users;

public interface IUserServices {

    Users getUser(Integer id);

    Users getUserEmail(String email);

    Users createUser(CreateUserRequest request);
}

package ms.books.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.model.Users;
import ms.books.payments.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServices servicesUser;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Users> getUserid(@PathVariable Integer userId) {

        log.info("Request received for user {}", userId);
        Users user = servicesUser.getUser(userId);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers() {

        List<Users> users = servicesUser.getAllUsers();

        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Users> addUsers(@RequestBody CreateUserRequest request) {

        Users createdProduct = servicesUser.createUser(request);

        if (createdProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

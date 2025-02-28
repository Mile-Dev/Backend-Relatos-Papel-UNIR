package ms.books.payments.data;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.PersonalData;
import ms.books.payments.data.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository  {

    private final UserJpaRepository repositoryUser;

    public List<Users> getUsers() { return  repositoryUser.findAll();}

    public Users getUserById(int id) { return repositoryUser.findById(id).orElse(null);}

    public Users save(Users user) {
        return repositoryUser.save(user);
    }

    public void delete(Users user) {
        repositoryUser.delete(user);
    }

    public Users findByEmail(String email) { return repositoryUser.findByEmail(email);}

}

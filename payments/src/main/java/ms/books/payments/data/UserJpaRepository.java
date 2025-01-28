package ms.books.payments.data;

import ms.books.payments.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserJpaRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    Users findByEmail(String email);

}

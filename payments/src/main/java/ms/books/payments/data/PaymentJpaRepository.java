package ms.books.payments.data;

import ms.books.payments.data.model.Payments;
import ms.books.payments.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentJpaRepository extends JpaRepository<Payments, Integer>, JpaSpecificationExecutor<Payments> {

}

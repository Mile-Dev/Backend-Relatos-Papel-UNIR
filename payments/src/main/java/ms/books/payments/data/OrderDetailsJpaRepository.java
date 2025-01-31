package ms.books.payments.data;

import ms.books.payments.data.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDetailsJpaRepository extends JpaRepository<OrderDetails, Integer>, JpaSpecificationExecutor<OrderDetails> {

}

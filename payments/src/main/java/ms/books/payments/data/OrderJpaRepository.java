package ms.books.payments.data;

import ms.books.payments.data.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderJpaRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {

}

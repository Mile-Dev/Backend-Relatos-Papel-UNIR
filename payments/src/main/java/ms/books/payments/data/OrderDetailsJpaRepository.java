package ms.books.payments.data;

import ms.books.payments.data.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderDetailsJpaRepository extends JpaRepository<OrderDetails, Integer>, JpaSpecificationExecutor<OrderDetails> {

    public List<OrderDetails> findByOrderId(int orderId);

    public OrderDetails findByOrderIdAndId(int orderId, int detailId);

}

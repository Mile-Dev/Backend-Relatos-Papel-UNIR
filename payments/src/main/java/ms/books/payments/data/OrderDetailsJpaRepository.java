package ms.books.payments.data;

import ms.books.payments.data.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderDetailsJpaRepository extends JpaRepository<OrderDetails, Integer>, JpaSpecificationExecutor<OrderDetails> {

    List<OrderDetails> findByOrderId(int orderId);

    OrderDetails findByOrderIdAndId(int orderId, int detailId);

    OrderDetails findByOrderIdAndBookId(int orderId, int bookId);

    void removeById(int id);
}

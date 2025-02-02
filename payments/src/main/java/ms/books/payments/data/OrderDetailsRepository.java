package ms.books.payments.data;

import lombok.RequiredArgsConstructor;
import ms.books.payments.data.model.OrderDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailsRepository {

    private final OrderDetailsJpaRepository repositoryOrderDetails;

    public List<OrderDetails> getOrders() {
        return repositoryOrderDetails.findAll();
    }

    public List<OrderDetails> findByOrderId(int orderId) {
        return repositoryOrderDetails.findByOrderId(orderId);
    }

    public OrderDetails findByOrderIdAndId(int orderId, int detailId  ) {
        return repositoryOrderDetails.findByOrderIdAndId(orderId, detailId);
    }

    public OrderDetails findByOrderIdAndBookId(int orderId, int bookId  ) {
        return repositoryOrderDetails.findByOrderIdAndBookId(orderId, bookId);
    }

    public OrderDetails save(OrderDetails ordersDetails) {return repositoryOrderDetails.save(ordersDetails);}

    public void removeItem(int id) {
        repositoryOrderDetails.removeById(id);
    }

    public OrderDetails getOrderById(int id ) {
        return repositoryOrderDetails.findById(id).orElse(null);
    }
}

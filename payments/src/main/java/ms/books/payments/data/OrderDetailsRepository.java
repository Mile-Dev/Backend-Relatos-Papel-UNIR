package ms.books.payments.data;

import lombok.RequiredArgsConstructor;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.Orders;
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

    public OrderDetails save(OrderDetails ordersDetails) {return repositoryOrderDetails.save(ordersDetails);}
}

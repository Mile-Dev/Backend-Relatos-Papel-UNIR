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

    public List<OrderDetails> getOrders() { return  repositoryOrderDetails.findAll();}
}

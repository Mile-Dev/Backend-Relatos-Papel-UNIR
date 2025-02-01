package ms.books.payments.data;

import lombok.RequiredArgsConstructor;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final OrderJpaRepository repositoryOrder;

    public List<Orders> getOrders() { return  repositoryOrder.findAll();}

    public Orders save(Orders orders) {return repositoryOrder.save(orders);}


}

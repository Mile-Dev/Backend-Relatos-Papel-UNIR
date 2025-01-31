package ms.books.payments.services;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateOrderDetailsRequest;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.data.OrderDetailsRepository;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsServices implements IOrderDetailsServices {

    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderDetails getOrderDetails(Integer id) {
        return null;
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrdered(Integer email) {
        return List.of();
    }

    @Override
    public OrderDetails addOrderDetails(CreateOrderDetailsRequest request) {
        return null;
    }
}

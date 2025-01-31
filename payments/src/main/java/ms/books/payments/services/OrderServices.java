package ms.books.payments.services;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServices implements IOrderServices {

    private final OrderRepository orderRepository;


    @Override
    public Orders getOrder(Integer id) {
        return null;
    }

    @Override
    public List<Orders> getOrdered() {
        return List.of();
    }

    @Override
    public Orders createOrdered(CreateOrderedRequest request) {
        return null;
    }
}

package ms.books.payments.services;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.UserRepository;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.model.Users;
import ms.books.payments.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServices implements IOrderServices {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public Orders getOrder(int id) {
        return orderRepository.getOrderedById(id);
    }

    @Override
    public List<Orders> getOrdered() {
        return orderRepository.getOrders();
    }

    @Override
    @Transactional
    public Orders createOrdered(CreateOrderedRequest request) {
        Users user = userRepository.getUserById(request.getUserId());
        if (user == null)
            {
             throw new UserNotFoundException("User with ID " + request.getUserId() + " not found");
            }
        Orders order = Orders.builder()
                .user(user)
                .totalAmount(request.getTotalAmount())
                .build();

        return orderRepository.save(order);
    }
}

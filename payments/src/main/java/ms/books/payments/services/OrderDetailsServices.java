package ms.books.payments.services;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateOrderDetailsRequest;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.data.OrderDetailsRepository;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.Orders;
import ms.books.payments.exceptions.OrderNotFoundException;
import ms.books.payments.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsServices implements IOrderDetailsServices {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderDetails getOrderDetails(int orderId, int detailId) {
        return orderDetailsRepository.findByOrderIdAndId(orderId, detailId);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrdered(int orderId) {
        return orderDetailsRepository.findByOrderId(orderId);
    }

    @Override
    public OrderDetails addOrderDetails(CreateOrderDetailsRequest request, int orderId) {

        Orders orders = orderRepository.getOrderedById(orderId);
        if (orders == null)
        {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
        OrderDetails orderDetails = OrderDetails.builder()
                .order(orders)
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .quantity(request.getQuantity())
                .bookId(request.getBookId())
                .build();

        return orderDetailsRepository.save(orderDetails);
    }
}

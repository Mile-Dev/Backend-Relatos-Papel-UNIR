package ms.books.payments.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateOrderDetailsRequest;
import ms.books.payments.data.OrderDetailsRepository;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.Orders;
import ms.books.payments.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsServices implements IOrderDetailsServices {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public OrderDetails getOrderDetails(int orderId, int detailId) {
        return orderDetailsRepository.findByOrderIdAndId(orderId, detailId);
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrdered(int orderId) {
        return orderDetailsRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderDetails addOrderDetails(CreateOrderDetailsRequest request, int orderId) {
        Orders orders = orderRepository.getOrderedById(orderId);
        if (orders == null)
        {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
        OrderDetails orderDetails = orderDetailsRepository.findByOrderIdAndBookId(orderId, request.getBookId());
        if (orderDetails == null)
        {
            orderDetails =   OrderDetails.builder()
                    .order(orders)
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .bookId(request.getBookId())
                    .build();
        }
        else
        {
            orderDetails.setPrice(orderDetails.getPrice().add(request.getPrice()));
            orderDetails.setQuantity(orderDetails.getQuantity() + request.getQuantity());
        }
        OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
        entityManager.refresh(savedOrderDetails);
        return savedOrderDetails;
    }

    @Override
    public boolean removeItem(int id) {
        OrderDetails orderDetails = orderDetailsRepository.getOrderById(id);

        if (orderDetails != null) {
            orderDetailsRepository.removeItem(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}

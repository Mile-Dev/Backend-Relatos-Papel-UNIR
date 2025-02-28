package ms.books.payments.services;

import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.controller.model.OrderDTO;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.utils.OrderStatus;

import java.util.List;

public interface IOrderServices {

    Orders getOrder(int id);

    List<Orders> getOrdered();

    Orders createOrdered(CreateOrderedRequest request);

    Boolean updateOrderedCompleted(int orderId);

    Boolean updateOrderedCancelled(int orderId);

    Boolean CreateOrder(OrderDTO orderDTO);
}

package ms.books.payments.services;

import ms.books.payments.controller.model.CreateOrderDetailsRequest;
import ms.books.payments.data.model.OrderDetails;

import java.util.List;

public interface IOrderDetailsServices {

    OrderDetails getOrderDetails(int orderId, int detailId);

    List<OrderDetails> getOrderDetailsByOrdered(int orderId);

    OrderDetails addOrderDetails(CreateOrderDetailsRequest request, int orderId);
}

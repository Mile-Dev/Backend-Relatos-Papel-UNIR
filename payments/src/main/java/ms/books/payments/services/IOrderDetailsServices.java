package ms.books.payments.services;

import ms.books.payments.controller.model.CreateOrderDetailsRequest;
import ms.books.payments.data.model.OrderDetails;

import java.util.List;

public interface IOrderDetailsServices {

    OrderDetails getOrderDetails(Integer id);

    List<OrderDetails> getOrderDetailsByOrdered(Integer email);

    OrderDetails addOrderDetails(CreateOrderDetailsRequest request);
}

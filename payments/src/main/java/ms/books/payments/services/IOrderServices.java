package ms.books.payments.services;

import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.data.model.Orders;

import java.util.List;

public interface IOrderServices {

    Orders getOrder(Integer id);

    List<Orders> getOrdered();

    Orders createOrdered(CreateOrderedRequest request);

}

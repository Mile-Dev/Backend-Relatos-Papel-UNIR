package ms.books.payments.services;

import ms.books.payments.controller.model.CreatePaymentsRequest;
import ms.books.payments.data.model.Payments;

import java.util.List;

public interface IPaymentServices {

    List<Payments> getPayment(Integer orderId);

    Payments getPaymentsById(Integer id);

    Payments createPayments(CreatePaymentsRequest request);

    List<Payments> getAllPayments();
}

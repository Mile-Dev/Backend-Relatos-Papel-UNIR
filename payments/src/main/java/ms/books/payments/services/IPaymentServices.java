package ms.books.payments.services;

import ms.books.payments.controller.model.CreatePaymentsRequest;
import ms.books.payments.data.model.Payments;

import java.util.List;

public interface IPaymentServices {

    Payments getPayment(Integer id);

    Payments getPaymentsById(Integer userId);

    Payments createPayments(CreatePaymentsRequest request);

    List<Payments> getAllPayments();
}

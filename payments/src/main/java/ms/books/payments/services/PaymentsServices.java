package ms.books.payments.services;

import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.controller.model.CreatePaymentsRequest;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.PaymentRepository;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.model.Payments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsServices implements IPaymentServices {

    private final PaymentRepository paymentRepository;

    @Override
    public Payments getPayment(Integer id) {
        return null;
    }

    @Override
    public Payments getPaymentsById(Integer userId) {
        return null;
    }

    @Override
    public Payments createPayments(CreatePaymentsRequest request) {
        return null;
    }

    @Override
    public List<Payments> getAllPayments() {
        return List.of();
    }
}

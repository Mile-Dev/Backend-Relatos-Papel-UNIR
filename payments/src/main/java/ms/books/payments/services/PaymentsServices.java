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

        return paymentRepository.findById(id)
                .orElseThrow(()  -> new PaymentNotFoundException("Payment with ID " + id + " not found"));
    }

    @Override
    public List<Payments> getPaymentsById(Integer userId) {

        return paymentRepository.findByUserId(userId);

    }

    @Override
    public Payments createPayments(CreatePaymentsRequest request) {
        Payments payment = Payments.builder()
                .amount(request.getAmount())
                .userId(request.getUserId())
                .build();
        return paymentRepository.save(payment);

    }

    @Override
    public List<Payments> getAllPayments() {

        return paymentRepository.findAll();

    }
}

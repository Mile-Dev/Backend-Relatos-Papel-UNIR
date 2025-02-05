package ms.books.payments.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms.books.payments.controller.model.CreatePaymentsRequest;
import ms.books.payments.data.PaymentRepository;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.model.Payments;
import ms.books.payments.data.utils.OrderStatus;
import ms.books.payments.data.utils.PaymentStatus;
import ms.books.payments.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsServices implements IPaymentServices {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<Payments> getPayment(Integer id) {

        return paymentRepository.getPayments();

    }

    //Revisar metodo: Devolver payments por id
    @Override
    public Payments getPaymentsById(Integer id) {

        return paymentRepository.getPaymentById(id);

    }


    @Override
    @Transactional
    public Payments createPayments(CreatePaymentsRequest request) {
        Orders order = orderRepository.getOrderedById(request.getOrderId());
        if (order == null)
        {
            throw new OrderNotFoundException("Order with ID "+ request.getOrderId() + "not found" );
        }

        Payments payment = Payments.builder()
                .orderId(order)
                .amount(request.getAmount())
                .paymentMethodUsers(request.getPaymentMethodUsers())
                .paymentStatus(PaymentStatus.Pending)
                .build();

        return paymentRepository.save(payment);

    }

    @Override
    public List<Payments> getAllPayments() {

        return paymentRepository.getPayments();

    }
}

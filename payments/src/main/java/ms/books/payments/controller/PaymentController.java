package ms.books.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreatePaymentsRequest;

import ms.books.payments.data.model.Payments;
import ms.books.payments.data.model.Orders;
import ms.books.payments.services.PaymentsServices;
import ms.books.payments.services.OrderServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsServices servicesPayments;
    private final OrderServices servicesOrders;

    @GetMapping("/payment/{id}")
    public ResponseEntity<Payments> getPaymentId(@PathVariable Integer id) {

        log.info("Request received for user {}", id);
        Payments payments = (Payments) servicesPayments.getPayment(id);

        if (payments != null) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payments/order/{id}")
    public ResponseEntity<Payments> getPaymentByOrderId(@PathVariable Integer id) {

        Orders order = servicesOrders.getOrder(id);
        Payments payment = servicesPayments.getPaymentsById(order.getId());

        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payments>> getAllPayments(){

        List<Payments> payments = servicesPayments.getAllPayments();
        if(payments != null){
            return ResponseEntity.ok(payments);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/payments")
    public ResponseEntity<Payments> addUsers(@RequestBody CreatePaymentsRequest request) {

        Payments createdPayment = servicesPayments.createPayments(request);

        if (createdPayment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

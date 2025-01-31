package ms.books.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.data.model.Orders;
import ms.books.payments.services.OrderServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices servicesOrder;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Orders> getOrdered(@PathVariable Integer orderId) {

        log.info("Request received for user {}", orderId);
        Orders order = servicesOrder.getOrder(orderId);

        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getOrders() {

        List<Orders> orders = servicesOrder.getOrdered();

        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Orders> addUsers(@RequestBody CreateOrderedRequest request) {

        Orders createdOrder = servicesOrder.createOrdered(request);

        if (createdOrder != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

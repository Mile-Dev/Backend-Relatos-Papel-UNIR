package ms.books.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreateOrderDetailsRequest;
import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.OrderDetailsRepository;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.Users;
import ms.books.payments.services.OrderDetailsServices;
import ms.books.payments.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderDetailsController {

    private final OrderDetailsServices servicesOrderDetails;

    @GetMapping("/order/{orderId}/details/{detailId}")
    public ResponseEntity<OrderDetails> getOrderedDetailsById(@PathVariable int orderId, @PathVariable int detailId) {

        log.info("Request received for user {}", orderId);
        OrderDetails orderDetails = servicesOrderDetails.getOrderDetails(orderId,detailId);

        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderId}/details")
    public ResponseEntity<List<OrderDetails>> getOrderedDetailsByOrder(@PathVariable int orderId) {

        List<OrderDetails> orderDetails = servicesOrderDetails.getOrderDetailsByOrdered(orderId);

        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders/{orderId}/details")
    public ResponseEntity<OrderDetails> addOrderDetails(@RequestBody CreateOrderDetailsRequest request, @PathVariable int orderId) {

        OrderDetails addOrderDetails = servicesOrderDetails.addOrderDetails(request, orderId);

        if (addOrderDetails != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addOrderDetails);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

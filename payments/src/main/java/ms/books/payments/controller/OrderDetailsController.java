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

    @GetMapping("/order detail/{id}")
    public ResponseEntity<OrderDetails> getOrderedDetails(@PathVariable Integer id) {

        log.info("Request received for user {}", id);
        OrderDetails orderDetails = servicesOrderDetails.getOrderDetails(id);

        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order detail/order/{orderId}")
    public ResponseEntity<List<OrderDetails>> getOrderedDetailsByOrder(@PathVariable Integer orderId) {

        List<OrderDetails> orderDetails = servicesOrderDetails.getOrderDetailsByOrdered(orderId);

        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/order detail")
    public ResponseEntity<OrderDetails> addUsers(@RequestBody CreateOrderDetailsRequest request) {

        OrderDetails addOrderDetails = servicesOrderDetails.addOrderDetails(request);

        if (addOrderDetails != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addOrderDetails);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

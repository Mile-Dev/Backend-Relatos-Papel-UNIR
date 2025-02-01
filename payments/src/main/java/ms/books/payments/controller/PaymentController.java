package ms.books.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreatePaymentsRequest;
import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.model.Payments;
import ms.books.payments.data.model.Users;
import ms.books.payments.services.PaymentsServices;
import ms.books.payments.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Payments Controller", descripcion = "Microservicio encargado de exponer operaciones CRUD sobre pagos alojados en una base de datos en memoria.")
public class PaymentController {

    private final PaymentsServices servicesPayments;

    @GetMapping("/payment/{id}")
    @Operation(
            operationId = "Obtener pagos",
            descripcion = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los pagos almacenadps en la base de datos."
    )
    @ApiResponse(
            responseCode = "200"
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payments.class)))

    )
    public ResponseEntity<List<Payments>> getPaymentId(@PathVariable Integer id) {

        log.info("Request received for user {}", id);
        Payments payments = servicesPayments.getPayment(id);

        if (payments != null) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payments/order/{id}")
    public ResponseEntity<Payments> getPaymentByOrderId(@PathVariable Integer id) {

        Payments payment = servicesPayments.getPaymentsById(id);

        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
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

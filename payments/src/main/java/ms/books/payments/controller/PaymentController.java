package ms.books.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.controller.model.CreatePaymentsRequest;

import ms.books.payments.data.model.Payments;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.model.Users;
import ms.books.payments.services.PaymentsServices;
import ms.books.payments.services.OrderServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Collections;
import java.util.Map;
@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Payments Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre los pagos realizados  ealojados en una base datos.")
public class PaymentController {

    private final PaymentsServices servicesPayments;
    private final OrderServices servicesOrders;

    @GetMapping("/payment/{id}")
    @Operation(
            operationId = "Obtener pago por id",
            description = "Operacion de lectura",
            summary = "Se devuelve un pago a partir de su identificador."
    )
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payments.class))
    )
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el pago con el identificador indicado.")
    public ResponseEntity<Payments> getPaymentId(@PathVariable Integer id) {

        log.info("Request received for payment {}", id);
        Payments payments = (Payments) servicesPayments.getPayment(id);

        if (payments != null) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payments/order/{id}")
    @Operation(
            operationId = "Obtener pedido por id de orden",
            description = "Operacion de lectura",
            summary = "Se devuelve un pedido de acuerdo al id de la orden especificado.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la orden con el identificador indicado.")
    public ResponseEntity<Payments> getPaymentByOrderId(@PathVariable Integer id) {

        log.info("Request received for order {}", id);

        Orders order = servicesOrders.getOrder(id);
        Payments payment = servicesPayments.getPaymentsById(order.getId());


        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payments")
    @Operation(
            operationId = "Obtener lista de todos los pagos realizados",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos las pagos almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class)))
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
    @Operation(
            operationId = "Insertar un nuevo pago",
            description = "Operacion de escritura",
            summary = "Se crea un pago  a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del pago a realizar.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderedRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la orden  con el identificador indicado.")
    public ResponseEntity<Payments> createPayments(@RequestBody CreatePaymentsRequest request) {

        Payments createdPayment = servicesPayments.createPayments(request);

        if (createdPayment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

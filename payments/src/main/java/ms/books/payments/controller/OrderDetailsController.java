package ms.books.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreateOrderDetailsRequest;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.controller.model.CreateUserRequest;
import ms.books.payments.data.OrderDetailsRepository;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.Orders;
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
    @Operation(
            operationId = "Obtener un item de una orden de pedido",
            description = "Operacion de lectura",
            summary = "Se devuelve un detalle de la orden de pedido especifico de la base de datos")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetails.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el item con el identificador indicado.")
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
    @Operation(
            operationId = "Obtener los detalles de una orden de pedido",
            description = "Operacion de lectura",
            summary = "Se devuelve un detalle de la orden de pedido especifico de la base de datos")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetails.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el item con el identificador indicado.")

    public ResponseEntity<List<OrderDetails>> getOrderedDetailsByOrder(@PathVariable int orderId) {

        List<OrderDetails> orderDetails = servicesOrderDetails.getOrderDetailsByOrdered(orderId);

        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders/{orderId}/details")
    @Operation(
            operationId = "Insertar un nuevo item a una  order",
            description = "Operacion de escritura",
            summary = "Se crea un nuevo item a  orden de pedido  a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del detalle de orden de pedido a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateOrderDetailsRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetails.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el item  con el identificador indicado.")
    public ResponseEntity<OrderDetails> addOrderDetails(@RequestBody CreateOrderDetailsRequest request, @PathVariable int orderId) {

        OrderDetails addOrderDetails = servicesOrderDetails.addOrderDetails(request, orderId);

        if (addOrderDetails != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addOrderDetails);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

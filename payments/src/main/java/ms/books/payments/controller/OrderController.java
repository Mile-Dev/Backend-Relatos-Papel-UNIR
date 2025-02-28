package ms.books.payments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.controller.model.OrderDTO;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.model.Users;
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

    @GetMapping("/order/{id}")
    @Operation(
            operationId = "Obtener order de pedido",
            description = "Operacion de lectura",
            summary = "Se devuelve una orden de pedido especifico de la base de datos")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el usuario con el identificador indicado.")

    public ResponseEntity<Orders> getOrdered(@PathVariable int id) {
        try{
            log.info("Request received for user {}", id);
            Orders order = servicesOrder.getOrder(id);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch(NumberFormatException e){
            log.error("Formato de order inválido: {}", id, e);
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            log.error("Error al eliminar el libro: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/orders")
    @Operation(
            operationId = "Obtener ordenes de pedidos",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos las ordenes almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Users.class)))

    public ResponseEntity<List<Orders>> getOrders() {
       try{
        log.info("Request received for order");
        List<Orders> orders = servicesOrder.getOrdered();
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            log.error("Failled request for order");
            return ResponseEntity.notFound().build();
        }
       }catch(NumberFormatException e){
           log.error("Formato de order inválido:", e);
           return ResponseEntity.badRequest().build();
       }catch (Exception e) {
           log.error("Error al obtener el libro: {}", e.getMessage(), e);
           return ResponseEntity.internalServerError().build();
       }
    }

    @PostMapping("/orders")
    @Operation(
            operationId = "Insertar un nueva order",
            description = "Operacion de escritura",
            summary = "Se crea una orden de pedido  a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del orden de pedido a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la orden  con el identificador indicado.")

    public ResponseEntity<OrderDTO> addOrdered(@RequestBody OrderDTO request) {
       try {
           log.info("Recived request for addOrdered");
           Boolean createdOrder = servicesOrder.CreateOrder(request);

           if (createdOrder != null) {
               return ResponseEntity.status(HttpStatus.CREATED).body(request);
           } else {
               return ResponseEntity.badRequest().build();
           }
       }catch(NumberFormatException e){
           log.error("Formato de order inválido:", e);
           return ResponseEntity.badRequest().build();
       }catch (Exception e) {
           log.error("Error al agregar el order: {}", e.getMessage(), e);
           return ResponseEntity.internalServerError().build();
       }
    }


    @PatchMapping("/orders/{id}/completed")
    @Operation(
            operationId = "Modificar parcialmente una orden de pedido a estado completed",
            description = "RFC 7386. Operacion de escritura",
            summary = "RFC 7386. Se modifica parcialmente una orden de pedido.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la orden de pedido a modificar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Orders.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No es posible a actualizar el registro.")

    public ResponseEntity<Boolean> pathOrderCompleted(@PathVariable int id) {
        try{
        boolean result = servicesOrder.updateOrderedCompleted(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
        }catch(NumberFormatException e){
            log.error("Formato de Completed order:", e);
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            log.error("Error al Completed el order: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/orders/{id}/cancelled")
    @Operation(
            operationId = "Modificar parcialmente una orden de pedido a estado cancellado",
            description = "RFC 7386. Operacion de escritura",
            summary = "RFC 7386. Se modifica parcialmente una orden de pedido.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la orden de pedido a modificar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Orders.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Orders.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No es posible a actualizar el registro.")
    public ResponseEntity<Boolean> pathOrderCancelled(@PathVariable int id) {
        try{
        boolean result = servicesOrder.updateOrderedCompleted(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
        }catch(NumberFormatException e){
                log.error("Formato de cancelled order:", e);
                return ResponseEntity.badRequest().build();
        }catch (Exception e) {
                log.error("Error al cancelled el order: {}", e.getMessage(), e);
                return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/orders")
    @Operation(
            operationId = "Insertar un nueva order",
            description = "Operacion de escritura",
            summary = "Se crea una orden de pedido  a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del orden de pedido a crear.",
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

    public ResponseEntity<OrderDTO> createOrdered(@RequestBody OrderDTO request) {
        try {
            log.info("Recived request for addOrdered");
            Boolean createdOrder = servicesOrder.CreateOrder(request);

            if (createdOrder != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(request);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }catch(NumberFormatException e){
            log.error("Formato de order inválido:", e);
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            log.error("Error al agregar el order: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
package ms.books.payments.controller.model;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateOrderedRequest {

    @NotNull(message = "totalAmount cannot be null")
    private BigDecimal totalAmount;



}

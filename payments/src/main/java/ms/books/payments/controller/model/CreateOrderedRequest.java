package ms.books.payments.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CreateOrderedRequest {

    @NotNull(message = "totalAmount cannot be null")
    private BigDecimal totalAmount;

    PRI

}

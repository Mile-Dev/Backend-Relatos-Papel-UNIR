package ms.books.payments.controller.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailsRequest {

    @NotNull(message = "Book cannot be null")
    private int bookId;

    @NotNull(message = "Order cannot be null")
    private int orderId;

    @NotNull(message = "Quantity cannot be null")
    private int quantity;

    @NotNull(message = "Quantity cannot be null")
    private BigDecimal price;
}

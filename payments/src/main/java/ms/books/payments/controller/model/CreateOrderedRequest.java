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
public class CreateOrderedRequest {

    @NotNull(message = "totalAmount cannot be null")
    private BigDecimal totalAmount;

    @NotNull(message = "User cannot be null")
    private Integer userId;

}

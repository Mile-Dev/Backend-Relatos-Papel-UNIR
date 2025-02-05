package ms.books.payments.controller.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ms.books.payments.data.utils.PaymentMethodUsers;


import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class CreatePaymentsRequest {
    @NotNull(message = "orderId cannot be null")
    private Integer orderId;

    @NotNull(message="amount cannot be null")
    private BigDecimal amount;

    @NotNull(message="paymentMethodUsers cannot be null")
    private PaymentMethodUsers paymentMethodUsers;




}

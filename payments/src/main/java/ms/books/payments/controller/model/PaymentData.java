package ms.books.payments.controller.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentData {
    private String cardNumber;
    private String expiration;

};

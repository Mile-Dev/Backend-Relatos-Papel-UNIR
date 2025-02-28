package ms.books.payments.controller.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
    public class OrderDTO {
        private PersonalData personalData;
        private PaymentData paymentData;
        private List<CartItem> cart;
        private String total;

    };

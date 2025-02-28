package ms.books.payments.controller.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartItem {
    private String id;
    private String title;
    private int quantity;
    private String price;
    private String subtotal;
};

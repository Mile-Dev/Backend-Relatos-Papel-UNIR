package ms.books.payments.data.model;

import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;
import ms.books.payments.data.utils.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = Consts.USER, nullable = false)
    private Users user;

    @Column(name =  Consts.TOTAL, nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = Consts.STATUS, nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = Consts.CREATEDAT, updatable = false, nullable = false)
    private Date createdAt;
}

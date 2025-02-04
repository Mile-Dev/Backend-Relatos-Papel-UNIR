package ms.books.payments.data.model;

import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;
import ms.books.payments.data.utils.PaymentMethod;
import ms.books.payments.data.utils.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = Consts.ORDERID, nullable = false)
    private Orders orderId;

    @Column(name = Consts.AMOUNT, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = Consts.PAYMENTMETHOD, nullable = false)
    private PaymentMethod paymentMethodUsers;

    @Enumerated(EnumType.STRING)
    @Column(name = Consts.STATUS, nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.Pending;

    @Column(name = Consts.CREATE, updatable = false, nullable = false)
    private LocalDateTime createdAt;
}

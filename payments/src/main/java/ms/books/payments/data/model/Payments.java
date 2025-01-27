package ms.books.payments.data.model;

import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;
import ms.books.payments.data.utils.OrderStatus;
import ms.books.payments.data.utils.PaymentMethod;
import ms.books.payments.data.utils.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Payments")
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
    private Orders order;

    @Column(name = Consts.AMOUNT, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = Consts.STATUS, nullable = false)
    private PaymentMethod paymentmethod;

    @Enumerated(EnumType.STRING)
    @Column(name = Consts.STATUS, nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = Consts.CREATEDAT, updatable = false, nullable = false)
    private Date createdAt;
}

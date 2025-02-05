package ms.books.payments.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;
import ms.books.payments.data.utils.PaymentMethodUsers;
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
    @Column(name = Consts.PAYMENTMETHODUSERS, nullable = false)
    private PaymentMethodUsers paymentMethodUsers;

    @Enumerated(EnumType.STRING)
    @Column(name = Consts.STATUS, nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = Consts.CREATE, updatable = false, nullable = false)
    private LocalDateTime createdAt= LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

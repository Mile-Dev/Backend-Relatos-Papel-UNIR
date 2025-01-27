package ms.books.payments.data.model;

import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "OrderDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = Consts.ORDERID, nullable = false)
    private Orders order;

    @Column(name =  Consts.BOOKID, nullable = false)
    private Integer bookid;

    @Column(name = Consts.QUANTITY, nullable = false)
    private Integer quantity;

    @Column(name = Consts.PRICE, nullable = false)
    private BigDecimal price;

    @Column(name = Consts.CREATEDAT, updatable = false, nullable = false)
    private Date createdAt;
}

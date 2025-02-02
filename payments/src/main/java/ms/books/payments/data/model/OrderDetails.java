package ms.books.payments.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderDetails")
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
    private Integer bookId;

    @Column(name = Consts.QUANTITY, nullable = false)
    private Integer quantity;

    @Column(name = Consts.PRICE, nullable = false)
    private BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = Consts.CREATE, insertable = false, updatable = false, nullable = false)
    private LocalDateTime createdAt;

}

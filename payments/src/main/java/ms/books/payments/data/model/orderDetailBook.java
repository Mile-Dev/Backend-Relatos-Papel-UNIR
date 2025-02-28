package ms.books.payments.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderDetailBook")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class orderDetailBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  Consts.IDBOOK, nullable = false)
    private String IdBook;

    @Column(name =  Consts.TITLE, nullable = false)
    private String title;

    @Column(name =  Consts.QUANTITY, nullable = false)
    private Integer quantity;

    @Column(name =  Consts.PRICE, nullable = false)
    private String price;

    @Column(name =  Consts.SUBTOTAL, nullable = false)
    private String subtotal;


    @ManyToOne
    @JoinColumn(name = Consts.IDORDER, nullable = false)
    private Orders order;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = Consts.CREATE, insertable = false, updatable = false, nullable = false)
    private LocalDateTime createdAt;

}

package ms.books.payments.data.model;

import jakarta.persistence.*;
import lombok.*;
import ms.books.payments.data.utils.Consts;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = Consts.NAME)
    private String name;

    @Column(name = Consts.EMAIL, unique = true)
    private String email;

    @Column(name = Consts.PHONE)
    private String phone;
}

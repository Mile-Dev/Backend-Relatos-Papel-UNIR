package ms.books.payments.controller.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {

    private String name;
    private String email;
    private String phone;
}

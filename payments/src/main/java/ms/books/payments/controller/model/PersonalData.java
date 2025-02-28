package ms.books.payments.controller.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
    public class PersonalData {
    private String name;
    private String email;
    private String phone;

    };

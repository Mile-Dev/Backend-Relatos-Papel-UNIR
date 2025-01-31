package ms.books.payments.data;

import lombok.RequiredArgsConstructor;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.Payments;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {

    private final PaymentJpaRepository repositoryPayment;

    public List<Payments> getPayments() { return  repositoryPayment.findAll();}
}

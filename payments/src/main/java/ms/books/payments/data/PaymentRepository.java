package ms.books.payments.data;

import lombok.RequiredArgsConstructor;
import ms.books.payments.data.model.Payments;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {

    private final PaymentJpaRepository repositoryPayment;

    //Mostrar todos los pagos
    public List<Payments> getPayments() { return  repositoryPayment.findAll();}



    public Payments getPaymentById(int id) {return repositoryPayment.findById(id).orElse(null);}



    public Payments save(Payments payments) {return repositoryPayment.save(payments);}
}

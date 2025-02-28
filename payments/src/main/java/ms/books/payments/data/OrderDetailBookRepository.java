package ms.books.payments.data;

import lombok.RequiredArgsConstructor;
import ms.books.payments.data.model.OrderDetails;
import ms.books.payments.data.model.orderDetailBook;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class OrderDetailBookRepository {
    private final OrderDetailBookJpaRepository orderDetailBookJpaRepository;

    public List<orderDetailBook> getOrders() {
        return orderDetailBookJpaRepository.findAll();
    }

    public List<orderDetailBook> findByOrderId(int orderId) {
        return orderDetailBookJpaRepository.findByOrderId(orderId);
    }

    public orderDetailBook findByOrderIdAndId(int IdOrder, int Id  ) {
        return orderDetailBookJpaRepository.findByOrderIdAndId(IdOrder, Id);
    }


    public orderDetailBook save(orderDetailBook orderDetailBook) {return orderDetailBookJpaRepository.save(orderDetailBook);}


}

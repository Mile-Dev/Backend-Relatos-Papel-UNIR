package ms.books.payments.data;


import ms.books.payments.data.model.orderDetailBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderDetailBookJpaRepository extends JpaRepository<orderDetailBook, Integer>, JpaSpecificationExecutor<orderDetailBook> {

    List<orderDetailBook> findByOrderId(int idOrder);

    orderDetailBook findByOrderIdAndId(int idOrder, int id);

}

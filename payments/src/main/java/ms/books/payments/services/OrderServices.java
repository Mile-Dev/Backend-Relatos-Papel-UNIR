package ms.books.payments.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.books.payments.controller.model.CartItem;
import ms.books.payments.controller.model.CreateOrderedRequest;
import ms.books.payments.controller.model.OrderDTO;
import ms.books.payments.data.OrderDetailBookRepository;
import ms.books.payments.data.OrderRepository;
import ms.books.payments.data.UserRepository;
import ms.books.payments.data.model.Orders;
import ms.books.payments.data.model.Users;
import ms.books.payments.data.model.orderDetailBook;
import ms.books.payments.data.utils.OrderStatus;
import ms.books.payments.exceptions.OrderNotFoundException;
import ms.books.payments.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServices implements IOrderServices {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailBookRepository OrderDetailBookRepository;


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Orders getOrder(int id) {
        return orderRepository.getOrderedById(id);
    }

    @Override
    public List<Orders> getOrdered() {
        return orderRepository.getOrders();
    }

    @Override
    @Transactional
    public Orders createOrdered(CreateOrderedRequest request) {
        Users user = userRepository.getUserById(request.getUserId());
        if (user == null)
            {
             throw new UserNotFoundException("User with ID " + request.getUserId() + " not found");
            }
        Orders order = Orders.builder()
                .user(user)
                .totalAmount(request.getTotalAmount())
                .status(OrderStatus.Pending)
                .build();

        Orders savedOrder = orderRepository.save(order);
        entityManager.refresh(savedOrder);

        return savedOrder;
    }



    @Override
    public Boolean updateOrderedCompleted(int orderId) {
           Orders order = orderRepository.getOrderedById(orderId);
           if (order != null)
           {
               try{
               order.setStatus(OrderStatus.Completed);
               orderRepository.save(order);
               return true;
               }
               catch(Exception e){
                   log.error("Error updating order {}", orderId, e);
                   return null;
               }
           }
           else
           {
               throw new OrderNotFoundException("Order with ID " + orderId + " not found");
           }
    }

    @Override
    public Boolean updateOrderedCancelled(int orderId) {
        Orders order = orderRepository.getOrderedById(orderId);
        if (order != null)
        {
            try{
                order.setStatus(OrderStatus.Cancelled);
                orderRepository.save(order);
                return true;
            }
            catch(Exception e){
                log.error("Error updating order {}", orderId, e);
                return null;
            }
        }
        else
        {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }


    }

    @Override
    public Boolean CreateOrder(OrderDTO orderDTO) {

        try {
            Users users = userRepository.save(
                    Users.builder()
                            .email(orderDTO.getPersonalData().getEmail())
                            .name(orderDTO.getPersonalData().getName())
                            .phone(orderDTO.getPersonalData().getPhone())
                            .build()
            );

            Orders order = orderRepository.save(
                    Orders.builder()
                            .user(users)
                            .totalAmount(new BigDecimal(orderDTO.getTotal()))
                            .status(OrderStatus.Completed)
                            .build()
            );
            for (CartItem cart : orderDTO.getCart()){

                orderDetailBook orderDetail  = OrderDetailBookRepository.save(
                        orderDetailBook.builder()
                                .IdBook(cart.getId())
                                .title(cart.getTitle())
                                .quantity(cart.getQuantity())
                                .price(cart.getPrice())
                                .subtotal(cart.getSubtotal())
                                .build()
                );

            }
            return true;
        }

        catch (Exception e) {
            return false;
        }

        }

    }




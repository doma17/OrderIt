//package inu.amigo.orderIt.domain.order.entity;
//
//import inu.amigo.orderIt.domain.user.entity.User;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "ORDERS")
//@Getter @Setter
//public class Order {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ORDER_ID")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
//
//    @Column(columnDefinition = "TIMESTAMP")
//    private LocalDateTime orderDate;
//
//    @Enumerated
//    private OrderStatus status;
//
//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }
//}

package inu.amigo.orderIt.domain.order;

import inu.amigo.orderIt.domain.user.Member;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;      //주문 회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private Date orderDate;

    @Enumerated
    private OrderStatus status;

    /**
     * 생성 메서드
     * @param member
     * @param orderItems
     * @return order
     */
    public static Order createOrder(Member member, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(new Date());
        return order;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}

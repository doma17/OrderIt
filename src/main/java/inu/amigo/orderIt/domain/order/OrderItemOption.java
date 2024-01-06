package inu.amigo.orderIt.domain.order;

import inu.amigo.orderIt.domain.item.Option;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItemOption {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_OPTION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ITEM_ID")
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTION_ID")
    private Option option;

    private int optionCount;
    private int optionPrice;
}
package inu.amigo.orderIt.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 재료 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ingredient {

    /**
     * 재료 번호 (Auto Increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientId;

    /**
     * 재료 가격
     */
    private int price;

    /**
     * 재료 이름
     */
    private String optionName;

    /**
     * 메뉴 상태 (SOLD_OUT, AVAILABLE)
     */
    private StockStatus stockStatus;

    /**
     * 메뉴 : 재료 = 1 : N (JoinColumn)
     */
    @ManyToOne
    @JoinColumn
    private Menu menu;
}

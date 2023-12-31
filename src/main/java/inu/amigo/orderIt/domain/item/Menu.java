package inu.amigo.orderIt.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Menu {

    /**
     * 메뉴 번호 (Auto Increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    /**
     * 메뉴 이름
     */
    private String name;

    /**
     * 메뉴 설명문 (length = 1000)
     */
    @Column(length = 1000) // description can save 1000 words
    private String description;

    /**
     * 메뉴 가격
     */
    private int price;

    /**
     * 메뉴 칼로리
     */
    private int calorie;

    /**
     * 메뉴 상태 (SOLD_OUT, AVAILABLE)
     */
    private StockStatus stockStatus;

    /**
     * 메뉴 : 메뉴 그룹 = N : M
     * 메뉴들은 여러 개의 메뉴 그룹에 소속될 수 있다.
     */
    @ManyToMany(mappedBy = "")
    private List<MenuGroup> menuGroups = new ArrayList<>();

    /**
     * 메뉴 : 세트 메뉴 = 1 : N (JoinColumn)
     * 세틑 메뉴는 여러 개의 메뉴를 가진다.
     */
    @ManyToOne
    @JoinColumn
    private Comb comb;

    /**
     * 메뉴 : 재료 = 1 : N (Cascade = ALL)
     * 메뉴는 여러 개의 재료를 가진다.
     */
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    /**
     * 메뉴 : 이미지 = 1 : 1
     * 하나의 메뉴는 하나의 이미지를 가진다.
     */
    @OneToOne
    private Image image;
}

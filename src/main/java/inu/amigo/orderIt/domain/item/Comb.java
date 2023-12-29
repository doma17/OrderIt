package inu.amigo.orderIt.domain.item;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 세트 메뉴 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comb {

    /**
     * 세트 메뉴 번호 (Auto Increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long combId;

    /**
     * 세트 메뉴 가격
     */
    private int combPrice;

    /**
     * 세트에 포함된 메뉴
     */
    @OneToMany(mappedBy = "combMenu", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    /**
     * 세트 메뉴 이미지
     */
    @OneToOne
    private Image image;
}

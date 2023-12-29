package inu.amigo.orderIt.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴 그룹 엔티티
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class MenuGroup {

    /**
     * 그룹 번호 (Auto Increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    /**
     * 메뉴 그룹 : 메뉴 = 1 : N
     */
    @OneToMany
    private List<Menu> menus = new ArrayList<>();

    /**
     * 메뉴 그룹 : 세트 메뉴 = 1 : N
     */
    @OneToMany
    private List<Comb> combs = new ArrayList<>();

    /**
     * 세일 시간 : 메뉴 그룹 = N : 1
     */
    @ManyToOne
//    @JoinColumn
    private SaleTime saleTime;

}

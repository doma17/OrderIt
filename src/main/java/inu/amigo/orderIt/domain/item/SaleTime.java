package inu.amigo.orderIt.domain.item;

import inu.amigo.orderIt.domain.item.MenuGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 세일 시간 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SaleTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleTimeId;

    private String dayOfWeek; // 판매 요일
    private LocalTime startTime; // 판매 시작 시간
    private LocalTime endTime; // 판매 종료 시간

    @OneToMany
    private List<MenuGroup> menuGroups = new ArrayList<>();
}

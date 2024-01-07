package inu.amigo.orderIt.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Option {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    private String name;
    private int price;
    private String description;

    // Iterable인지 아닌지 체크 필요여부 확인
    // 예시1 -> hot only, cold only, both
    // 예시2 -> 저지방 우유 True / False
    // 아니면 description을 통해서 OrderItemOption과 연동
    // 1 = both, 2 = hot only, 3 = cold only
}
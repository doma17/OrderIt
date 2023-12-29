package inu.amigo.orderIt.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Comb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long combId;

    private int combPrice;

    @OneToMany(mappedBy = "combMenu", cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    @OneToOne
    private Image image;
}

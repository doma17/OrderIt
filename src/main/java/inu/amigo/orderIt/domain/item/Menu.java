package inu.amigo.orderIt.domain.item;

import inu.amigo.orderIt.domain.MenuGroup;
import inu.amigo.orderIt.domain.StockStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private String name;
    private String description;

    private int price;
    private int calorie;

    private StockStatus stockStatus;

    @ManyToMany(mappedBy = "")
    private List<MenuGroup> menuGroups = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Comb comb;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToOne
    private Image image;
}

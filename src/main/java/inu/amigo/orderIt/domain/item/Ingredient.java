package inu.amigo.orderIt.domain.item;

import inu.amigo.orderIt.domain.item.Menu;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientId;

    private int price;
    private String optionName;

    @ManyToOne
    @JoinColumn
    private Menu menu;
}

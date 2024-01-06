package inu.amigo.orderIt.dto;

import inu.amigo.orderIt.domain.item.Menu;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private String imagePath;
}

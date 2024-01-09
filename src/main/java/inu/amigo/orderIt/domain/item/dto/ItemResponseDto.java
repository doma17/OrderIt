package inu.amigo.orderIt.domain.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemResponseDto {
    private Long item_id;
    private String name;
    private int price;
    private String imagePath;
}

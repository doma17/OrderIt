package inu.amigo.orderIt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemResponseDto {
    private String name;
    private int price;
    private String imagePath;
}
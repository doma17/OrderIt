package inu.amigo.orderIt.domain.item.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemRequestDto {
    private String name;
    private int price;
    private String imagePath;
    private List<Long> options;
}

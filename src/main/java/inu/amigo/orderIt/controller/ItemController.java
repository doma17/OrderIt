package inu.amigo.orderIt.controller;

import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.Menu;
import inu.amigo.orderIt.domain.item.Option;
import inu.amigo.orderIt.dto.ItemDto;
import inu.amigo.orderIt.exception.FileValidationException;
import inu.amigo.orderIt.service.ItemService;
import inu.amigo.orderIt.service.OptionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@Tag(name = "Item API")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 전체 아이템 목록 조회
    @GetMapping()
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // 아이템 등록
    @PostMapping()
    public Item createItem(@ModelAttribute ItemDto itemDto, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            return itemService.createItem(itemDto, imageFile);
        } catch (IOException | FileValidationException e) {
            // 예외 발생 시 예외 메시지를 반환
            throw new RuntimeException(e.getMessage());
        }
    }

    // 특정 메뉴의 아이템 목록 조회
    @GetMapping("/{menu}")
    public List<Item> getItemsByMenu(@PathVariable Menu menu) {
        return itemService.getItemsByMenu(menu);
    }
}

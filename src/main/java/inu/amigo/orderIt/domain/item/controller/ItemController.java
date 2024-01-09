package inu.amigo.orderIt.domain.item.controller;

import inu.amigo.orderIt.domain.item.dto.ItemResponseDto;
import inu.amigo.orderIt.domain.item.service.ItemService;
import inu.amigo.orderIt.domain.item.entity.Menu;
import inu.amigo.orderIt.domain.item.dto.ItemRequestDto;
import inu.amigo.orderIt.domain.item.exception.FileValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Operation(description = "모든 Item 조회")
    public List<ItemResponseDto> getAllItems() {
        return itemService.getAllItems();
    }

    // 아이템 등록
    @PostMapping()
    @Operation(description = "Item 생성")
    public ItemResponseDto createItem(@ModelAttribute ItemRequestDto itemRequestDto, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            return itemService.createItem(itemRequestDto, imageFile);
        } catch (IOException | FileValidationException e) {
            // 예외 발생 시 예외 메시지를 반환
            throw new RuntimeException(e.getMessage());
        }
    }

    // 특정 메뉴의 아이템 목록 조회
    @GetMapping("/{menu}")
    @Operation(description = "특정 메뉴 카테고리의 Item 조회")
    public List<ItemResponseDto> getItemsByMenu (@PathVariable Menu menu) {
        return itemService.getItemsByMenu(menu);
    }

    @DeleteMapping("delete/{itemId}")
    @Operation(description = "ItemId로 Item 삭제")
    public ResponseEntity<String> deleteItemById (@PathVariable Long itemId) {
        try {
            itemService.deleteItem(itemId);

            return new ResponseEntity<>("Member registered successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

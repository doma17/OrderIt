package inu.amigo.orderIt.controller;

import inu.amigo.orderIt.domain.item.Coffee;
import inu.amigo.orderIt.domain.item.Dessert;
import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.NonCoffee;
import inu.amigo.orderIt.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Item API", description = "Item API")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> save(@RequestBody Item item,
                               @RequestPart("image") MultipartFile imageFile) {
        try {
            itemService.save(item, imageFile);
            return ResponseEntity.ok("Item and image saved successfully.");
        } catch (IOException e) {
            String errorMessage = "Error saving item and image: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/items")
    public List<? extends Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/coffees")
    public List<Coffee> getAllCoffees() {
        return itemService.getAllCoffees();
    }

    @GetMapping("/non-coffees")
    public List<NonCoffee> getAllNonCoffees() {
        return itemService.getAllNonCoffees();
    }

    @GetMapping("/desserts")
    public List<Dessert> getAllDesserts() {
        return itemService.getAllDesserts();
    }

}

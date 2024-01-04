package inu.amigo.orderIt.controller;

import inu.amigo.orderIt.domain.item.Coffee;
import inu.amigo.orderIt.domain.item.Dessert;
import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.NonCoffee;
import inu.amigo.orderIt.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/items")
@Tag(name = "Item API", description = "Item 반환하는 API")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public List<Item> getAllItems() {
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

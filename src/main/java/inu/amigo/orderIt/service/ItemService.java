package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Coffee;
import inu.amigo.orderIt.domain.item.Dessert;
import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.NonCoffee;
import inu.amigo.orderIt.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<NonCoffee> getAllNonCoffees() {
        return itemRepository.findAllNonCoffees();
    }

    public List<Coffee> getAllCoffees() {
        return itemRepository.findAllCoffees();
    }

    public List<Dessert> getAllDesserts() {
        return itemRepository.findAllDesserts();
    }
}


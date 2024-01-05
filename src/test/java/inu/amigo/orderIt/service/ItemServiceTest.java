package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Coffee;
import inu.amigo.orderIt.domain.item.Dessert;
import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.NonCoffee;
import inu.amigo.orderIt.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    public ItemServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllItems() {
        // create fake data
        List<Item> fakeItemList = Arrays.asList(
                new Coffee(), new NonCoffee(), new Dessert()
        );

        // Mock Setting
        when(itemRepository.findAll()).thenReturn(fakeItemList);

        // Test
        List<? extends Item> result = itemService.getAllItems();

        // Result
        assertEquals(fakeItemList.size(), result.size());
    }

    @Test
    void getAllNonCoffees() {
        // create fake data
        List<NonCoffee> fakeNonCoffeeList = Arrays.asList(
                new NonCoffee(), new NonCoffee()
        );

        // Mock Setting
        when(itemRepository.findAllNonCoffees()).thenReturn(fakeNonCoffeeList);

        // Test
        List<NonCoffee> result = itemService.getAllNonCoffees();

        // Result
        assertEquals(fakeNonCoffeeList.size(), result.size());
    }

    @Test
    void getAllCoffees() {
        // create fake data
        List<Coffee> fakeCoffeeList = Arrays.asList(
                new Coffee(), new Coffee(), new Coffee()
        );

        // Mock Setting
        when(itemRepository.findAllCoffees()).thenReturn(fakeCoffeeList);

        // Test
        List<Coffee> result = itemService.getAllCoffees();

        // Result
        assertEquals(fakeCoffeeList.size(), result.size());
    }

    @Test
    void getAllDesserts() {
        // create fake data
        List<Dessert> fakeDessertList = Arrays.asList(
                new Dessert(), new Dessert()
        );

        // Mock Setting
        when(itemRepository.findAllDesserts()).thenReturn(fakeDessertList);

        // Test
        List<Dessert> result = itemService.getAllDesserts();

        // Result
        assertEquals(fakeDessertList.size(), result.size());
    }
}

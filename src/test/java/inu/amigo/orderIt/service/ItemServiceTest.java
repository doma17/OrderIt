package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Coffee;
import inu.amigo.orderIt.domain.item.Dessert;
import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.NonCoffee;
import inu.amigo.orderIt.repository.ItemRepository;
import inu.amigo.orderIt.service.ItemService;
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
        // 가짜 데이터 생성
        List<Item> fakeItemList = Arrays.asList(
                new Coffee(), new NonCoffee(), new Dessert()
        );

        // Mock 설정
        when(itemRepository.findAll()).thenReturn(fakeItemList);

        // 테스트
        List<Item> result = itemService.getAllItems();

        // 검증
        assertEquals(fakeItemList.size(), result.size());
    }

    @Test
    void getAllNonCoffees() {
        // 가짜 데이터 생성
        List<NonCoffee> fakeNonCoffeeList = Arrays.asList(
                new NonCoffee(), new NonCoffee()
        );

        // Mock 설정
        when(itemRepository.findAllNonCoffees()).thenReturn(fakeNonCoffeeList);

        // 테스트
        List<NonCoffee> result = itemService.getAllNonCoffees();

        // 검증
        assertEquals(fakeNonCoffeeList.size(), result.size());
    }

    @Test
    void getAllCoffees() {
        // 가짜 데이터 생성
        List<Coffee> fakeCoffeeList = Arrays.asList(
                new Coffee(), new Coffee(), new Coffee()
        );

        // Mock 설정
        when(itemRepository.findAllCoffees()).thenReturn(fakeCoffeeList);

        // 테스트
        List<Coffee> result = itemService.getAllCoffees();

        // 검증
        assertEquals(fakeCoffeeList.size(), result.size());
    }

    @Test
    void getAllDesserts() {
        // 가짜 데이터 생성
        List<Dessert> fakeDessertList = Arrays.asList(
                new Dessert(), new Dessert()
        );

        // Mock 설정
        when(itemRepository.findAllDesserts()).thenReturn(fakeDessertList);

        // 테스트
        List<Dessert> result = itemService.getAllDesserts();

        // 검증
        assertEquals(fakeDessertList.size(), result.size());
    }
}

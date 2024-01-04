package inu.amigo.orderIt.repository;
import inu.amigo.orderIt.domain.item.*;
import inu.amigo.orderIt.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindAllCoffees() {
        // Save a Coffee item to the database
        Coffee coffee = new Coffee();
        coffee.setName("Espresso");
        coffee.setPrice(3);
        coffee.setTempOption(TempOption.HOT_ONLY);
        coffee.setShotAllow(true);
        itemRepository.save(coffee);

        // Fetch all coffee items from the repository
        List<Coffee> coffees = itemRepository.findAllCoffees();

        // Ensure that the list contains the saved coffee
        assertEquals(1, coffees.size());
        assertEquals("Espresso", coffees.get(0).getName());
    }

    // Similar tests can be written for findAllNonCoffees and findAllDesserts

    @Test
    public void testFindAllItems() {
        // Save items of different types to the database
        Coffee coffee = new Coffee();
        coffee.setName("Latte");
        coffee.setPrice(4);
        coffee.setTempOption(TempOption.HOT_ONLY);
        coffee.setShotAllow(true);
        itemRepository.save(coffee);

        NonCoffee nonCoffee = new NonCoffee();
        nonCoffee.setName("Iced Tea");
        nonCoffee.setPrice(3);
        nonCoffee.setTempOption(TempOption.ICE_ONLY);
        itemRepository.save(nonCoffee);

        Dessert dessert = new Dessert();
        dessert.setName("Chocolate Cake");
        dessert.setPrice(5);
        itemRepository.save(dessert);

        // Fetch all items from the repository
        List<Item> items = itemRepository.findAll();

        // Ensure that the list contains all saved items
        assertEquals(3, items.size());
    }
}

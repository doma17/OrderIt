package inu.amigo.orderIt.repository;

import inu.amigo.orderIt.domain.item.Coffee;
import inu.amigo.orderIt.domain.item.Dessert;
import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.NonCoffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    /**
     * NonCoffee 조회
     */
    @Query("SELECT n FROM NonCoffee n")
    List<NonCoffee> findAllNonCoffees();

    /**
     * Coffee 조회
     */
    @Query("SELECT c FROM Coffee c")
    List<Coffee> findAllCoffees();

    /**
     * Dessert 조회
     */
    @Query("SELECT d FROM Dessert d")
    List<Dessert> findAllDesserts();

}

package inu.amigo.orderIt.item.repository;

import inu.amigo.orderIt.item.entity.Item;
import inu.amigo.orderIt.item.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByMenu(Menu menu);
}

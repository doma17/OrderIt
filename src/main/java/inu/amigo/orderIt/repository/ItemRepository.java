package inu.amigo.orderIt.repository;

import inu.amigo.orderIt.domain.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Item item) {
        if (item.getId() == null)
            em.persist(item);
        else
            em.merge(item);
    }

    public Item findOne(long id) { return em.find(Item.class, id); }
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}

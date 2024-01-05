package inu.amigo.orderIt.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("D")
@Getter @Setter
public class Dessert extends Item {
    @Override
    public String toString() {
        return "Dessert{}";
    }
}

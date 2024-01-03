package inu.amigo.orderIt.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("N")
@Getter @Setter
public class NonCoffee extends Item {
    TempOption tempOption;

    @Override
    public String toString() {
        return "NonCoffee{}";
    }
}

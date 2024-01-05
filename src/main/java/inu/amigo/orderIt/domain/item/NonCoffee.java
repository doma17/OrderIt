package inu.amigo.orderIt.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("N")
@Getter @Setter
public class NonCoffee extends Item {

    @Enumerated(EnumType.STRING)
    private TempOption tempOption;

    @Override
    public String toString() {
        return "NonCoffee{}";
    }
}

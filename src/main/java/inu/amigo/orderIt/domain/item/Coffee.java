package inu.amigo.orderIt.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("C")
@Getter @Setter @ToString
public class Coffee extends Item{
    @Enumerated(EnumType.STRING)
    private TempOption tempOption;

    private boolean shotAllow; // 샷추가 옵션 허용
    private boolean decafAllow; // 디카페인 옵션 허용
    private boolean sizeUpAllow; // 사이즈업 옵션 허용
}
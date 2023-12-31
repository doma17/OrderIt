package inu.amigo.orderIt.domain.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * 이미지 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {

    /**
     * 이미지 번호 (Auto Increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;


    /**
     * 이미지 이름
     */
    private String imageName;
}

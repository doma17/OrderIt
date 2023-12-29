package inu.amigo.orderIt.repository;

import inu.amigo.orderIt.domain.item.Image;
import inu.amigo.orderIt.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void saveAndFindById() {
        // Given
        Image image = new Image();
        image.setUrl("/path/to/image.jpg");

        // When
        Image savedImage = imageRepository.save(image);
        Image foundImage = imageRepository.findByImageId(savedImage.getImageId());

        // Then
        assertThat(foundImage).isNotNull();
        assertThat(foundImage.getImageId()).isEqualTo(savedImage.getImageId());
        assertThat(foundImage.getUrl()).isEqualTo("/path/to/image.jpg");
    }

    @Test
    public void findByUuid() {
        // Given
        Image image = new Image();
        UUID uuid = UUID.randomUUID();
        image.setUuid(uuid);
        image.setUrl("/path/to/another-image.jpg");

        // When
        Image savedImage = imageRepository.save(image);
        Image foundImage = imageRepository.findByUuid(uuid);

        // Then
        assertThat(foundImage).isNotNull();
        assertThat(foundImage.getImageId()).isEqualTo(savedImage.getImageId());
        assertThat(foundImage.getUrl()).isEqualTo("/path/to/another-image.jpg");
    }
}

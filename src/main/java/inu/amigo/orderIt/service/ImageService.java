package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Image;
import inu.amigo.orderIt.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }


    public Image getImageById(Long imageId) {
        return imageRepository.getReferenceById(imageId);
    }
}

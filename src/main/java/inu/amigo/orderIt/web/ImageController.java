package inu.amigo.orderIt.web;

import inu.amigo.orderIt.domain.item.Image;
import inu.amigo.orderIt.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{imageId}")
    public String getImagePath(@PathVariable Long imageId) {
        // 이미지 ID를 이용해 요청시 반환
        Image image = imageService.getImageById(imageId);

        return "/static/images/" + image.getImageId();
    }
}

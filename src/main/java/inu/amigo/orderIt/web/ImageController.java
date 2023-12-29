package inu.amigo.orderIt.web;

import inu.amigo.orderIt.domain.item.Image;
import inu.amigo.orderIt.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public String upload(@RequestPart MultipartFile imageFile) {
        Image saveImage = imageService.saveImage(imageFile);
        return saveImage.getUrl(); // 이미지 URL 반환
    }

    @GetMapping("/{imageId}")
    public String getImagePath(@PathVariable Long imageId) {
        Image saveImage = imageService.getImageById(imageId);
        return saveImage.getUrl(); // 이미지 URL 반환
    }
}

package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Image;
import inu.amigo.orderIt.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getImageById(Long imageId) {
        return imageRepository.findByImageId(imageId);
    }

    public Image getImageByUuid(UUID uuid) {
        return imageRepository.findByUuid(uuid);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image saveImage(MultipartFile imageFile) {
        // 이미지 로컬 저장 로직
        String imageUrl = saveImageLocally(imageFile);

        // Image 엔티티 생성
        Image image = new Image();
        image.setUrl(imageUrl);

        // 이미지 저장
        return imageRepository.save(image);
    }

    /**
     * 실제 이미지 저장 로적 (로컬 디렉토리에 저장)
     * 저장 위치 = /static/images
     * @param imageFile
     * @return filePath
     */
    private String saveImageLocally(MultipartFile imageFile) {
        // 저장 위치를 /static/images 로 설정
        String directory = "/static/images/";
        String filename = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();

        // 실제 저장 경로
        String filePath = directory + filename;

        // 저장 로직 구현

        return filePath;
    }

    public void deleteImage(Long imageId) {
        // 이미지 삭제 전 비즈니스 로직 추가 필요
        imageRepository.deleteById(imageId);
    }

}

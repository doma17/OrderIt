package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Image;
import inu.amigo.orderIt.repository.ImageRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageInitService {

    private final ImageRepository imageRepository;

    @Value("${image.base-path}")
    private String imagePath;

    @Autowired
    public ImageInitService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * 초기 설정시 이미지 디렉토리 내의 모든 이미지 파일 자동으로 등록
     */
    @PostConstruct
    @Transactional
    public void init() {
        try {
            // 이미지 디렉토리 경로
            Path imageDirectory = Paths.get(imagePath);

            // 디렉토리에 있는 파일 읽어오기
            Files.list(imageDirectory)
                    .filter(Files::isRegularFile)
                    .forEach(this::saveImageToDB);
        } catch (IOException e) {
            // 예외 처리 로직 추가 필요
            e.printStackTrace();
        }
    }

    private void saveImageToDB(Path imagePath) {
        try {
            // 이미지 이름 추출
            String imageName = imagePath.getFileName().toString();

            // 이미지 이름 DB 등록
            Image image = new Image();
            image.setImageName(imageName);

            // Repo에 저장
            imageRepository.save(image);
        } catch (Exception e) {
            // 예외 처리 로직 추가
            e.printStackTrace();
        }
    }
}

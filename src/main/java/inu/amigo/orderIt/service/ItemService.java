package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Coffee;
import inu.amigo.orderIt.domain.item.Dessert;
import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.NonCoffee;
import inu.amigo.orderIt.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Value("${image.base-path}")
    private String uploadPath; // 파일이 저장될 로컬 경로

    public void save(Item item, MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            log.error("Image file is empty");
            throw new IllegalArgumentException("Image file is empty");
        }

        String savedImagePath = saveImage(imageFile, item.getId());
        item.setImagePath(savedImagePath);

        itemRepository.save(item);
    }

    public String saveImage(MultipartFile file, Long itemId) throws IOException {
        if (file.getOriginalFilename() == null) {
            log.error("Original filename is NULL");
            throw new IllegalArgumentException("Original filename is NULL");
        }

        String originalName = file.getOriginalFilename();
        String fileName = itemId + "_" + StringUtils.cleanPath(originalName);

        // 로컬 경로에 파일 저장
        Path uploadLocation = Paths.get(uploadPath);
        Files.createDirectories(uploadLocation); // 디렉토리가 존재하지 않으면 생성
        Path filePath = uploadLocation.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        log.info("Uploaded file path: {}", filePath);
        return filePath.toString(); // 저장된 파일의 경로 반환
    }

    public List<? extends Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<NonCoffee> getAllNonCoffees() {
        return itemRepository.findAllNonCoffees();
    }

    public List<Coffee> getAllCoffees() {
        return itemRepository.findAllCoffees();
    }

    public List<Dessert> getAllDesserts() {
        return itemRepository.findAllDesserts();
    }
}


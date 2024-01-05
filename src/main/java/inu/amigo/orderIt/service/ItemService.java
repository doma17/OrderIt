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
            log.error("imageFile.isEmpty()");
            throw new IOException();
        }

    }

    public String saveImage(MultipartFile file, String itemId) throws IOException {
        if (file.getOriginalFilename() == null) {
            log.error("file.getOriginalFilename() is NULL");
            throw new IOException();
        }
        String originalName = file.getOriginalFilename();
        String fileName = itemId + "_" + StringUtils.cleanPath(originalName);

        // 로컬 경로에 파일 저장
        Path uploadLocation = Paths.get(uploadPath);
        Path filePath = uploadLocation.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        log.info("uploadPath + fileName = %s", (uploadPath + fileName));
        return uploadPath + fileName; // 저장된 파일의 이름 반환
    }

    public List<Item> getAllItems() {
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


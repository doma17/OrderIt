package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.Menu;
import inu.amigo.orderIt.domain.item.Option;
import inu.amigo.orderIt.dto.ItemDto;
import inu.amigo.orderIt.exception.FileValidationException;
import inu.amigo.orderIt.repository.ItemRepository;
import inu.amigo.orderIt.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Value("${image.base-path}")
    private String imageBasePath;

    // 이미지 파일의 허용된 확장자 목록
    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png");

    // 이미지 파일의 최대 크기 (10MB로 설정)
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByMenu(Menu menu) {
        return itemRepository.findByMenu(menu);
    }



    public Item createItem(ItemDto itemDto, MultipartFile imageFile) throws IOException {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());

        // 이미지 유효성 검사
        validateImage(imageFile);

        // 이미지를 서버의 로컬에 저장
        String imagePath = saveImageLocal(imageFile);
        item.setImagePath(imagePath);

        // 아이템을 저장하고 저장된 아이템을 반환
        return itemRepository.save(item);
    }

    private void validateImage(MultipartFile imageFile) {
        // 파일이 존재하는지 확인
        if (imageFile.isEmpty()) {
            throw new FileValidationException("Image file is empty.");
        }

        // 파일 확장자 확인
        String fileExtension = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
        if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            throw new FileValidationException("Only " + String.join(", ", ALLOWED_EXTENSIONS) + " files are allowed.");
        }

        // 파일 크기 확인
        if (imageFile.getSize() > MAX_FILE_SIZE) {
            throw new FileValidationException("File size exceeds the maximum limit (10MB).");
        }
    }

    private String saveImageLocal(MultipartFile imageFile) throws IOException {
        // 서버의 로컬에 이미지를 저장할 경로 설정
        String uploadDir = imageBasePath;
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        // 파일 이름 생성
        String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(imageFile.getOriginalFilename());

        // 이미지를 서버의 로컬에 복사
        Path imagePath = Path.of(uploadPath.getAbsolutePath() + File.separator + fileName);
        Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        // 이미지의 상대 경로를 반환
        return fileName;
    }
}

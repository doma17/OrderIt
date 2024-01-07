package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.Menu;
import inu.amigo.orderIt.domain.item.Option;
import inu.amigo.orderIt.dto.ItemRequestDto;
import inu.amigo.orderIt.dto.ItemResponseDto;
import inu.amigo.orderIt.exception.FileValidationException;
import inu.amigo.orderIt.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    private final OptionsService optionsService;

    @Value("${image.base-path}")
    private String imageBasePath;

    // 이미지 파일의 허용된 확장자 목록
    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png");

    // 이미지 파일의 최대 크기 (10MB로 설정)
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    @Autowired
    public ItemService(ItemRepository itemRepository, OptionsService optionsService) {
        this.itemRepository = itemRepository;
        this.optionsService = optionsService;
    }

    /**
     * 모든 Item 반환 (Option 포함 X)
     */
    public List<ItemResponseDto> getAllItems() {
        log.trace("getAllItems() 실행");
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        List<Item> itemList = itemRepository.findAll();

        for (Item item : itemList) {
            // Item -> ResponseDto Mapping
            ItemResponseDto itemResponseDto = convertItemToResponseDto(item);
            itemResponseDtoList.add(itemResponseDto);
        }
        return itemResponseDtoList;
    }

    /**
     * 메뉴 카테고리에 따른 Item 리스트 반환 로직
     */
    public List<ItemResponseDto> getItemsByMenu(Menu menu) {
        log.trace("getItemsByMenu(Menu menu) 실행");
        log.debug("Request Menu : {}", menu.toString());
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        List<Item> itemList = itemRepository.findByMenu(menu);

        for (Item item : itemList) {
            // Item -> ResponseDto Mapping
            ItemResponseDto itemResponseDto = convertItemToResponseDto(item);
            itemResponseDtoList.add(itemResponseDto);
        }
        return itemResponseDtoList;
    }

    /**
     * 아이템 생성 로직
     */
    public ItemResponseDto createItem(ItemRequestDto itemRequestDto, MultipartFile imageFile) throws IOException {
        // ItemDto -> Item Mapping
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        item.setPrice(itemRequestDto.getPrice());
        log.trace("ItemRequestDto to Item Mapping 완료");

        // Option ID 리스트를 기반으로 Option 엔티티들을 조회
        List<Option> options = optionsService.getOptionsByIds(itemRequestDto.getOptions());

        // Item 엔티티에 options 설정
        item.setOptions(options);

        // 이미지 유효성 검사
        validateImage(imageFile);

        // 이미지를 서버의 로컬에 저장
        String imagePath = saveImageLocal(imageFile);
        item.setImagePath(imagePath);

        // 아이템을 저장하고 저장된 아이템을 반환
        item = itemRepository.save(item);
        // Dto로 Mapping해서 반환
        return convertItemToResponseDto(item);
    }

    /**
     * 이미지 유효성 검사 로직 메서드
     */
    private void validateImage(MultipartFile imageFile) {
        // 파일이 존재하는지 확인
        if (imageFile.isEmpty()) {
            log.error("이미지 파일이 비어있음");
            throw new FileValidationException("Image file is empty.");
        }

        // 파일 확장자 확인
        String fileExtension = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
        if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
            log.error("이미지 확장자가 아님");
            throw new FileValidationException("Only " + String.join(", ", ALLOWED_EXTENSIONS) + " files are allowed.");
        }

        // 파일 크기 확인
        if (imageFile.getSize() > MAX_FILE_SIZE) {
            log.error("이미지 사이즈가 10MB가 넘음");
            throw new FileValidationException("File size exceeds the maximum limit (10MB).");
        }
        log.info("이미지 유효성 확인 완료");
    }

    /**
     * 이미지 로컬 저장 내부 로직 메서드
     */
    private String saveImageLocal(MultipartFile imageFile) throws IOException {
        log.trace("이미지 저장 메서드 실행");
        // 서버의 로컬에 이미지를 저장할 경로 설정
        String uploadDir = imageBasePath;
        File uploadPath = new File(uploadDir);

        // 업로드 경로가 존재하지 않으면 디렉토리 생성
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
            log.debug("이미지 디렉토리가 존재 하지 않음");
        }

        // 이미지의 상대 경로를 반환
        return saveFile(imageFile, uploadPath);
    }

    /**
     * 파일 저장 내부 로직 메서드
     */
    private String saveFile(MultipartFile imageFile, File uploadPath) throws IOException {
        log.trace("파일 저장 메서드 실행");
        // 파일 이름 생성
        String fileName = System.currentTimeMillis() + "_" + Objects.requireNonNull(imageFile.getOriginalFilename());

        // 이미지를 서버의 로컬에 복사
        Path imagePath = Path.of(uploadPath.getAbsolutePath() + File.separator + fileName);
        Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    // Item -> ResponseDto Mapping

    /**
     * Item to ItemResponseDto Mapper
     * + ModelMapper, @Mapping 어노테이션 방법으로 수정?
     */
    private ItemResponseDto convertItemToResponseDto(Item item) {
        log.trace("Item to ItemResponseDto Mapper 실행");
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.setItem_id(item.getId());
        itemResponseDto.setName(item.getName());
        itemResponseDto.setPrice(item.getPrice());
        itemResponseDto.setImagePath(item.getImagePath());
        return itemResponseDto;
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}

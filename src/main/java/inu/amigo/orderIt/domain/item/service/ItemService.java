package inu.amigo.orderIt.domain.item.service;

import inu.amigo.orderIt.domain.item.dto.ItemResponseDto;
import inu.amigo.orderIt.domain.item.repository.ItemRepository;
import inu.amigo.orderIt.domain.item.entity.Item;
import inu.amigo.orderIt.domain.item.entity.Menu;
import inu.amigo.orderIt.domain.item.entity.Option;
import inu.amigo.orderIt.domain.item.dto.ItemRequestDto;
import inu.amigo.orderIt.domain.item.exception.FileValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    private final OptionsService optionsService;
    private final ImageService imageService;

    @Autowired
    public ItemService(ItemRepository itemRepository, OptionsService optionsService, ImageService imageService) {
        this.itemRepository = itemRepository;
        this.optionsService = optionsService;
        this.imageService = imageService;
    }

    /**
     * 모든 Item을 반환합니다. (Option은 포함되지 않습니다.)
     *
     * @return 모든 Item의 정보를 담은 ItemResponseDto 리스트
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
     * 특정 메뉴 카테고리에 따른 Item 리스트를 반환합니다.
     *
     * @param menu 조회할 메뉴 카테고리
     * @return 해당 메뉴에 속하는 Item의 정보를 담은 ItemResponseDto 리스트
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
     *
     * @param itemRequestDto 생성할 아이템의 정보를 담은 DTO
     * @param imageFile      업로드된 이미지 파일
     * @return 생성된 아이템의 정보를 담은 ItemResponseDto
     * @throws IOException               이미지 파일 처리 중 발생하는 예외
     * @throws FileValidationException   이미지 유효성 검사 실패 시 예외
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

        // 이미지 유효성 검사 및 저장
        String imagePath = imageService.saveImage(imageFile);
        item.setImagePath(imagePath);

        // 아이템을 저장하고 저장된 아이템을 반환
        item = itemRepository.save(item);
        // Dto로 Mapping해서 반환
        return convertItemToResponseDto(item);
    }

    /**
     * Item 엔티티를 ItemResponseDto로 변환하는 메서드입니다.
     * ModelMapper 또는 @Mapping 어노테이션 등을 사용하여 수정할 수 있습니다.
     *
     * @param item 변환할 Item 엔티티
     * @return 변환된 ItemResponseDto
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

    /**
     * 주어진 itemId에 해당하는 Item을 삭제합니다.
     *
     * @param itemId 삭제할 Item의 식별자
     */
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}

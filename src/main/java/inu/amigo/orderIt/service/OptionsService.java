package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.Option;
import inu.amigo.orderIt.repository.ItemRepository;
import inu.amigo.orderIt.repository.OptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OptionsService {

    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public OptionsService(ItemRepository itemRepository, OptionRepository optionRepository) {
        this.itemRepository = itemRepository;
        this.optionRepository = optionRepository;
    }

    /**
     * 모든 옵션을 반환합니다.
     *
     * @return 모든 Option 엔티티를 담은 리스트
     */
    public List<Option> getAllOptions() {
        log.trace("getAllOptions() 실행");
        return optionRepository.findAll();
    }

    /**
     * 주어진 id 리스트에 해당하는 옵션들을 반환합니다.
     *
     * @param optionIds 조회할 옵션들의 id 리스트
     * @return 조회된 Option 엔티티를 담은 리스트
     */
    public List<Option> getOptionsByIds(List<Long> optionIds) {
        log.trace("getOptionsByIds(List<Long> optionIds) 실행");
        return optionRepository.findAllById(optionIds);
    }

    /**
     * 주어진 Option을 생성하고 저장합니다.
     *
     * @param option 저장할 Option 엔티티
     * @return 저장된 Option 엔티티
     */
    public Option createOption(Option option) {
        log.trace("createOption(Option option) 실행");
        return optionRepository.save(option);
    }

    /**
     * 주어진 Item에 속한 Option 목록을 반환합니다.
     *
     * @param itemId 조회할 Item의 식별자
     * @return Item에 속한 Option 엔티티를 담은 리스트
     * @throws IllegalArgumentException 주어진 itemId에 해당하는 Item이 존재하지 않을 경우 발생하는 예외
     */
    public List<Option> getOptionsByItemId(Long itemId) {
        log.trace("getOptionsByItemId(Long itemId) 실행");
        // 아이템 ID로 아이템을 조회
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalItem.isPresent()) {
            // 아이템이 존재하면 해당 아이템의 옵션 목록 반환
            return optionalItem.get().getOptions();
        } else {
            // 아이템이 존재하지 않으면 예외 발생
            log.error("아이템이 존재하지 않음!");
            throw new IllegalArgumentException("Item with ID " + itemId + " not found.");
        }
    }

    /**
     * 주어진 optionId에 해당하는 Option을 반환합니다.
     *
     * @param optionId 조회할 Option의 식별자
     * @return 조회된 Option 엔티티
     * @throws IllegalArgumentException 주어진 optionId에 해당하는 Option이 존재하지 않을 경우 발생하는 예외
     */
    public Option getOptionById(Long optionId) {
        log.trace("getOptionById(Long optionId) 실행");
        Optional<Option> optionalOption = optionRepository.findById(optionId);

        return optionalOption.orElseThrow(() ->
                new IllegalArgumentException("Option with ID " + optionId + " not found.")
        );
    }

}

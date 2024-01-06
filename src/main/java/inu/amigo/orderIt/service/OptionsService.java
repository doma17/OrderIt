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

    public List<Option> getAllOptions() {
        log.trace("getAllOptions() 실행");
        return optionRepository.findAll();
    }

    public List<Option> getOptionsByIds(List<Long> optionIds) {
        log.trace("getOptionsByIds(List<Long> optionIds) 실행");
        return optionRepository.findAllById(optionIds);
    }

    public Option createOption(Option option) {
        log.trace("createOption(Option option) 실행");
        return optionRepository.save(option);
    }

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

    public Option getOptionById(Long optionId) {
        log.trace("getOptionById(Long optionId) 실행");
        Optional<Option> optionalOption = optionRepository.findById(optionId);

        return optionalOption.orElseThrow(() ->
                new IllegalArgumentException("Option with ID " + optionId + " not found.")
        );
    }

}

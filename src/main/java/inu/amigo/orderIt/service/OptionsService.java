package inu.amigo.orderIt.service;

import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.Option;
import inu.amigo.orderIt.repository.ItemRepository;
import inu.amigo.orderIt.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionsService {

    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public OptionsService(ItemRepository itemRepository, OptionRepository optionRepository) {
        this.itemRepository = itemRepository;
        this.optionRepository = optionRepository;
    }


    public List<Option> getAllOptions() {return optionRepository.findAll();}

    public List<Option> getItemOptions(Long itemId) {
        // 아이템 ID로 아이템을 조회
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalItem.isPresent()) {
            // 아이템이 존재하면 해당 아이템의 옵션 목록 반환
            return optionalItem.get().getOptions();
        } else {
            // 아이템이 존재하지 않으면 예외 발생
            throw new IllegalArgumentException("Item with ID " + itemId + " not found.");
        }
    }

    public Option createOption(Option option) {
        return optionRepository.save(option);
    }
}

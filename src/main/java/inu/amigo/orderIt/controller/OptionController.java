package inu.amigo.orderIt.controller;

import inu.amigo.orderIt.domain.item.Option;
import inu.amigo.orderIt.service.OptionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
@Tag(name = "Option API")
public class OptionController {

    private final OptionsService optionsService;

    @Autowired
    public OptionController(OptionsService optionsService) {
        this.optionsService = optionsService;
    }

    // 옵션 추가
    @PostMapping()
    public Option createOption(@ModelAttribute Option option) {
        return optionsService.createOption(option);
    }

    // 전체 옵션 조회
    @GetMapping()
    public List<Option> getItemOptions() {
        return optionsService.getAllOptions();
    }

    // 특정 아이템 옵션 목록 조회
    @GetMapping("/{itemId}")
    public List<Option> getItemOptions(@PathVariable Long itemId) {
        return optionsService.getOptionsByItemId(itemId);
    }
}

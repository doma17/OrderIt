package inu.amigo.orderIt.controller;

import inu.amigo.orderIt.domain.item.Item;
import inu.amigo.orderIt.domain.item.Menu;
import inu.amigo.orderIt.domain.item.Option;
import inu.amigo.orderIt.dto.ItemDto;
import inu.amigo.orderIt.exception.FileValidationException;
import inu.amigo.orderIt.service.ItemService;
import inu.amigo.orderIt.service.OptionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @PostMapping("/options")
    public Option createOption(@ModelAttribute Option option) {
        return optionsService.createOption(option);
    }

    // 전체 옵션 조회
    @GetMapping("/options")
    public List<Option> getItemOptions() {
        return optionsService.getAllOptions();
    }

    // 특정 아이템 옵션 목록 조회
    @GetMapping("/options/{itemId}")
    public List<Option> getItemOptions(@PathVariable Long itemId) {
        return optionsService.getItemOptions(itemId);
    }
}

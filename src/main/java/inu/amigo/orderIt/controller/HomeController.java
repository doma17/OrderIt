package inu.amigo.orderIt.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Home Page", description = "홈페이지를 반환합니다.")
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
}

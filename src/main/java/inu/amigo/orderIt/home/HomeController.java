package inu.amigo.orderIt.home;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Tag(name = "Page")
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/new")
    public String createForm() {
        return "createItemForm";
    }
}

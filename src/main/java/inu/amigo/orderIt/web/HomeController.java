package inu.amigo.orderIt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Main Page Controller
 *  Author : 곽병민
 *  Date : 2023-12-28
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "/home";
    }
}

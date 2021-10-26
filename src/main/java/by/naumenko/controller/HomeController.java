package by.naumenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static by.naumenko.util.HtmlPages.HOME;

@Controller
public class HomeController {

    @GetMapping({"/home", "/"})
    public String getHome() {
        return HOME;
    }
}

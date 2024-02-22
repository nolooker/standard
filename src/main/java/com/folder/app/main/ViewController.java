package com.folder.app.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/list")
    public String home() {
        return "user/List";
    }
}

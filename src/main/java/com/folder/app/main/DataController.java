package com.folder.app.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/main")
    public String main() {
        return "Data 준비중..";
    }
}

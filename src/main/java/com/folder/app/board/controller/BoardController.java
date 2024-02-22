package com.folder.app.board.controller;

import com.folder.app.board.dto.BoardDto;
import com.folder.app.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor  // 생성자 생성을 위함
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")   //주소
    public String board() { // 자바 메서드 이름
        return "board"; // 리턴할 화면 이름
    }

    @PostMapping("/board")
    public String board(@ModelAttribute BoardDto boardDto){
        System.out.println(boardDto);

        boardService.board(boardDto);

        return "index";
    }
}

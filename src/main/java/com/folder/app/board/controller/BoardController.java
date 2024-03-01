package com.folder.app.board.controller;

import com.folder.app.board.dto.BoardDto;
import com.folder.app.board.dto.BoardFileDto;
import com.folder.app.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor  // 생성자 생성을 위함
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")   //주소
    public String board() { // 자바 메서드 이름
        return "board"; // 리턴할 화면 이름
    }

    @PostMapping("/board")
    public String board(@ModelAttribute BoardDto boardDto) throws IOException {
        System.out.println(boardDto);

        boardService.board(boardDto);

        return "redirect:/boardList";   // boardList를 다시 요청
    }

    @GetMapping("/boardList")
    public String findAll(Model model) {
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList", boardDtoList);
        System.out.println(boardDtoList);
        return "boardList";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        // 조회수 처리 먼저
        boardService.updateHits(id);
        // 상세내용 가져온다
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        System.out.println(boardDto);

        if (boardDto.getFileAttached() == 1) {
            BoardFileDto boardFileDto = boardService.findFile(id);
            model.addAttribute("boardFile", boardFileDto);
        }

        return "boardDetail";
    }

    @GetMapping("/boardUpdate/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
        System.out.println(boardDto);
        return "boardUpdate";
    }

    @PostMapping("/boardUpdate/{id}")
    public String update(BoardDto boardDto, Model model) {
        boardService.boardUpdate(boardDto);
        BoardDto dto = boardService.findById(boardDto.getId());
        model.addAttribute("board", dto);
        System.out.println(dto);
        return "boardDetail";
    }

    @GetMapping("/boardDelete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/boardList";
    }
}

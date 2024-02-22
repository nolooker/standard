package com.folder.app.board.service;

import com.folder.app.board.dto.BoardDto;
import com.folder.app.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void board(BoardDto boardDto) {
        boardRepository.board(boardDto);
    }
}

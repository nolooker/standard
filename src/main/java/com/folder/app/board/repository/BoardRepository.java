package com.folder.app.board.repository;

import com.folder.app.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    public void board(BoardDto boardDto) {
        sql.insert("Board.board", boardDto);
    }
}

package com.folder.app.board.repository;

import com.folder.app.board.dto.BoardDto;
import com.folder.app.board.dto.BoardFileDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    public BoardDto board(BoardDto boardDto) {

        sql.insert("Board.board", boardDto);
        return boardDto;
    }

    public List<BoardDto> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        sql.update("Board.updateHits", id);
    }

    public BoardDto findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public void boardUpdate(BoardDto boardDto) {
        sql.update("Board.update", boardDto);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }

    public void saveFile(BoardFileDto boardFileDto) {
        sql.insert("Board.saveFile", boardFileDto);
    }

    public BoardFileDto findFile(Long id) {
        return sql.selectOne("Board.findFile", id);
    }
}

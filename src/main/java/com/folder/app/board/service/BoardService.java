package com.folder.app.board.service;

import com.folder.app.board.dto.BoardDto;
import com.folder.app.board.dto.BoardFileDto;
import com.folder.app.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void board(BoardDto boardDto) throws IOException {

        if(boardDto.getBoardFile().isEmpty()) {
            // 파일이 없으면
            boardDto.setFileAttached(0);
            boardRepository.board(boardDto);
        } else {
            // 파일 존재 시
            boardDto.setFileAttached(1);
            // 게시물 저장 후 id값 활용을 위해 리턴 받음
            BoardDto saveBoard = boardRepository.board(boardDto);
            // 파일만 따로 가져오기
            MultipartFile boardFile = boardDto.getBoardFile();
            // 파일 이름 가져오기
            String originalFileName = boardFile.getOriginalFilename();
            System.out.println(originalFileName);
            // 저장용 이름 만들기
            System.out.println(System.currentTimeMillis());
            String storedFileName = System.currentTimeMillis() + "-" + originalFileName;
            System.out.println(storedFileName);
            // BoardFileDto 세팅
            BoardFileDto boardFileDto = new BoardFileDto();
            boardFileDto.setOriginalFileName(originalFileName);
            boardFileDto.setStoredFileName(storedFileName);
            boardFileDto.setBoardId(saveBoard.getId());

            // 파일 저장을 폴더에 파일 저장 처리
            String savePath = "C:/developement/intellij/spring_upload_files/" + storedFileName;
            boardFile.transferTo(new File(savePath));

            // board_file_table 저장 처리
            boardRepository.saveFile(boardFileDto);
        }

    }

    public List<BoardDto> findAll() {
        return boardRepository.findAll();
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDto findById(Long id) {
        return boardRepository.findById(id);
    }

    public void boardUpdate(BoardDto boardDto) {
        boardRepository.boardUpdate(boardDto);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public BoardFileDto findFile(Long id) {
        return boardRepository.findFile(id);
    }
}

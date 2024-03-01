package com.folder.app.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class BoardDto {
    private Long id;

    private String boardWriter;

    private String boardPass;

    private String boardTitle;

    private String boardContents;

    private int boardHits;

    private String createdAt;

    private int fileAttached;   // 파일 첨부 판단

    private MultipartFile boardFile;    // 게시글 작성 시 파일 담기 위한

}

package com.folder.app.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDto {

    private Long id;

    private Long boardId;

    private String originalFileName;    // 원본 파일

    private String storedFileName;
}

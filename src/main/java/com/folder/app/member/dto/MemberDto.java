package com.folder.app.member.dto;

import com.folder.app.member.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 자동으로 만들어줌
@ToString   // dto 객체가 가지고 있는 값을 ToString 메서드로 자동으로 만들어주는 Lombok
public class MemberDto {  // 회원 정보에 필요한 부분을 필드로 정의
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setMemberEmail(memberEntity.getMemberEmail());
        memberDto.setMemberPassword(memberEntity.getMemberPassword());
        memberDto.setMemberName(memberEntity.getMemberName());
        return memberDto;
    }
}

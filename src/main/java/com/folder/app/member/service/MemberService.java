package com.folder.app.member.service;

import com.folder.app.member.dto.MemberDto;
import com.folder.app.member.entity.MemberEntity;
import com.folder.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(MemberDto memberDto) {

        // 1. dto -> emtity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
        // repository의 save 메서드 호출 ( 조건, entity 객체를 넘겨줘야 함)

    }

    public MemberDto login(MemberDto memberDto) {

        /*
        *   1. 회원이 입력한 이메일로 DB에서 조회를 함
        *   2. DB에서 조호한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDto.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다.)
            MemberEntity memberEntity = byMemberEmail.get();

            if (memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())){
                // 비밀번호가 일치
                // entity 객체를 dto로 변환 후 리턴
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;

            } else {
                // 비밀번호 불일치
                return null;
            }

        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다.)
            return null;
        }

    }

    public List<MemberDto> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (MemberEntity memberEntity: memberEntityList) {
            memberDtoList.add(MemberDto.toMemberDto(memberEntity));

            // 위와 동일 ==
//            MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
//            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    public MemberDto findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {

//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
//            return MemberDto;

            return MemberDto.toMemberDto(optionalMemberEntity.get());
        }
        return null;
    }

    public MemberDto updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);

        if (optionalMemberEntity.isPresent()) {
            return MemberDto.toMemberDto(optionalMemberEntity.get());
        } else {
            return null;
        }

    }

    public void update(MemberDto memberDto) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회결과가 있으면 -> 사용할 수 없다.
            return null;
        } else {
            // 조회결과가 없으면 -> 사용할 수 있다.
            return "ok";
        }
    }
}

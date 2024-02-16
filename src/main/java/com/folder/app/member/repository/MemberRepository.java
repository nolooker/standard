package com.folder.app.member.repository;

import com.folder.app.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {   // 어떤 ENTITY를 상속 받을 것인가 , PK의 TYPE을 가져온다.
    // 이메일로 회원 정보 조회 (select * from member_table where member_email = ?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}

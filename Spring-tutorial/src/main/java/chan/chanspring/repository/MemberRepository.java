package chan.chanspring.repository;

import chan.chanspring.domain.Member;

import java.util.*;

public interface MemberRepository {
    Member save(Member member); //멤버 저장
    Optional<Member> findById(Long id); // 아이디 찾기
    Optional<Member> findByName(String name); // 이름찾기
    List<Member> findAll(); // 저장된 모든 멤버 다 반환
}

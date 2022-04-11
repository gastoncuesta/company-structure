package com.amazingco.companystructure.dao;

import com.amazingco.companystructure.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    //List<Member> getAllDescendantMember(String memberId);
}

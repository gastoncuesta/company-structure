package com.amazingco.companystructure.service;

import com.amazingco.companystructure.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getAllDescendantMember(Member member);

    void changeParent(Member member);
}

package com.amazingco.companystructure.service;

import com.amazingco.companystructure.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl implements MemberService {

    @Override
    public List<Member> getAllDescendantMember(Member member) {
        return null;
    }

    @Override
    public void changeParent(Member member) {

    }
}

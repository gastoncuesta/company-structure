package com.amazingco.companystructure.service;

import com.amazingco.companystructure.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    List<MemberEntity> getAllDescendantByMemberId(Long memberId);

    MemberEntity getMemberById(Long memberId);

    MemberEntity updateMember(MemberEntity updatedChild);
}

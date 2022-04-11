package com.amazingco.companystructure.service;

import com.amazingco.companystructure.entity.MemberEntity;
import com.amazingco.companystructure.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberEntity> getAllDescendantByMemberId(Long memberId) {
        return memberRepository.getAllDescendantByMemberId(memberId);
    }

    @Override
    public MemberEntity getMemberById(Long memberId) {
        Optional<MemberEntity> result = memberRepository.findById(memberId);
        return result.orElse(null);
    }

    @Override
    public MemberEntity updateMember(MemberEntity updatedChild) {
        return memberRepository.save(updatedChild);
    }

}

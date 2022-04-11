package com.amazingco.companystructure.functional;

import com.amazingco.companystructure.entity.Member;
import com.amazingco.companystructure.entity.MemberEntity;

import java.util.function.Function;

public class MemberEntityToMemberFunctional implements Function<MemberEntity, Member> {

    @Override
    public Member apply(MemberEntity memberEntity) {
        return Member.builder()
                .id(memberEntity.getId())
                .name(memberEntity.getName())
                .parent(memberEntity.getParent() == null ? null : memberEntity.getParent().getName())
                .root(memberEntity.getRoot() == null ? null : memberEntity.getRoot().getName())
                .height(memberEntity.getHeight())
                .build();
    }
}

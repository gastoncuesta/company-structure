package com.amazingco.companystructure.controller;

import com.amazingco.companystructure.entity.Member;
import com.amazingco.companystructure.entity.MemberEntity;
import com.amazingco.companystructure.functional.MemberEntityToMemberFunctional;
import com.amazingco.companystructure.service.MemberService;
import com.amazingco.companystructure.utils.MemberUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/structure")
public class MemberRestController {

    private final MemberService memberService;
    private final MemberEntityToMemberFunctional memberEntityToMemberFunctional = new MemberEntityToMemberFunctional();


    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/getMember/{memberId}")
    public ResponseEntity<Object> getMember(@PathVariable Long memberId) {
        Objects.requireNonNull(memberId);
        MemberEntity member;
        try {
            member = memberService.getMemberById(memberId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        if (member == null) {
            return ResponseEntity.status(404).body("Member not found");
        }
        return ResponseEntity.ok(memberEntityToMemberFunctional.apply(member));
    }

    @GetMapping("/descendants/{memberId}")
    public ResponseEntity<Object> getAllDescendantMember(@PathVariable Long memberId) {
        Objects.requireNonNull(memberId);
        MemberEntity member;
        try {
            member = memberService.getMemberById(memberId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        if (member == null) {
            return ResponseEntity.status(404).body("Member not found");
        }
        List<MemberEntity> allDescendantMember;
        try {
            allDescendantMember = memberService.getAllDescendantByMemberId(memberId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        List<Member> members = allDescendantMember.stream().map(memberEntityToMemberFunctional).collect(Collectors.toList());
        return ResponseEntity.ok(members);
    }

    @PostMapping("/changeParent")
    public ResponseEntity<Object> changeParent(Long memberId, Long newParentId) {
        Objects.requireNonNull(memberId);
        Objects.requireNonNull(newParentId);

        MemberEntity child = memberService.getMemberById(memberId);
        MemberEntity parent = memberService.getMemberById(newParentId);

        if (child == null) {
            return ResponseEntity.status(404).body("Member not found");
        }

        if (parent == null) {
            return ResponseEntity.status(404).body("Parent not found");
        }
        if(!MemberUtil.isAChild(child, parent)){
            MemberEntity updatedChild = MemberUtil.changeParent(child, parent);
            MemberEntity memberEntity;
            try {
                memberEntity = memberService.updateMember(updatedChild);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(memberEntityToMemberFunctional.apply(memberEntity));
        }else{
            return ResponseEntity.status(400).body("Is already a child");
        }


    }
}

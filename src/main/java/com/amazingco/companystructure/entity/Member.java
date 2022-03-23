package com.amazingco.companystructure.entity;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Member {

    private String name;
    private Member parent;
    private List<Member> children;


    public void addChild(Member member) {
        if (children == null || children.size() == 0) {
            children = new ArrayList<>();
        }
        if (!existChild(member)) {
            member.setParent(this);
            children.add(member);
        }
    }

    private Boolean existChild(Member person) {
        return children.stream().anyMatch(child -> child.getName().equalsIgnoreCase(person.getName()));
    }


}

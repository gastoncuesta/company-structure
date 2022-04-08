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
    private Member root;
    private int height;
    private List<Member> children;


    public void addChild(Member member) {
        if (children == null || children.size() == 0) {
            children = new ArrayList<>();
        }
        if (!existChild(member)) {
            member.setParent(this);
            addRoot(member);
            addHeight(member);
            children.add(member);
        }
    }

    private Boolean existChild(Member person) {
        return children.stream().anyMatch(child -> child.getName().equalsIgnoreCase(person.getName()));
    }


    public static List<Member> getAllDescendants(Member member) {
        List<Member> descendants = new ArrayList<>();
        List<Member> children = member.getChildren();

        while (children != null) {

            for (Member child : children) {
                descendants.addAll(children);
                children = child.getChildren();
            }

        }

        return descendants;
    }


    public void changeParent(Member newParent) {
        newParent.addChild(this);
        parent = newParent;
    }


    private static void addRoot(Member member) {
        Member root = member.getParent();

        while (root != null) {

            if (root.getParent() == null) {
                member.root = root;
                break;

            } else {
                root = root.getParent();
            }
        }
    }

    private static void addHeight(Member member) {
        member.height = 0;
        Member root = member.getParent();
        while (root != null) {

            member.height = member.height + 1;

            root = root.getParent();
        }

    }


    private String getChildrenNames() {
        StringBuilder names = new StringBuilder();
        if (children != null) {
            for (Member child : children) {
                names.append(child.getName()).append(" - ");
            }
        }
        return names.toString();
    }


    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", root=" + root +
                ", height=" + height +
                ", children=" + getChildrenNames() +
                '}';
    }

}

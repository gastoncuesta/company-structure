package com.amazingco.companystructure.entity;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder

public class Member2 {
    private String name;
    private Member2 parent;
    private Member2 root;
    private int height;
    private List<Member2> children;


    public void addChild(Member2 member2) {
        if (children == null || children.isEmpty()) {
            children = new ArrayList<>();
        }
        if (!existChild(member2)) {
            member2.setParent(this);
            addRoot(member2);
            addHeight(member2);
            children.add(member2);
        }
    }

    private Boolean existChild(Member2 person) {
        //if (children != null && children.size() != 0) {
            return children.stream().anyMatch(child -> child.getName().equalsIgnoreCase(person.getName()));
       // }
      //  return false;
    }


    public static List<Member2> getAllDescendants(Member2 member2) {
        List<Member2> descendants = new ArrayList<>();
        List<Member2> children = member2.getChildren();

        while (children != null && children.size() != 0) {

            for (Member2 child : children) {
                descendants.addAll(children);
                children = child.getChildren();
            }

        }

        return descendants;
    }


    public void changeParent(Member2 newParent) {
        newParent.addChild(this);
        parent = newParent;
    }


    private static void addRoot(Member2 member2) {
        Member2 root = member2.getParent();

        while (root != null) {

            if (root.getParent() == null) {
                member2.root = root;
                break;

            } else {
                root = root.getParent();
            }
        }
    }

    private static void addHeight(Member2 member2) {
        member2.height = 0;
        Member2 root = member2.getParent();
        while (root != null) {

            member2.height = member2.height + 1;

            root = root.getParent();
        }

    }


    private String getChildrenNames() {
        StringBuilder names = new StringBuilder();
        if (children.size() > 0) {
            for (Member2 child : children) {
                names.append(child.getName()).append(" - ");
            }
        }
        return names.toString();
    }


    @Override
    public String toString() {
        return "Member2{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", root=" + root +
                ", height=" + height +
                ", children=" + getChildrenNames() +
                '}';
    }

}

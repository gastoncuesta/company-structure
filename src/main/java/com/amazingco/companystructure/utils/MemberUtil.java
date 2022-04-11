package com.amazingco.companystructure.utils;

import com.amazingco.companystructure.entity.MemberEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberUtil {

    public static MemberEntity changeParent(MemberEntity child, MemberEntity newParent) {
        MemberEntity parent = child.getParent();

        if (parent != null && parent.getParent() == null && parent.getRoot() == null) {
            child.setRoot(newParent);
        }

        if (isARoot(child, newParent)) {
            child.setHeight(1);
            initializeNewRoot(newParent);
            removeChild(newParent, child);
            updateChildrenRoot(child, newParent);
            updateChildrenHeight(child);
            child.setRoot(newParent);
        } else {
            removeChild(child, newParent);
        }

        addChild(child, newParent);
        child.setParent(newParent);
        return child;
    }

    private static void updateChildrenHeight(MemberEntity child) {
        if (child.getChildren() != null) {
            while (child.getChildren().size() > 0) {
                for (MemberEntity childMember : child.getChildren()) {
                    int height = calculateHeight(childMember);
                    childMember.setHeight(height);
                    child = childMember;
                }
            }
        }
    }

    private static void updateChildrenRoot(MemberEntity child, MemberEntity newParent) {
        if (child.getChildren() != null) {
            while (child.getChildren().size() > 0) {
                for (MemberEntity childMember : child.getChildren()) {
                    if (childMember.getRoot() != newParent) {
                        childMember.setRoot(newParent);
                    }
                    child = childMember;
                }


            }
        }

    }

    private static void initializeNewRoot(MemberEntity newParent) {
        newParent.setParent(null);
        newParent.setRoot(null);
        newParent.setHeight(0);
    }

    public static Boolean isAChild(MemberEntity member, MemberEntity parent) {
        return parent.getChildren() != null && parent.getChildren().stream().anyMatch(child -> child.getName().equalsIgnoreCase(member.getName()));
    }

    private static Boolean isARoot(MemberEntity root, MemberEntity member) {
        if (root.getRoot() == null) {

            while (member.getRoot() != null) {
                if (member.getRoot().equals(root)) {
                    return true;
                } else {
                    member = member.getRoot();
                }
            }
        }
        return false;
    }

    private static void removeChild(MemberEntity member, MemberEntity parent) {
        List<MemberEntity> children = parent.getChildren();
        if (children != null) {
            children.stream().filter(child -> child.getName().equalsIgnoreCase(member.getName()))
                    .findFirst().ifPresent(children::remove);
        }

    }

    public static void addChild(MemberEntity child, MemberEntity parent) {
        List<MemberEntity> children = parent.getChildren();
        if (children == null || children.isEmpty()) {
            parent.setChildren(new ArrayList<>());
            children = new ArrayList<>();
        }
        child.setParent(parent);
        if (!isARoot(child, parent)) {
            addRoot(child);
        } else {
            changeRoot(child, parent);
        }
        int height = calculateHeight(child);
        child.setHeight(height);
        children.add(child);
        parent.setChildren(children);

    }

    private static void changeRoot(MemberEntity child, MemberEntity parent) {
        if (child != null) {
            while (child != null && child.getRoot() != null) {
                if (child.getParent().getRoot() == null && child.getRoot() != null) {
                    child.setParent(null);
                    child.setRoot(parent);
                } else {
                    child = child.getParent().getRoot();
                }
            }
        }
    }

    private static int calculateHeight(MemberEntity member) {
        int height = 0;
        MemberEntity root = member.getParent();
        while (root != null) {

            height++;

            root = root.getParent();
        }
        return height;
    }

    private static void addRoot(MemberEntity member) {
        MemberEntity root = member.getParent();
        while (root != null) {

            if (root.getParent() == null) {
                member.setRoot(root);
                break;

            } else {
                root = root.getParent();
            }
        }
    }

    public static List<MemberEntity> getAllDescendants(MemberEntity member) {
        List<MemberEntity> descendants = new ArrayList<>();
        List<MemberEntity> children = member.getChildren();

        while (children != null && children.size() != 0) {

            for (MemberEntity child : children) {
                descendants.addAll(children);
                children = child.getChildren();
            }

        }

        return descendants;
    }

}

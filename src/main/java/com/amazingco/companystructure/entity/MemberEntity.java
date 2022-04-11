package com.amazingco.companystructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")

public class MemberEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private MemberEntity parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root")
    private MemberEntity root;

    @Column(nullable = false)
    private int height;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<MemberEntity> children;

    public MemberEntity getRoot() {
        return root;
    }

    public MemberEntity getParent() {
        return parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(MemberEntity parent) {
        this.parent = parent;
    }

    public void setRoot(MemberEntity root) {
        this.root = root;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<MemberEntity> getChildren() {
        return children;
    }

    public void setChildren(List<MemberEntity> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity member = (MemberEntity) o;
        return height == member.height && Objects.equals(id, member.id) && Objects.equals(name, member.name) && Objects.equals(parent, member.parent) && Objects.equals(root, member.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parent, root, height);
    }
}

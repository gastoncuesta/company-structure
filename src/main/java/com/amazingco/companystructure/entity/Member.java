package com.amazingco.companystructure.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
    private Long id;
    private String name;
    private String parent;
    private String root;
    private int height;


    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", root=" + root +
                ", height=" + height +
                '}';
    }

}

package com.amazingco.companystructure;


import com.amazingco.companystructure.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyStructureApplicationTests {

    Member root = Member.builder().name("root").build();

    @Test
    @DisplayName("Get parent node")
    void testGetRoot() {
        assertEquals(root.getName(), "root", "Name of root should be root");
        assertNull(root.getParent(), "Root node should not   have a parent");
    }

    @Test
    @DisplayName("Add One child to the parent")
    void testAddChildToRoot() {
        Member child = Member.builder().name("a").parent(root).build();
        root.addChild(child);

        assertEquals(1, root.getChildren().size(), "It should have one child");
    }

    @Test
    @DisplayName("Add Two Equal children to parent")
    void testAddTwoEqualChildrenToFather() {
        root.addChild(Member.builder().name("h").build());
        root.addChild(Member.builder().name("h").build());
        assertEquals(1, root.getChildren().size(), "It should have one father");
    }


    @Test
    @DisplayName("Add children to parent")
    void testAddManyChildrenToParent() {
        Member childA = Member.builder().name("a").parent(root).build();
        Member childB = Member.builder().name("b").parent(root).build();
        Member childC = Member.builder().name("c").parent(root).build();
        Member childD = Member.builder().name("d").parent(root).build();
        Member childE = Member.builder().name("e").parent(root).build();

        List<Member> children = new ArrayList<>() {{
            add(childA);
            add(childB);
            add(childC);
            add(childD);
            add(childE);
        }};

        root.setChildren(children);

        assertTrue(root.getChildren().size() > 1, "It should have many children");
    }

}

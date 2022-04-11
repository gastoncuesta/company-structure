package com.amazingco.companystructure;


import com.amazingco.companystructure.entity.MemberEntity;
import com.amazingco.companystructure.utils.MemberUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CompanyStructureApplicationTests {

    MemberEntity root = MemberEntity.builder().name("root").build();

    @Test
    @DisplayName("Get parent node")
    void testGetRoot() {
        assertEquals(root.getName(), "root", "Name of root should be root");
        assertNull(root.getParent(), "Root node should not   have a parent");
    }

    @Test
    @DisplayName("Get first child of root")
    void testGetFirstChild() {
        MemberEntity firstChild = MemberEntity.builder().name("firstChild").parent(root).build();
        assertEquals(firstChild.getName(), "firstChild", "Name of firstChild should be firstChild");
        assertEquals(firstChild.getParent(), root, "Parent of firstChild should be root");
    }

    @Test
    @DisplayName("Get second child of root")
    void testGetSecondChild() {
        MemberEntity.builder().name("firstChild").parent(root).build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").parent(root).build();
        assertEquals(secondChild.getName(), "secondChild", "Name of secondChild should be secondChild");
        assertEquals(secondChild.getParent(), root, "Parent of secondChild should be root");
    }

    @Test
    @DisplayName("Get first child of first child")
    void testGetFirstChildOfFirstChild() {
        MemberEntity firstChild = MemberEntity.builder().name("firstChild").parent(root).build();
        MemberEntity firstChildOfFirstChild = MemberEntity.builder().name("firstChildOfFirstChild").parent(firstChild).build();
        assertEquals(firstChildOfFirstChild.getName(), "firstChildOfFirstChild", "Name of firstChildOfFirstChild should be firstChildOfFirstChild");
        assertEquals(firstChildOfFirstChild.getParent(), firstChild, "Parent of firstChildOfFirstChild should be firstChild");
    }

    @Test
    @DisplayName("Get second child of first child")
    void testGetSecondChildOfFirstChild() {
        MemberEntity firstChild = MemberEntity.builder().name("firstChild").parent(root).build();
        MemberEntity.builder().name("firstChildOfFirstChild").parent(firstChild).build();
        MemberEntity secondChildOfFirstChild = MemberEntity.builder().name("secondChildOfFirstChild").parent(firstChild).build();
        assertEquals(secondChildOfFirstChild.getName(), "secondChildOfFirstChild", "Name of secondChildOfFirstChild should be secondChildOfFirstChild");
        assertEquals(secondChildOfFirstChild.getParent(), firstChild, "Parent of secondChildOfFirstChild should be firstChild");
    }

    @Test
    @DisplayName("Get first child of second child")
    void testGetFirstChildOfSecondChild() {
        MemberEntity.builder().name("firstChild").parent(root).build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").parent(root).build();
        MemberEntity firstChildOfSecondChild = MemberEntity.builder().name("firstChildOfSecondChild").parent(secondChild).build();
        assertEquals(firstChildOfSecondChild.getName(), "firstChildOfSecondChild", "Name of firstChildOfSecondChild should be firstChildOfSecondChild");
        assertEquals(firstChildOfSecondChild.getParent(), secondChild, "Parent of firstChildOfSecondChild should be secondChild");
    }

    @Test
    @DisplayName("Get second child of second child")
    void testGetSecondChildOfSecondChild() {
        MemberEntity.builder().name("firstChild").parent(root).build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").parent(root).build();
        MemberEntity secondChildOfSecondChild = MemberEntity.builder().name("secondChildOfSecondChild").parent(secondChild).build();
        assertEquals(secondChildOfSecondChild.getName(), "secondChildOfSecondChild", "Name of secondChildOfSecondChild should be secondChildOfSecondChild");
        assertEquals(secondChildOfSecondChild.getParent(), secondChild, "Parent of secondChildOfSecondChild should be secondChild");
    }

    @Test
    @DisplayName("Get first child of second child of first child")
    void testGetFirstChildOfSecondChildOfFirstChild() {
        MemberEntity.builder().name("firstChild").parent(root).build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").parent(root).build();
        MemberEntity firstChildOfSecondChild = MemberEntity.builder().name("firstChildOfSecondChild").parent(secondChild).build();
        MemberEntity firstChildOfSecondChildOfFirstChild = MemberEntity.builder().name("firstChildOfSecondChildOfFirstChild").parent(firstChildOfSecondChild).build();
        assertEquals(firstChildOfSecondChildOfFirstChild.getName(), "firstChildOfSecondChildOfFirstChild", "Name of firstChildOfSecondChildOfFirstChild should be firstChildOfSecondChildOfFirstChild");
        assertEquals(firstChildOfSecondChildOfFirstChild.getParent(), firstChildOfSecondChild, "Parent of firstChildOfSecondChildOfFirstChild should be firstChildOfSecondChild");
    }

    @Test
    @DisplayName("Get all descendants of root")
    void testGetAllDescendantsOfRoot() {
        MemberEntity firstChild = MemberEntity.builder().name("firstChild").build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").build();
        MemberUtil.addChild(firstChild, root);
        MemberUtil.addChild(secondChild, root);

        MemberEntity firstChildOfFirstChild = MemberEntity.builder().name("firstChildOfFirstChild").build();
        MemberEntity secondChildOfFirstChild = MemberEntity.builder().name("secondChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfFirstChild, firstChild);
        MemberUtil.addChild(secondChildOfFirstChild, firstChild);

        MemberEntity firstChildOfSecondChild = MemberEntity.builder().name("firstChildOfSecondChild").build();
        MemberEntity secondChildOfSecondChild = MemberEntity.builder().name("secondChildOfSecondChild").build();
        MemberUtil.addChild(firstChildOfSecondChild, secondChild);
        MemberUtil.addChild(secondChildOfSecondChild, secondChild);

        MemberEntity firstChildOfSecondChildOfFirstChild = MemberEntity.builder().name("firstChildOfSecondChildOfFirstChild").build();
        MemberEntity secondChildOfSecondChildOfFirstChild = MemberEntity.builder().name("secondChildOfSecondChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfSecondChildOfFirstChild, firstChildOfSecondChild);
        MemberUtil.addChild(secondChildOfSecondChildOfFirstChild, firstChildOfSecondChild);

        List<MemberEntity> descendants = Arrays.asList(firstChild, secondChild, firstChildOfFirstChild, secondChildOfFirstChild, firstChildOfSecondChild, secondChildOfSecondChild, firstChildOfSecondChildOfFirstChild, secondChildOfSecondChildOfFirstChild);
        assertEquals(MemberUtil.getAllDescendants(root), descendants, "All descendants of root should be firstChild, secondChild, firstChildOfFirstChild, secondChildOfFirstChild, firstChildOfSecondChild, secondChildOfSecondChild, firstChildOfSecondChildOfFirstChild, secondChildOfSecondChildOfFirstChild");
    }

    @Test
    @DisplayName("Get all descendants of first child")
    void testGetAllDescendantsOfFirstChild() {
        MemberEntity firstChild = MemberEntity.builder().name("firstChild").build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").build();
        MemberUtil.addChild(firstChild, root);
        MemberUtil.addChild(secondChild, root);

        MemberEntity firstChildOfFirstChild = MemberEntity.builder().name("firstChildOfFirstChild").build();
        MemberEntity secondChildOfFirstChild = MemberEntity.builder().name("secondChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfFirstChild, firstChild);
        MemberUtil.addChild(firstChildOfFirstChild, secondChildOfFirstChild);

        MemberEntity firstChildOfSecondChild = MemberEntity.builder().name("firstChildOfSecondChild").build();
        MemberEntity secondChildOfSecondChild = MemberEntity.builder().name("secondChildOfSecondChild").build();
        MemberUtil.addChild(firstChildOfSecondChild, secondChild);
        MemberUtil.addChild(secondChildOfSecondChild, secondChild);

        MemberEntity firstChildOfSecondChildOfFirstChild = MemberEntity.builder().name("firstChildOfSecondChildOfFirstChild").build();
        MemberEntity secondChildOfSecondChildOfFirstChild = MemberEntity.builder().name("secondChildOfSecondChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfSecondChildOfFirstChild, firstChildOfSecondChild);
        MemberUtil.addChild(secondChildOfSecondChildOfFirstChild, firstChildOfSecondChild);


        List<MemberEntity> descendantsActual = Arrays.asList(firstChildOfSecondChild, secondChildOfSecondChild, firstChildOfSecondChildOfFirstChild, secondChildOfSecondChildOfFirstChild);
        List<MemberEntity> allDescendantsOfSecondChildExpected = MemberUtil.getAllDescendants(secondChild);
        assertEquals(4, allDescendantsOfSecondChildExpected.size());
        assertEquals(allDescendantsOfSecondChildExpected, descendantsActual, "All descendants of firstChild should be firstChildOfFirstChild, secondChildOfFirstChild, firstChildOfSecondChild, secondChildOfSecondChild, firstChildOfSecondChildOfFirstChild, secondChildOfSecondChildOfFirstChild");
    }

    @Test
    @DisplayName("Change the parent of a node")
    void changeTheParentofANode() {
        MemberEntity firstChild = MemberEntity.builder().name("firstChild").build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").build();
        MemberUtil.addChild(firstChild, root);
        MemberUtil.addChild(secondChild, root);

        MemberEntity firstChildOfFirstChild = MemberEntity.builder().name("firstChildOfFirstChild").build();
        MemberEntity secondChildOfFirstChild = MemberEntity.builder().name("secondChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfFirstChild, firstChild);
        MemberUtil.addChild(firstChildOfFirstChild, secondChildOfFirstChild);

        MemberEntity newParent = MemberEntity.builder().name("newParent").build();
        MemberUtil.addChild(newParent, root);
        MemberUtil.changeParent(firstChildOfFirstChild, newParent);

        assertEquals("newParent", firstChildOfFirstChild.getParent().getName(), "firstChildOfFirstChild's parent's name should be newParent");
        assertEquals("firstChildOfFirstChild", newParent.getChildren().get(0).getName(), "child of newParent should be firstChildOfFirstChild");
    }

    @Test
    @DisplayName("Change the parent of a node")
    void changeTheParentofANodeWhoIsRoot() {
        MemberEntity firstChild = MemberEntity.builder().name("C").build();
        MemberEntity secondChild = MemberEntity.builder().name("D").build();
        MemberUtil.addChild(firstChild, root);
        MemberUtil.addChild(secondChild, root);

        MemberEntity firstChildOfFirstChild = MemberEntity.builder().name("1").build();
        MemberEntity secondChildOfFirstChild = MemberEntity.builder().name("2").build();
        MemberUtil.addChild(firstChildOfFirstChild, firstChild);
        MemberUtil.addChild(firstChildOfFirstChild, secondChildOfFirstChild);

        MemberEntity firstChildOfFirstChildOfFirstChild = MemberEntity.builder().name("4").build();
        MemberUtil.addChild(firstChildOfFirstChildOfFirstChild, firstChildOfFirstChild);


        MemberUtil.changeParent(firstChild, firstChildOfFirstChild);

        assertEquals("1", firstChild.getParent().getName(), "firstChild's parent's name should be 1");
    }

    @Test
    @DisplayName("Get the root of the node")
    void getRootOfTheNode() {
        MemberEntity firstChild = MemberEntity.builder().name("firstChild").build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").build();
        MemberUtil.addChild(firstChild, root);
        MemberUtil.addChild(secondChild, root);

        MemberEntity firstChildOfFirstChild = MemberEntity.builder().name("firstChildOfFirstChild").build();
        MemberEntity secondChildOfFirstChild = MemberEntity.builder().name("secondChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfFirstChild, firstChild);
        MemberUtil.addChild(secondChildOfFirstChild, firstChild);

        MemberEntity firstChildOfSecondChild = MemberEntity.builder().name("firstChildOfSecondChild").build();
        MemberEntity secondChildOfSecondChild = MemberEntity.builder().name("secondChildOfSecondChild").build();
        MemberUtil.addChild(firstChildOfSecondChild, secondChild);
        MemberUtil.addChild(secondChildOfSecondChild, secondChild);

        MemberEntity firstChildOfFirstChildOfFirstChild = MemberEntity.builder().name("firstChildOfFirstChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfFirstChildOfFirstChild, firstChildOfFirstChild);

        assertNull(root.getRoot(), "root should be null");
        assertEquals(root, secondChild.getRoot(), "The root of the node should be root");
        assertEquals(root, secondChildOfSecondChild.getRoot(), "The root of the node should be root");
        assertEquals(root.getName(), firstChildOfFirstChildOfFirstChild.getRoot().getName(), "The root of the node should be root");
    }

    @Test
    void testSetNodeHeight() {

        MemberEntity firstChild = MemberEntity.builder().name("firstChild").parent(root).build();
        MemberEntity secondChild = MemberEntity.builder().name("secondChild").build();
        MemberUtil.addChild(firstChild, root);
        MemberUtil.addChild(secondChild, root);

        MemberEntity firstChildOfFirstChild = MemberEntity.builder().name("firstChildOfFirstChild").build();
        MemberEntity secondChildOfFirstChild = MemberEntity.builder().name("secondChildOfFirstChild").build();
        MemberUtil.addChild(firstChildOfFirstChild, firstChild);
        MemberUtil.addChild(secondChildOfFirstChild, firstChild);


        assertEquals(0, root.getHeight(), "The height of root should be 0");
        assertEquals(1, firstChild.getHeight(), "The height of the node should be 1");
        assertEquals(1, secondChild.getHeight(), "The height of the node should be 1");
        assertEquals(2, firstChildOfFirstChild.getHeight(), "The height of the node should be 2");

    }
}

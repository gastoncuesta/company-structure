package com.amazingco.companystructure;


import com.amazingco.companystructure.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    @DisplayName("Get first child of root")
    void testGetFirstChild() {
        Member firstChild = Member.builder().name("firstChild").parent(root).build();
        assertEquals(firstChild.getName(), "firstChild", "Name of firstChild should be firstChild");
        assertEquals(firstChild.getParent(), root, "Parent of firstChild should be root");
    }

    @Test
    @DisplayName("Get second child of root")
    void testGetSecondChild() {
        Member.builder().name("firstChild").parent(root).build();
        Member secondChild = Member.builder().name("secondChild").parent(root).build();
        assertEquals(secondChild.getName(), "secondChild", "Name of secondChild should be secondChild");
        assertEquals(secondChild.getParent(), root, "Parent of secondChild should be root");
    }

    @Test
    @DisplayName("Get first child of first child")
    void testGetFirstChildOfFirstChild() {
        Member firstChild = Member.builder().name("firstChild").parent(root).build();
        Member firstChildOfFirstChild = Member.builder().name("firstChildOfFirstChild").parent(firstChild).build();
        assertEquals(firstChildOfFirstChild.getName(), "firstChildOfFirstChild", "Name of firstChildOfFirstChild should be firstChildOfFirstChild");
        assertEquals(firstChildOfFirstChild.getParent(), firstChild, "Parent of firstChildOfFirstChild should be firstChild");
    }

    @Test
    @DisplayName("Get second child of first child")
    void testGetSecondChildOfFirstChild() {
        Member firstChild = Member.builder().name("firstChild").parent(root).build();
        Member.builder().name("firstChildOfFirstChild").parent(firstChild).build();
        Member secondChildOfFirstChild = Member.builder().name("secondChildOfFirstChild").parent(firstChild).build();
        assertEquals(secondChildOfFirstChild.getName(), "secondChildOfFirstChild", "Name of secondChildOfFirstChild should be secondChildOfFirstChild");
        assertEquals(secondChildOfFirstChild.getParent(), firstChild, "Parent of secondChildOfFirstChild should be firstChild");
    }

    @Test
    @DisplayName("Get first child of second child")
    void testGetFirstChildOfSecondChild() {
        Member.builder().name("firstChild").parent(root).build();
        Member secondChild = Member.builder().name("secondChild").parent(root).build();
        Member firstChildOfSecondChild = Member.builder().name("firstChildOfSecondChild").parent(secondChild).build();
        assertEquals(firstChildOfSecondChild.getName(), "firstChildOfSecondChild", "Name of firstChildOfSecondChild should be firstChildOfSecondChild");
        assertEquals(firstChildOfSecondChild.getParent(), secondChild, "Parent of firstChildOfSecondChild should be secondChild");
    }

    @Test
    @DisplayName("Get second child of second child")
    void testGetSecondChildOfSecondChild() {
        Member.builder().name("firstChild").parent(root).build();
        Member secondChild = Member.builder().name("secondChild").parent(root).build();
        Member secondChildOfSecondChild = Member.builder().name("secondChildOfSecondChild").parent(secondChild).build();
        assertEquals(secondChildOfSecondChild.getName(), "secondChildOfSecondChild", "Name of secondChildOfSecondChild should be secondChildOfSecondChild");
        assertEquals(secondChildOfSecondChild.getParent(), secondChild, "Parent of secondChildOfSecondChild should be secondChild");
    }

    @Test
    @DisplayName("Get first child of second child of first child")
    void testGetFirstChildOfSecondChildOfFirstChild() {
        Member.builder().name("firstChild").parent(root).build();
        Member secondChild = Member.builder().name("secondChild").parent(root).build();
        Member firstChildOfSecondChild = Member.builder().name("firstChildOfSecondChild").parent(secondChild).build();
        Member firstChildOfSecondChildOfFirstChild = Member.builder().name("firstChildOfSecondChildOfFirstChild").parent(firstChildOfSecondChild).build();
        assertEquals(firstChildOfSecondChildOfFirstChild.getName(), "firstChildOfSecondChildOfFirstChild", "Name of firstChildOfSecondChildOfFirstChild should be firstChildOfSecondChildOfFirstChild");
        assertEquals(firstChildOfSecondChildOfFirstChild.getParent(), firstChildOfSecondChild, "Parent of firstChildOfSecondChildOfFirstChild should be firstChildOfSecondChild");
    }

    @Test
    @DisplayName("Get all descendants of root")
    void testGetAllDescendantsOfRoot() {
        Member firstChild = Member.builder().name("firstChild").build();
        Member secondChild = Member.builder().name("secondChild").build();
        root.addChild(firstChild);
        root.addChild(secondChild);

        Member firstChildOfFirstChild = Member.builder().name("firstChildOfFirstChild").build();
        Member secondChildOfFirstChild = Member.builder().name("secondChildOfFirstChild").build();
        firstChild.addChild(firstChildOfFirstChild);
        firstChild.addChild(secondChildOfFirstChild);

        Member firstChildOfSecondChild = Member.builder().name("firstChildOfSecondChild").build();
        Member secondChildOfSecondChild = Member.builder().name("secondChildOfSecondChild").build();
        secondChild.addChild(firstChildOfSecondChild);
        secondChild.addChild(secondChildOfSecondChild);

        Member firstChildOfSecondChildOfFirstChild = Member.builder().name("firstChildOfSecondChildOfFirstChild").build();
        Member secondChildOfSecondChildOfFirstChild = Member.builder().name("secondChildOfSecondChildOfFirstChild").build();
        firstChildOfSecondChild.addChild(firstChildOfSecondChildOfFirstChild);
        firstChildOfSecondChild.addChild(secondChildOfSecondChildOfFirstChild);

        List<Member> descendants = Arrays.asList(firstChild, secondChild, firstChildOfFirstChild, secondChildOfFirstChild, firstChildOfSecondChild, secondChildOfSecondChild, firstChildOfSecondChildOfFirstChild, secondChildOfSecondChildOfFirstChild);
        assertEquals(Member.getAllDescendants(root), descendants, "All descendants of root should be firstChild, secondChild, firstChildOfFirstChild, secondChildOfFirstChild, firstChildOfSecondChild, secondChildOfSecondChild, firstChildOfSecondChildOfFirstChild, secondChildOfSecondChildOfFirstChild");
    }

    @Test
    @DisplayName("Change the parent of a node")
    void changeTheParentofANode() {
        Member firstChild = Member.builder().name("firstChild").build();
        Member secondChild = Member.builder().name("secondChild").build();
        root.addChild(firstChild);
        root.addChild(secondChild);

        Member firstChildOfFirstChild = Member.builder().name("firstChildOfFirstChild").build();
        Member secondChildOfFirstChild = Member.builder().name("secondChildOfFirstChild").build();
        firstChild.addChild(firstChildOfFirstChild);
        firstChild.addChild(secondChildOfFirstChild);

        Member newParent = Member.builder().name("newParent").build();
        root.addChild(newParent);
        firstChildOfFirstChild.changeParent(newParent);

        assertEquals("newParent", firstChildOfFirstChild.getParent().getName(), "firstChildOfFirstChild's parent's name should be newParent");
        assertEquals("firstChildOfFirstChild", newParent.getChildren().get(0).getName(), "child of newParent should be firstChildOfFirstChild");
    }

    @Test
    @DisplayName("Get the root of the node")
    void getRootOfTheNode() {
        Member firstChild = Member.builder().name("firstChild").build();
        Member secondChild = Member.builder().name("secondChild").build();
        root.addChild(firstChild);
        root.addChild(secondChild);

        Member firstChildOfFirstChild = Member.builder().name("firstChildOfFirstChild").build();
        Member secondChildOfFirstChild = Member.builder().name("secondChildOfFirstChild").build();
        firstChild.addChild(firstChildOfFirstChild);
        firstChild.addChild(secondChildOfFirstChild);

        Member firstChildOfSecondChild = Member.builder().name("firstChildOfSecondChild").build();
        Member secondChildOfSecondChild = Member.builder().name("secondChildOfSecondChild").build();
        secondChild.addChild(firstChildOfSecondChild);
        secondChild.addChild(secondChildOfSecondChild);

        Member firstChildOfFirstChildOfFirstChild = Member.builder().name("firstChildOfFirstChildOfFirstChild").build();
        firstChildOfFirstChild.addChild(firstChildOfFirstChildOfFirstChild);

        assertNull(root.getRoot(), "root should be null");
        assertEquals(root, secondChild.getRoot(), "The root of the node should be root");
        assertEquals(root, secondChildOfSecondChild.getRoot(), "The root of the node should be root");
        assertEquals(root, firstChildOfFirstChildOfFirstChild.getRoot(), "The root of the node should be root");
    }

    @Test
    void testSetNodeHeight(){

        Member firstChild = Member.builder().name("firstChild").parent(root).build();
        Member secondChild = Member.builder().name("secondChild").build();
        root.addChild(firstChild);
        root.addChild(secondChild);

        Member firstChildOfFirstChild = Member.builder().name("firstChildOfFirstChild").build();
        Member secondChildOfFirstChild = Member.builder().name("secondChildOfFirstChild").build();
        firstChild.addChild(firstChildOfFirstChild);
        firstChild.addChild(secondChildOfFirstChild);


        assertEquals(0, root.getHeight(), "The height of root should be 0");
        assertEquals(1, firstChild.getHeight(), "The height of the node should be 1");
        assertEquals(1, secondChild.getHeight(), "The height of the node should be 1");
        assertEquals(2, firstChildOfFirstChild.getHeight(), "The height of the node should be 2");

    }
}

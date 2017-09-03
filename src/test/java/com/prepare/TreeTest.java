package com.prepare;

import org.junit.Test;

public class TreeTest {

    class MyNode {
        int data;
        MyNode left;
        MyNode right;
        MyNode parent;
    }

    MyNode root;

    /**
     * Двоичное дерева
     * ***************
     * Вставка и обход дерева в глубину и в ширину
     */
    @Test
    public void testInsertAndTraverse() {
        TreeTest tree = new TreeTest();

        tree.root = tree.insert(tree.root, 2, null);
        tree.root = tree.insert(tree.root, 1, null);
        tree.root = tree.insert(tree.root, 3, null);

        tree.traverse(tree.root);
    }

    /*
     * Алгоритм вставки элементов в двоичное дерево
     */
    MyNode insert(MyNode current, int data, MyNode parent){ // NULL | value | NULL
        if(current == null){
            current = new MyNode();
            current.parent = parent;
            current.data = data;
            current.left = null;
            current.right = null;
        } else if(data < current.data) {
            current.left = insert(current.left, data, current); // NULL | value | <value>
        } else {
            current.right = insert(current.right, data, current);
        }

        return current;
    }

    /*
     * Алгоритм обхода двоичного дерева в глубину и в ширину
     */
    void traverse (MyNode current){
        if(current == null)
            return;

        traverse(current.left);
        traverse(current.right);
    }
}

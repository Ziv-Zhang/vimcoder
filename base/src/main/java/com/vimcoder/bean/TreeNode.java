package com.vimcoder.bean;

public class TreeNode<E extends Comparable<E>> {
    // 为了方便使用，直接设置public
    public E element;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode(E element) {
        this(element, null, null);
    }

    public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}

package com.vimcoder.tree;

import com.vimcoder.bean.TreeNode;

/**
 * 普通二叉树的操作
 * 
 * @author apple
 *
 * @param <E>
 */
public class BinSearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;

    /**
     * 二叉树查找
     * 
     * @param e
     * @return
     */
    public boolean search(E e) {
        TreeNode<E> cur = root;
        while(cur != null){
            int c;
            if((c = e.compareTo(cur.element)) < 0){
                // 向左查找
                cur = cur.left;
            }else if(c > 0){
                // 向右子树查找
                cur = cur.right;
            }else{
                // 查找到元素
                return true;
            }
        }
        return false;
    }

    /**
     * 如果已存在e，直接返回false。如果没有存在，就添加e元素并返回true
     * 
     * @param e
     * @return
     */
    public boolean insert(E e) {
        if(e == null){
            return false;
        }
        if(root == null) {
            // 1.如果根节点为空，直接创建根节点
            this.root = createNewNode(e);
        }else{
            // 2.根节点不为空，首先查找e的位置
            TreeNode<E> parent = null;// 控制插入位置的父元素
            TreeNode<E> cur = root;// 控制遍历查找
            while(cur != null){
                int c;// 比较结果
                if((c = e.compareTo(cur.element)) > 0){
                    // 选择右节点遍历
                    parent = cur;
                    cur = cur.right;
                }else if(c < 0){
                    // 选择左节点遍历
                    parent = cur;
                    cur = cur.left;
                }else{
                    // 存在节点
                    return false;
                }
            }
            // 获取插入位置的父节点
            if(e.compareTo(parent.element) > 0){
                parent.right = createNewNode(e);
            }else{
                parent.left = createNewNode(e);
            }
        }

        return true;
    }

    /**
     * 二叉树的删除
     * 
     * @param e
     * @return
     */
    public boolean remove(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> cur = root;
        boolean isLeft = false;
        while(cur != null){
            int c;
            if((c = e.compareTo(cur.element)) < 0){
                parent = cur;
                cur = cur.left;
                isLeft = true;
            }else if(c > 0){
                parent = cur;
                cur = cur.right;
                isLeft = false;
            }else{
                break;
            }
        }
        if(cur == root && e.compareTo(cur.element) != 0){
            // 没有找到
            return false;
        }
        if(cur.left == null && cur.right == null){
            // 左右子树都为空，直接删除
            if(parent == null){
                root = null;
            }else if(isLeft){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }else if(cur.left == null && cur.right != null){
            // 右子树不为空，左子树为空，直接把右子树节点移到父节点
            if(parent == null){
                // 根节点
                root = cur.right;
            }else if(isLeft){
                parent.left = cur.right;
            }else{
                parent.right = cur.right;
            }
        }else if(cur.left != null && cur.right == null){
            // 左子树不为空，右子树为空，直接把左子树接到父节点上
            if(parent == null){
                root = cur.left;
            }else if(isLeft){
                parent.left = cur.left;
            }else{
                parent.right = cur.left;
            }
        }else{
            // 左右子树都不为空，选取左子树的最大值或右子树的最小值替换删除节点，AVL中应该是选取最深的一边
            TreeNode<E> min = popMin(cur.right);
            cur.element = min.element;
        }
        return true;
    }

    private TreeNode<E> popMin(TreeNode<E> root) {
        TreeNode<E> min = root;
        TreeNode<E> parent = null;
        while(min.left != null){
            parent = min;
            min = min.left;
        }
        if(min.right != null){
            // 找到最小值，连接右子树
            parent.left = min.right;
        }
        return min;
    }

    private TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }
    
    /**
     * 先序遍历
     */
    public void printFirst(TreeNode<E> node) {
        if(node == null){
            return;
        }
        System.out.println(node.element);
        printFirst(node.left);
        printFirst(node.right);
    }
    
    /**
     * 中序遍历
     */
    public void printMin(TreeNode<E> node) {
        if(node == null){
            return;
        }
        printMin(node.left);
        System.out.println(node.element);
        printMin(node.right);
    }

    /**
     * 后序遍历
     */
    public void printLast(TreeNode<E> node) {
        if(node == null){
            return;
        }
        printLast(node.left);
        printLast(node.right);
        System.out.println(node.element);
    }

    public static void main(String[] args) {
        BinSearchTree<Integer> tree = new BinSearchTree<Integer>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(8);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(7);
        
        tree.remove(5);
        
        tree.printFirst(tree.root);
        // tree.printMin(tree.root);
        // tree.printLast(tree.root);
    }
}

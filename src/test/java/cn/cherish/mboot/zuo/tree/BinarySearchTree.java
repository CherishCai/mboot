package cn.cherish.mboot.zuo.tree;

import java.util.Stack;

/**
 * 是否二叉搜索树，根据中序遍历是否为递增
 * @author Cherish
 * @version 1.0
 * @date 2017/8/25 16:14
 */
public class BinarySearchTree {
    public static void main(String[] args) {
        Node root = null;
        boolean result = isBST(root);
        System.out.println("result = " + result);
    }

    private static boolean isBST(Node root) {
        if (root == null) {
            return true;
        }

        int tmp = 0x80000000;
        Stack<Node> stack = new Stack<>();
        Node head = root;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                Node pop = stack.pop();
                head = pop.right;

                // 中序遍历，可处理
                if (pop.val < tmp) {
                    return false;
                }
                tmp = pop.val;
            }
        }
        return true;
    }


}

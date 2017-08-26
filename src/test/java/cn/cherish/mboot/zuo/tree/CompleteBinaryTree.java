package cn.cherish.mboot.zuo.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否完全二叉树
 * @author Cherish
 * @version 1.0
 * @date 2017/8/25 15:51
 */
public class CompleteBinaryTree {

    public static void main(String[] args) {

        Node root = null;
        boolean result = isCBT(root);
        System.out.println("result = " + result);
    }

    private static boolean isCBT(Node root) {
        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node l;
        Node r;
        boolean leaf = false;// 表示是否到了叶子层面

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            l = node.left;
            r = node.right;
            if (
                (leaf && (l != null || r != null)) || // 叶子层面，需要以后的孩子都不存在
                (l == null && r != null) // 左孩子没有，有右孩子，明显不平衡了
                ) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;// 进入叶子层面
            }
        }
        return true;
    }

}

package cn.cherish.mboot.zuo.tree;

/**
 * 是否平衡二叉树
 * @author Cherish
 * @version 1.0
 * @date 2017/8/25 17:04
 */
public class BalanceBinaryTree {
    public static void main(String[] args) {
        Node root = null;
        boolean result = isBBT(root);
        System.out.println("result = " + result);
    }

    private static boolean balance = true;
    private static boolean isBBT(Node root) {
        getHeight(root, 1);
        return balance;
    }

    private static int getHeight(Node root, int h) {
        if (root == null) {
            return h;
        }
        int lH = getHeight(root.left, h + 1);
        if (!balance) {
            return h;
        }

        int rH = getHeight(root.right, h + 1);
        if (!balance) {
            return h;
        }
        if (Math.abs(lH - rH) > 1) {
            balance = false;
        }
        return Math.max(lH, rH);
    }
}

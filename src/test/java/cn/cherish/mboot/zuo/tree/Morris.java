package cn.cherish.mboot.zuo.tree;

/**
 * Morris遍历二叉树
 * @author Cherish
 * @version 1.0
 * @date 2017/8/26 9:46
 */
public class Morris {
    public static void main(String[] args) {
        Node root = null;
        morrisIn(root);
    }

    private static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;// 左孩子
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;// 找到左子树的最右节点
                }
                if (cur2.right == null) {// 第一次到达该节点
                    cur2.right = cur1;// 指向后继 cur1
                    cur1 = cur1.left;// 继续向左探索
                    continue;
                } else {// 已经指向了当前 cur1 节点，说明是第二次到达该节点
                    cur2.right = null;
                }
            }
            System.out.println(cur1.val + " ");
            cur1 = cur1.right;// 走向右孩子
        }
        System.out.println();
    }

    private static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;// 左孩子
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;// 找到左子树的最右节点
                }
                if (cur2.right == null) {
                    System.out.println(cur1.val + " ");
                    cur2.right = cur1;// 指向后继 cur1
                    cur1 = cur1.left;// 继续向左探索
                    continue;
                } else {// 已经指向了当前 cur1 节点
                    cur2.right = null;
                }
            }else {
                System.out.println(cur1.val + " ");
            }
            cur1 = cur1.right;// 走向右孩子
        }
        System.out.println();
    }

}

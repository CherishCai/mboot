package cn.cherish.mboot.zuo.tree;

/**
 * 左神之最近公共祖先
 * @author Cherish
 * @version 1.0
 * @date 2017/8/25 15:40
 */
public class LowestAncestor {
    public static void main(String[] args) {
        Node head = null;
        Node n1 = null;
        Node n2 = null;
        Node result = lowestAncestor(head, n1, n2);
        System.out.println("result = " + result);
    }

    private static Node lowestAncestor(Node head, Node n1, Node n2) {
        if (head == null || head == n1 || head == n2) {
            return head;// 找到了其中的一个节点
        }

        Node left = lowestAncestor(head.left, n1, n2);
        Node right = lowestAncestor(head.right, n1, n2);

        // 左右都找到了节点，当前节点是最近公共祖先
        if (left != null && right != null) {
            return head;
        }

        // 返回非空的一个给上一层
        return left != null ? left : right;
    }
}

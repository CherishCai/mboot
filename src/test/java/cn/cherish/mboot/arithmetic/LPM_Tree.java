package cn.cherish.mboot.arithmetic;


import java.util.HashMap;

/**
 * 最长匹配的树路径
 * @author Cherish
 * @version 1.0
 * @date 2017/8/24 8:43
 */
public class LPM_Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-1);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        target = 7;

        traverse(root);

        System.out.println("maxLen = " + maxLen);
    }

    private static int target;//目标值
    private static int sum;//当前和
    private static int height;//树高度
    private static int maxLen;//最长匹配路径
    private static HashMap<Integer, Integer> map = new HashMap<>();
    static {
        map.put(0, 0);
    }


    private static void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        // 1
        height++;
        sum += node.val;
        map.putIfAbsent(sum, height);

        System.out.println("height = " + height);
        System.out.println("sum = " + sum);

        Integer h = map.get(sum - target);
        if (h != null) {
            if (maxLen < height - h) {
                maxLen = height - h;
            }
        }
        traverse(node.left);
        //2

        traverse(node.right);
        //3
        if (h == map.get(sum)) {
            map.remove(sum);
        }
        sum -= node.val;
        height--;
    }


    static class TreeNode {
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode left;
        TreeNode right;
        int val;
    }
}

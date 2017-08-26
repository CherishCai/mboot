package cn.cherish.mboot.zuo.tree;

import java.util.HashMap;

/**
 * 最长定值累加和路径
 * @author Cherish
 * @version 1.0
 * @date 2017/8/26 9:16
 */
public class TreePathMaxLen {
    public static void main(String[] args) {
        int sum = 0;
        Node root = null;
        int len = getMaxLen(root, sum);
        System.out.println("len = " + len);
    }

    private static int getMaxLen(Node root, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);
        return preOrder(root, sum, 0, 1, 0, sumMap);
    }

    private static int preOrder(Node head, int sum, int preSum, int level,
                                int maxLen, HashMap<Integer, Integer> sumMap) {
        if (head == null) {
            return maxLen;
        }

        int curSum = preSum + head.val;
        sumMap.putIfAbsent(curSum, level);// 保留最先出现的 sum 及 level

        if (sumMap.containsKey(curSum - sum)) {// 先前出现过这个差值
            maxLen = Math.max(maxLen, level - sumMap.get(curSum - sum));
        }

        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);

        // 回溯的时候，清楚身为树孩子的总值
        if (level == sumMap.get(curSum)) {
            sumMap.remove(curSum);
        }
        return maxLen;
    }
}

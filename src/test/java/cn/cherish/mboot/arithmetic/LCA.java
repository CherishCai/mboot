package cn.cherish.mboot.arithmetic;

/**
 * 二叉树的最近公共祖先
 * @author Cherish
 * @version 1.0
 * @date 2017/8/2 23:40
 */
public class LCA {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 若是二叉搜索树
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor2(root.left,p,q);
        }else if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor2(root.right,p,q);
        }else {
            return root;
        }
    }

    /**
     * 若是普通二叉树
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 左右子树探索时发现目标节点，则通过返回值标记
        if(root == null || p == root || q == root) {
            return root;
        }

        // 查看左子树中是否有目标结点，没有为null
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        // 查看右子树中是否有目标结点，没有为null
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        //都不为空，说明左右子树都有目标结点，则公共祖先就是本身
        if (l != null && r != null) {
            return root;
        }
        // 其他情况，则要继续向上标记，显示此节点下边有目标节点
        return l != null ? l : r;
    }
}

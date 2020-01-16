import java.util.LinkedList;
import java.util.Queue;

public class symmetric_tree {

    /**
     * 对称二叉树
     * 一颗二叉树的内部对称问题
     */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(4);
        treeNode.left.left = new TreeNode(5);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);


        System.out.println(symmetric_tree_v1(treeNode, treeNode));
        System.out.println(symmetric_tree_v2(treeNode));

    }


    /**
     * 对称树的概念是镜像对称，扩展化是树不是节点，
     * 然后判断子左树和子右数对称对称
     * 以递归形式实现就是判定当前treenode节点是否一致，在判断左树==右树
     */
    public static boolean symmetric_tree_v1(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return t1.val == t2.val
                && symmetric_tree_v1(t1.right, t2.left)
                && symmetric_tree_v1(t1.left, t2.right);
    }


    /**
     * BFS-Breadth First Search,广度优先遍历，每一次都是横扫
     * 将其转换成链表linklist,循环遍历
     * 取出节点,若相等则左右子结点反向插入,再循环匹配
     * 直到队列为空时返回true，或者中间检测出不对称则为false
     * 否则一直接续
     * 1ms,59.01%,37.9,65.05%
     */
    public static boolean symmetric_tree_v2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;

    }

    //二叉树结构
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}



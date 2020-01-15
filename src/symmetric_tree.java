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
    }


    /**
     * 对称树的概念是子树左右对称，所有左右子树都对称则是一颗对称二叉树
     * 以递归形式实现就是扩展化，
     * 判定当前treenode节点是否一致，在判断left和right是否一致
     * @param t1
     * @param t2
     * @return
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



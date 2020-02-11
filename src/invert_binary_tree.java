import java.util.LinkedList;
import java.util.Queue;

public class invert_binary_tree {

    /**
     * 翻转二叉树
     * 注意null的left或right也是可以翻转的
     */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(4);



        TreeNode node = invert_binary_tree_v2(treeNode);
        node = null;
    }

    /**
     * 思路：递归二叉树，将当前层级的左右子分支交换，并逐层返回
     * 0，100%，50，5%
     */
    public static TreeNode invert_binary_tree_v1(TreeNode root) {
        if (root == null || root.left == null || root.right == null)
            return root;
        TreeNode left = invert_binary_tree_v1(root.left);
        root.left = invert_binary_tree_v1(root.right);
        root.right = left;
        return root;
    }

    /**
     * 思路：扔进链表中，从根部起迭代逐层循序每个树节点并左右交换，直到当前树节点没有left及right
     * 注意:链表负责更换相连的treednode树节点，并没有去删除或新增树节点对象，完成后返回根节点root
     */

    public static TreeNode invert_binary_tree_v2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur =  queue.poll();  //如果不判断root是否为空，则可以执行到这里，cur为null，就会报空指了
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return root;
    }


        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            public TreeNode(int value) {
                this.val = value;
            }
        }
    }


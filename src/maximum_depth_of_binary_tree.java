import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class maximum_depth_of_binary_tree {

    /**
     * 二叉树的最大深度
     */

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(4);
        treeNode.left.left = new TreeNode(5);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);


        System.out.println(maximum_depth_of_binary_tree_v2(treeNode));

    }

    /**
     * 思路： DFS（深度优先搜索）
     * 递归，一个二叉树为一个对象，注意二叉树结构的独立性，只是负责计算深度
     * 实际只是判断当前有无值，然后计算深度+1，并返回给上支，该上支可能是left也可能是right
     * 递归的用法：返回是理解为上层，而实现的时候是下沉到下层，返回值为上层，所谓递归
     * 0，100%，39m,15%
     */
    public static int maximum_depth_of_binary_tree_v0(TreeNode treeNode) {
        if (treeNode == null)
            return 0;
        int depth_left = maximum_depth_of_binary_tree_v0(treeNode.left);
        int depth_right = maximum_depth_of_binary_tree_v0(treeNode.right);
        return Math.max(depth_left, depth_right) + 1;
    }


    /**
     * DFS-依然是遍历左右子树
     * 递归是直接返回深度，v2版是将深度以Pair的形式存在queue中，
     * 并每次比较，且同时更新当前深度，最后得出最大深度
     */

    public static int maximum_depth_of_binary_tree_v2(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.left, current_depth + 1));
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return depth;

    }

    /**
     * BFS-广度搜索实现
     * 需要两层循环，一个是深度，一个是区分深度层次上的不同树的循环，随后获取最终深度，
     * 1ms，47.05%，39.1，14.95%
     */
    public static int maximum_depth_of_binary_tree_v3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            maxDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return maxDepth;

    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            this.val = v;
        }

    }

}

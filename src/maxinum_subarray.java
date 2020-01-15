import sun.tools.jconsole.MaximizableInternalFrame;

public class maxinum_subarray {

    /**
     *最大子序和
     *求最大和的连续子数组（子数组最少包含一个元素），返回其和值
     * 动态规划法和贪心法
     */
    public static void main(String[] args) {

        int[] num = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxinum_subarray_v2(num));
    }


    /**
     * 思路：贪心法，每一步都选择最佳方案，到最后就是全局最优的方案，线性解决，最简单
     * 具体：当前元素和当前元素位置的和，以及迄今为止最大和三者比较
     * 1s,99.98,36.9,88.95%
     */
    public static int maxinum_subarray_v2(int[] nums) {
        int curSum = nums[0];
        int maxSum = curSum;
        for (int i = 1, size = nums.length; i < size; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;

    }

    /**
     * 思路：动态规划法DP，采用常数空间标准，贪心有些类似，沿数组移动并在原数组上修改(和贪心差别)
     * 具体：修改数组跟踪当前位置的最大和后更新全局最大和
     * 1s,99.98,38.7,82.12%
     */
    public static int maxinum_subarray_v3(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1, size = nums.length; i < size; i++) {
            if(nums[i-1]>0){
                nums[i]+=nums[i-1];
            }
             maxSum=Math.max(nums[i],maxSum);
        }
        return maxSum;

    }


    public static int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    public static int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int p = (left + right) / 2;

        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    /**
     * 分治法，不好理解，大量递归，相当不擅长；
     * 将问题分解为子问题并递归地解决它们，合并子问题的解以获得原始问题的解。
     * 具体：求左(递归)，求右(递归)，求左右和(在递归内部)，在三选最大值
     * 1s,99.98,36.9,94.59%
     */
    public static int maxinum_subarray_v1(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
}

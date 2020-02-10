public class hourse_robber {

    /**
     * 打家劫舍
     * 找到数组中非相邻数的值的和的最大值
     */
    public static void main(String[] args) {

        System.out.println(hourse_robber_v0(new int[]{2,7,9,3,1}));
    }

    /**
     * 解题思路：暴力求奇偶位置和
     * when当前偶数位和没有上一个奇数位和大时，则将最大值替换,奇数位亦然，互相替换
     * 遍历循环一次，就得至少间隔数为1个单位的数值最大和
     * 0ms,100%,34,79%
     */
    public static int hourse_robber_v1(int[] nums) {
        int max_one = 0;
        int max_two = 0;
        for (int i = 0, size = nums.length; i < size; i++) {
            if (i % 2 == 0) {
                max_one += nums[i];
                if (max_one < max_two)
                    max_one = max_two;
            } else {
                max_two += nums[i];
                if (max_two < max_one)
                    max_two = max_one;
            }
        }
        return max_one > max_two ? max_one : max_two;
    }


    /**
     * 解题思路：动态规划法
     * 对于n=3时，要么n1+n3，要么选择n2,所以得出公式f(k) = max(f(n – 2) +n, f(n – 1))
     * 设定f(–1) = f(0) = 0为初始情况来优化代码，preMax和curMax分别保存对应的值，循环替换
     * 0ms,100%,34,61%
     */
    public static int hourse_robber_v0(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

}


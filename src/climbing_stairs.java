public class climbing_stairs {

    /**
     * 爬楼梯
     * 爬n阶，每次可爬1或2个台阶，求共多少种组合--斐波那契额函数
     * 动态规划法
     */

    public static void main(String[] args) {
      System.out.println( climb_Stairs_v1(0, 3));
        System.out.println( climb_Stairs_v0(5));

    }

    /**
     * 思路：暴力解法，递归
     * 递归下发自我循环调用理解没有问题，关键是return，每一个sub自我的return
     * 不太理解
     */
    public static int climb_Stairs_v1(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs_v1(i + 1, n) + climb_Stairs_v1(i + 2, n);
    }


    /**
     *思路：动态规划法DP，第N个台阶的方法数=n-1台阶方法数+n-2台阶方法数的方法数和
     * 因为每次可以爬1 或 2 个台阶，所以不是n-1就是n-2，switch
     * n=Fib(n)=Fib(n−1)+Fib(n−2)就是斐波那契函数
     *0,100%,33.1,71.99%
     */
    public static int climb_Stairs_v2(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /**
     *思路：DP实现的斐波那契，v2改良版
     *0,100%,33,73.21%
     */
    public static int climb_Stairs_v0(int n) {
        if (n == 1) {
            return 1;
        }
        int first= 1;
         int second=2;
         int third;
        for (int i = 3; i <= n; i++) {
            third=first+second;
            first=second;
            second=third;
        }
        return second;
    }


}

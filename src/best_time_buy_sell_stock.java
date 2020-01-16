public class best_time_buy_sell_stock {

    /**
     * 买卖股票的最佳时机，求最大利润
     * 输入: [7,1,5,3,6,4]，输出: 5
     * 即在第2天买入，第5天卖出获得最大差值5。
     **/

    public static void main(String[] args) {
        System.out.println(bese_time_buy_sell_stock(new int[]{1,3,7,3,2,15}));
    }


    /**
     * 思路：循环一次，线性遍历保证顺序，分别保存低谷，和最大差值，并返回后者
     * 另有暴力解法，两次循环，遍历所有差值，选择最大
     * 1ms,99.99%,37.5,74%
     */
    public static int bese_time_buy_sell_stock(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0, size = prices.length; i < size; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;

            }
        }
        return maxProfit;
    }

}

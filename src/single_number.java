import java.util.*;

public class single_number {
    public static void main(String[] args) {
        System.out.println(single_number_v2(new int[]{1, 2, 3, 4, 1, 6, 3, 4, 2}));
//        System.out.println(single_number_v3(new Integer[]{1, 2, 3, 4, 1, 6, 3, 4, 2}));
        System.out.println(single_number_v0(new int[]{1, 2, 3, 4, 1, 6, 3, 4, 2}));
    }

    /**
     * 思路：比暴力解法好一点，采用栈匹配并弹出的方式
     * 暴力解法是两次循环，遍历数组+已遍历内容是否含有数字
     * 114,7.21%,40.2,78.76%
     */
    public static int single_number_v1(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, size = nums.length; i < size; i++) {
            if (stack.contains(nums[i]))
                stack.remove((Integer) nums[i]);
            else stack.add(nums[i]);
        }
        return stack.get(0);
    }

    /**
     * 思路：同上，但数据结构采用hashmap
     * 同样的匹配，hashmap比栈和linkedlist等结构好
     * 因为查找匹配数组不需要循环，减少O(n),链表结构式插入方便但查找费力
     * 11ms，26.92%,39.9,86.8%
     */
    public static int single_number_v2(int[] nums) {
        HashMap<Integer, Integer> stack = new HashMap<>();
        for (int i = 0, size = nums.length; i < size; i++) {
            if (stack.containsKey(nums[i]))
                stack.remove((Integer) nums[i]);
            else stack.put(nums[i], nums[i]);
        }

        for (Integer key : stack.keySet()) {
            return stack.get(key);
        }

        return 0;
    }

    /**
     * 思路：数学-2∗(a+b+c)−(a+a+b+b+c)=c
     * python比较好实现，java需要collection api
     * 以及两次遍历，一次求和，一次乘法
     * 8ms,30.63%,38.3,96.7%
     */
    public static int single_number_v3(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        int sum = 0;
        for (int i = 0, size = nums.length; i < size; i++) {
            sum += nums[i];
            set.add(nums[i]);
        }

        int sum2 = 0;
        for (Integer num : set) {
            sum2 += num;
        }
        return 2 * sum2 - sum;
    }


    /**
     * 思路：XOR异或运算
     * 不同为1，相同为0，最后计算出不同的那个值
     * a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
     * 1ms,99.56%,39.3,.21.68%
     */
    public static int single_number_v0(int[] nums) {
        int num = nums[0];
        for (int i = 1, size = nums.length; i < size; i++) {
            num = num ^ nums[i];
        }

        return num;

    }
}
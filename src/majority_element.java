import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 多数元素
 * 找到数组中的多数元素，即出现次数大于n/2次的元素
 * 注意；必须>n/2,就是设定数组中必须有众数，否则会出错
 */
public class majority_element {
    public static void main(String[] args) {

        int[] a = new int[]{1, 6, 5, 3, 5, 6, 5, 6, 7, 6, 7, 5, 6};

        System.out.println(majority_element_v0(a));
        System.out.println(majority_element_v2(a));


        //System.out.println(majority_element_v4(a, 0, a.length - 1));
    }


    /**
     * 思路：基础解法，逐个遍历匹配，借用hashmap降低维度，在线性时间内使用entrySet遍历nums
     * 20ms,32%,41,58%
     */
    public static int majority_element_v1(int nums[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }


    /**
     * 思路：先按大小排序，寻找相应下表位置对应的元素
     * 因次数>n/2,所以无论基偶多数元素需出现次数=（n+1）/2,其一定处于下坐标nums.length/2上
     * 当然偶数情况时，则会是nums.length/2+1也是该元素
     * 1,100%,42.7,6%
     */
    public static int majority_element_v2(int nums[]) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 思路： Boyer-Moore投票法，计算器
     * 循环查询，是众数+1，非众数-1，计数器为0则更换众数，因出现次数肯定>n/2，所以循环完毕会得到最后结果
     * 1ms,100%,42,5%
     */
    private static int majority_element_v0(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int count = 0;
        int mNum = nums[0];
        for (int i = 0, size = nums.length; i < size; i++) {
            if (count == 0) {
                mNum = nums[i];
            }
            if (mNum == nums[i]) {
                count++;
            } else
                count--;
        }
        return mNum;


        //优化写法，但因为+1一行代码效率变为2ms
//        for (int num : nums) {
//            if (count == 0) {
//                mNum = num;
//            }
//            count += (num == mNum) ? 1 : -1;
//        }
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }


    private static int majority_element_v4(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }

        int mid = (hi - lo) / 2 + lo;
        int left = majority_element_v4(nums, lo, mid);
        int right = majority_element_v4(nums, mid + 1, hi);

        if (left == right) {
            return left;
        }

        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

}

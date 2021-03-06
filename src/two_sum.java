
import java.util.Arrays;
import java.util.HashMap;

public class two_sum {

    public static void main(String args[]) {
        //v2最优，其次v3
        int[] value = twoSum_v2(new int[]{-1, -2, -3, -4, -5}, -8);
        System.out.println("answer:" + value[0] + "," + value[1]);
    }


    /**
     * 思路：利用java自带DualPivotQuicksort排序,虽然系统排序算法快，时间复杂度为O(nlogn)
     * 2 ms,99.36%,37.4 MB,88.96%
     */
    public int[] twoSum_v1(int[] nums, int target) {
        int m = 0, n = 0, k;
        int[] res = new int[2];
        int[] tmp1 = new int[nums.length];
        System.arraycopy(nums, 0, tmp1, 0, nums.length);
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] < target)
                i++;
            else if (nums[i] + nums[j] > target)
                j--;
            else if (nums[i] + nums[j] == target) {
                m = i;
                n = j;
                break;
            }
        }
        for (k = 0; k < nums.length; k++) {
            if (tmp1[k] == nums[m]) {
                res[0] = k;
                break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp1[i] == nums[n] && i != k)
                res[1] = i;
        }
        return res;
    }


    /**
     * 思路：进一步优化，减少T(n)
     * 2 ms,99.39%,38.3 MB,70.04%
     */
    public static int[] twoSum_v2(int[] nums, int target) {
        int m = 0, n = 0, k;
        int[] res = new int[2];
        int[] tmp1 = new int[nums.length];
        System.arraycopy(nums, 0, tmp1, 0, nums.length);
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] < target)
                i++;
            else if (nums[i] + nums[j] > target)
                j--;
            else if (nums[i] + nums[j] == target) {
                m = i;
                n = j;
                break;
            }
        }

        int index1 = -1, index2 = -1;
        for (k = 0; k < nums.length; k++) {
            if (tmp1[k] == nums[m] && index1 == -1 && k != index2) {
                index1 = k;
            } else if (tmp1[k] == nums[n] && k != index1 && index2 == -1) {
                index2 = k;
            }
            if (index1 != -1 && index2 != -1) {
                if (index1 > index2) {
                    res[0] = index2;
                    res[1] = index1;
                } else {
                    res[0] = index1;
                    res[1] = index2;
                }
                break;
            }
        }
        return res;
    }



    /**
     * 思路：哈希查询,
     * 2 ms,97.55%,37.1 MB,92.13%
     */
    public int[] twoSum_v3(int[] nums, int target) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int i = 0, size = nums.length; i < size; i++) {
            if (hashmap.containsKey(target - nums[i])) {
                int index2 = hashmap.get(target - nums[i]);
                if (index2 != i)
                    return new int[]{index2, i};
            }
            hashmap.put(nums[i], i);
        }
        return new int[]{0, 0};
    }
}

import java.util.ArrayList;
import java.util.List;

public class move_zeros {
    public static void main(String[] args) {

        System.out.println(move_zeros_v3(new int[]{1,2,0,1,3}));
    }

    /**
     * 思路：暴力稍稍优化，循环2次，首先顺序生成非0数组，后重置nums并将其余位置设置为0
     * 2ms,17%,47,5%
     */
    public static int[] move_zeros_v1(int nums[]) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, size = nums.length; i < size; i++) {
            if (nums[i] != 0) {
                ans.add(nums[i]);
            }
        }

        int length = ans.size();
        for (int i = 0, size = nums.length; i < size; i++) {
            if (i >= length) {
                nums[i] = 0;
            } else nums[i] = ans.get(i);
        }

        return nums;
    }


    /**
     * 思路：数组内部自我替换，两次循环解决
     * 循环遍历非0时依次放入数组下标，并更新下标值，完成后，将最后下标值到数组size之间的值置位0
     */
    public static int[] move_zeros_v2(int nums[]) {
        int index = 0;
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] != 0)
                nums[index++] = nums[i];
        }
        for (int i = index; i < size; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    /**
     * 思路：某种排序思路，一次循环解决；将非0元素移到当前位置减去0元素个数的位置上，并用0填充当前位置
     * 缺点：使用场景优先，变通窄
     *0,100%,48,5%
     */
    public static int[] move_zeros_v3(int nums[]) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else if(count>0){
                nums[i - count] = nums[i];
                nums[i] = 0;
            }
        }

        return nums;
    }

    /**
     * 思路：简易版快速排序确定待分割中间点x，然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边。
     * 当前x=0，使用当前指针i和慢指针j，只要nums[i]!=0，就交换nums[i]和nums[j]，然后j++(保证下一循环时nums[j]==0)
     * 0，100%，39，97%
     */
    public static int[] move_zeros_v0(int nums[]) {
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
        return nums;
    }


}

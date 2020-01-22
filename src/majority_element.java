import java.util.Arrays;


/**
 * 多数元素
 * 找到数组中的多数元素，即出现次数大于n/2次的元素
 */
public class majority_element {
    public static void main(String[] args) {

    }


    /**
     * 逐个遍历匹配，借用hashmap降低维度，在线性时间内遍历nums
     */
    public int majority_element_v1(int nums[]){
        Arrays.sort(nums);
        return nums[nums.length/2];
    }


    /**
     * 思路：先按大小排序，寻找相应下表位置对应的元素
     * 因次数>n/2,所以无论基偶多数元素需出现次数=（n+1）/2,其一定处于下坐标nums.length/2上
     * 当然偶数情况时，则会是nums.length/2+1也是该元素
     * 1,100%,42.7,6%
     */
    public int majority_element_v2(int nums[]){
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}

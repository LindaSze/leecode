fun main() {
    var value = twoSum_v1(intArrayOf(3, 2, 4), 6)
    println("twoSum_v1:"+value[0].toString() + "," + value[1])
}

/**
 * 执行用时 :716 ms,
 * 在所有 Kotlin 提交中击败了5.24%的用户
 * 内存消耗 :37.2 MB, 在所有 Kotlin 提交中击败了40.34%的用户
 */
fun twoSum_v1(nums: IntArray, target: Int): IntArray {
    var value: IntArray = intArrayOf(0, 0)
    var isFind: Boolean = false
    for ((index, num1) in nums.withIndex()) {
        for (j in index + 1 until nums.size) {
            value = intArrayOf(index, j)
            if (num1 + nums[j] == target) {
                isFind = true;
                break;
            }
        }
        if (isFind)
            break;
    }
    return value
}


fun twoSum_v2(nums: IntArray, target: Int): IntArray {
    var value: IntArray = intArrayOf(0, 0)
    var isFind: Boolean = false
    for ((index, num1) in nums.withIndex()) {
        for (j in index + 1 until nums.size) {
            value = intArrayOf(index, j)
            if (num1 + nums[j] == target) {
                isFind = true;
                break;
            }
        }
        if (isFind)
            break;
    }
    return value
}

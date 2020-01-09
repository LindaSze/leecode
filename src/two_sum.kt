fun main() {
    var value = twoSum_v3(intArrayOf(3, 3), 6)
    println("twoSum:" + value[0].toString() + "," + value[1])

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


/**
 * 思路：用空间换时间
 * 执行用时 :240 ms,
 * 在所有 Kotlin 提交中击败了47.18%的用户
 * 内存消耗 :36.7 MB, 在所有 Kotlin 提交中击败了47.18%的用户
 */
fun twoSum_v2(nums: IntArray, target: Int): IntArray {
    var hashmap = hashMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        hashmap.set(num, index)
    }

    for ((index, num) in nums.withIndex()) {
        if (hashmap.containsKey(target - num)) {
            var index2 = hashmap.get(target - num)
            if (index2 != index)
                return intArrayOf(index, index2!!)
        }
    }

    return intArrayOf(0, 0)
}


/**
 * 思路：变更为一次循环
 * 执行用时 :212 ms,
 * 在所有 Kotlin 提交中击败了89.92%的用户
 * 内存消耗 :36.7 MB, 在所有 Kotlin 提交中击败了47.18%的用户
 */
fun twoSum_v3(nums: IntArray, target: Int): IntArray {
    var hashmap = hashMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        if (hashmap.containsKey(target - num)) { //contain的是先找到的那个，set sum是后
            var index2 = hashmap.get(target - num)
            if (index2 != index)
                return intArrayOf(index2!!, index)
        }
        hashmap.set(num, index)  //加入的是第一个数字，后置
    }

    return intArrayOf(0, 0)
}


/**
 * 思路：indexof数组索引，indexOf循环遍历已经有O(n)，实际和暴力运算一样
 * 执行用时 :248 ms,
 * 在所有 Kotlin 提交中击败了34.44%的用户
 * 内存消耗 :36.7 MB, 在所有 Kotlin 提交中击败了47.18%的用户
 */
fun twoSum_v4(nums: IntArray, target: Int): IntArray {
    for (index in nums.indices) {
        var index2 = nums.indexOf(target - nums[index])
        if (index2 > -1 && index != index2) {
            return intArrayOf(index, index2)
        }
    }
    return throw IllegalArgumentException("")
}



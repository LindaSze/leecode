fun main() {
    //v0,v5较优
    var value = twoSum_v5(intArrayOf(2, 11, 7, 15), 9)
    println("answer:" + value[0].toString() + "," + value[1])



    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
            print(it)
        }
    }
    print(" done with nested loop")
}


/**
 * 716 ms,5.24%,37.2 MB,40.34%的用户
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
 * 240 ms,47.18%,36.7 MB,47.18%
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
 * 思路：反向计算出差值，后匹配查询，变更为一次循环
 * 212 ms,89.92%,36.7 MB,47.18%
 */
fun twoSum_v0(nums: IntArray, target: Int): IntArray {
    var hashmap = hashMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        if (hashmap.containsKey(target - num)) { //contain的是先找到的那个，set sum是后
            var index2 = hashmap.get(target - num)
            if (index2 != index)
                return intArrayOf(index2!!, index)
        }
        hashmap.set(num, index)  //加入的是第一个数字，后置
    }

    throw  IllegalArgumentException("No two sum solution");
}


/**
 * 思路：indexof数组索引，indexOf循环遍历已经有O(n)，实际和暴力运算一样
 * 248 ms,34.44%,36.7 MB,47.18%
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


/**
 * 思路：顺序排列找出索引位置，二次循环找出对应原索引
 * 212 ms,89.92%,37 MB,41.18%
 */
fun twoSum_v5(nums: IntArray, target: Int): IntArray {
    var m = -1
    var n = -1
    var res = IntArray(2)
    var temp = nums.copyOf()

    var i = 0
    var j = nums.size - 1
    nums.sort()
    while (i < j) {
        if (nums[i] + nums[j] < target)
            i++
        else if (nums[i] + nums[j] > target)
            j--
        else if (nums[i] + nums[j] == target) {
            m = i
            n = j
            break
        }
    }

    var index1 = -1
    var index2 = -1
    for (k in nums.indices) {
        if (temp[k] == nums[m] && index1 == -1 && k != index2) {
            index1 = k
        } else if (temp[k] == nums[n] && k != index1 && index2 == -1) {
            index2 = k
        }
        if (index1 != -1 && index2 != -1) {
            if (index1 > index2) {
                res[0] = index2
                res[1] = index1
            } else {
                res[0] = index1
                res[1] = index2
            }
            break
        }
    }

    return res
}



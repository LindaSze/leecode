fun main() {

    val l1 = ListNode(1)
    l1.next = ListNode(3)
    l1.next?.next = ListNode(5)

    val l2 = ListNode(2)
    l2.next = ListNode(4)
    l2.next?.next = ListNode(6)

    val ll = merge_two_sorted_lists_v1(l1, l2)
    println(ll?.next)
}


/**
 * 思路：迭代，比较好
 * 184 ms,80.52%,33.1 MB, 45.45%
 */
fun merge_two_sorted_lists_v1(l1: ListNode?, l2: ListNode?): ListNode? {
    var l1 = l1
    var l2 = l2
    var prehead: ListNode = ListNode(-1)
    var prev: ListNode = prehead;
    while (l1 != null && l2 != null) {
        if (l1.`val` < l2.`val`) {
            prev.next = l1
            l1 = l1.next
        } else {
            prev.next = l2
            l2 = l2.next
        }
        prev = prev.next!!
    }

    prev.next = l1 ?: l2
    return prehead.next
}


/**
 * 172 ms,96.10%,32.6 MB, 72.73%
 */
fun merge_two_sorted_lists_v2(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null)
        return l2
    if (l2 == null)
        return l1
    if (l1.`val` < l2.`val`) {

        l1.next = merge_two_sorted_lists_v2(l1.next, l2)
        return l1;
    } else {
        l2.next = merge_two_sorted_lists_v2(l2.next, l1)
        return l2;
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
import java.util.HashSet;

public class intersection_of_two_linked_lists {

    /**
     * 相交链表
     * 找到两个单链表相交的起始节点
     */
    public static void main(String[] args) {

        ListNode dot = new ListNode(6);

        ListNode headA = new ListNode(3);
        headA.next = new ListNode(3);
        headA.next.next = dot;
        headA.next.next.next = new ListNode(3);

        ListNode headB = new ListNode(3);
        headB.next = new ListNode(3);
        headB.next.next = new ListNode(3);
        headB.next.next.next = dot;
        headB.next.next.next.next = new ListNode(3);

        System.out.println(intersection_of_two_linked_lists_v0(headA, headB).val);
    }


    /**
     * 思路：将链表转为hash降低维度，然后匹配判断是否存在同一个listnode
     * 10ms,15.84%，41，17%
     */
    public static ListNode intersection_of_two_linked_lists_v1(ListNode headA, ListNode headB) {
        HashSet<ListNode> hashSet = new HashSet<>();

        while (headA != null) {
            hashSet.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (hashSet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }


    /**
     * 思路：双指针法，环形链表概念
     * 另a链表尾连接到b表头，b表尾到a表头，则存在a+b相较前节点=b+a相交前节点，即检测出相交点
     * 1ms,100%,49,15%
     */
    public static ListNode intersection_of_two_linked_lists_v0(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a.next;
            b = b.next;
            if (a == null && b == null) {
                return null;
            }
            if (a == null) {
                a = headB;
            }
            if (b == null) {
                b = headA;
            }
        }
        return a;


    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}

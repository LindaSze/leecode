import java.util.HashMap;
import java.util.HashSet;

public class linked_list_circle {

    /**
     * 环形链表
     * 链表中有一个环，其尾部连接链表中的某个节点。
     */

    public static void main(String[] args) {

        ListNode dot= new ListNode(1);
        ListNode listNode = new ListNode(1);
        listNode.next = dot;
        listNode.next.next =new ListNode(1);
        listNode.next.next.next = dot;
        System.out.println(linked_list_circle_v0(listNode));
    }

    /**
     * 思路：匹配校验
     * 存下当前的节点，如果已经存在节点了，说明被再次引用，有环存在
     * 6ms,26%,38,12%
     */
    public static boolean linked_list_circle_v1(ListNode head) {

        if (head == null || head.next == null)
            return false;
        HashSet<ListNode> set = new HashSet();
        while (head != null) {
            if (set.contains(head))
                return true;
            else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }


    /**
     * 思路：快慢指针，慢指针走一步，快指针走两步，如果存在追上的时候就说明有环
     * 快指针的具体表现实行为指node.next，比hashmap节省了空间复杂度
     * 根据非环内的长度不同，快指针的实际表现为有可能是进入环后的double反超
     * 0,100%,38,14%
     */

    public static boolean linked_list_circle_v0(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null)
                return false;
            else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return true;
    }

    /**
     * 思路：透过现象看本质，环就是我指我自己,
     * 当然具体实现思想还是快慢指针，只是逐步砍掉中间结构，破坏掉链表，减少空间复杂度
     * 0.100%,37,54%
     */
    public static boolean linked_list_circle_v3(ListNode head) {
        if (head == null || head.next == null)
            return false;

        while (head != null && head.next != null) {
            if (head.next == head)
                return true;
            else {
                head.next = head.next.next;
                head = head.next;
            }

        }
        return false;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int value) {
            this.val = value;

        }
    }

}


import java.util.List;

public class merge_two_sorted_lists {
    /**
     * 合并两个有序链表
     * 输入：1->2->4, 1->3->4，输出：1->1->2->3->4->4
     * LiseNode链表结构的连接问题
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);

        ListNode ll = merge_two_sorted_lists_v0(l1, l2);
        System.out.println(ll.val);

    }

    /**
     * 思路：递归，listnode含自有属性以及下一个链表对象的指向性，若next=null则说明到尾部,next和listnode本身是解耦的，只是负有指向性关系而已
     * 1 ms,82.77%,39.9 MB, 62.87%
     */
    public static ListNode merge_two_sorted_lists_v1(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = merge_two_sorted_lists_v1(l1.next, l2);
            return l1;
        } else {
            l2.next = merge_two_sorted_lists_v1(l2.next, l1);
            return l2;
        }


    }


    /**
     * 思路：充分利用链表指向性，prehead指向最后形成的连接，prev负责找出当前node中的最小值，当l1,l2,prev完成一轮任务后要后移；
     * 迭代思想，prehead为哑节点
     * 1 ms,82.77%,37.2 MB, 80.32%
     */
    public static ListNode merge_two_sorted_lists_v0(ListNode l1, ListNode l2) {
        ListNode prehead=new ListNode(-1);
        ListNode prev =prehead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next=l1;
                l1 = l1.next;
            } else {
                prev.next=l2;
                l2 = l2.next;
            }

            prev=prev.next;
        }
        prev.next=l1==null?l2:l1;
        return prehead.next;
    }


    static class ListNode {
        ListNode next;
        int val;

        ListNode(int value) {
            this.val = value;
        }

    }


}

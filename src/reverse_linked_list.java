public class reverse_linked_list {
    /**
     * 反转链表
     * 输入: 1->2->3->4->5->NULL，输出: 5->4->3->2->1->NULL
     */
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        reserve_linked_list_v0(listNode);
    }

    static class ListNode {
        ListNode next;
        int val;

        ListNode(int value) {
            this.val = value;
        }
    }

    /**
     * 思路：循环迭代，将当前节点的next连接到前节点值（首节点的next连接到null）
     * 将形成的新节点保存，再移除该缓存节点头部并更新为新的节点，往复循环
     * 0s,100,37,5%
     */
    public static ListNode reserve_linked_list_v1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 思路：反转即为head.next.next=head，链表指向变为反向，将链表逆向链接后再斩断顺序链接
     * 注意：
     * 链表中的各个节点是独立的对象，并决定了其next节点
     * 即链表可能是不同的序列指向，但节点只能是那4个节点
     * p是反转完毕的链表头结点，head.next是已经反转链表的尾结点，然后通过指定尾节点的next节点的方式，即设置head.next.next从而将新的节点加入反转链表中，然后返回表头即可
     * 注意将尾节点的next置位null,不然将死循环
     * 0ms
     */
    public static ListNode reserve_linked_list_v0(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reserve_linked_list_v0(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}

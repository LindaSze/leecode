import java.util.ArrayList;
import java.util.List;

public class palindrome_linked_list {
    public static ListNode frontPointer;


    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);

        frontPointer = node;
        System.out.println(palindrome_liked_list_v5(node));
    }

    /**
     * 思路：将链表转为数组，然后双指针法，一次循环进行首尾对称比较值是否相同
     * 转数组方法：循环节点的next，直到节点为空并存储值节点的值
     * 4ms,26.17,41,57%
     */
    public static boolean palindrome_liked_list_v1(ListNode head) {
        List<Integer> nums = new ArrayList<Integer>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }

        for (int i = 0, j = nums.size() - 1; i < j; i++) {
            if (nums.get(i).equals(nums.get(j)))
                j--;
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * 思路：同上，只是前后对称的值比较采用while来实现
     * 5ms,17%,5,15
     */
    public static boolean palindrome_liked_list_v2(ListNode head) {
        List<Integer> nums = new ArrayList<Integer>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        int front = 0;
        int back = nums.size() - 1;
        while (front < back) {

            if (!nums.get(front).equals(nums.get(back)))
                return false;
            else {
                front++;
                back--;
            }
        }
        return true;
    }


    /**
     * 思路：快慢指针法，慢针走一步，快针走两步，反转后半部分
     * 具体:
     * 1、先快慢遍历一次链表，快针移动到尾部，慢针位于链表后半部分的头结点,即获得后半部分链表
     * 2.将慢链表reversen后得到反转链表，再和正常链表匹配，全部相当则为回文链表
     * 3.因为节点是独立的对象，所以需要再判断完毕，再将反转链表再次反转恢复原来的head序列,即将原序列的尾节点重新设置回去
     * 若链表有奇数个节点，则中间的节点应该看作是前半部分。 //实际p2永远断于p1，后半部分肖爱玉前半部分
     * 空链表算回文
     * 2,48%,48,5%
     */
    public static boolean palindrome_liked_list_v0(ListNode head) {
        if (head == null) return true;

        ListNode halfEnd = endOfFirstHalf(head);
        ListNode reHalfEnd = reverseList(halfEnd.next);

        ListNode p1 = head;
        ListNode p2 = halfEnd;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val)
                result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        halfEnd.next = reverseList(reHalfEnd);
        return result;
    }

    private static ListNode reverseList(ListNode head) {
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

    private static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode reserveList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode prev = reserveList2(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }


    /**
     * 思路：快慢双指针法，分开并反转前半部分然后比较，2次循环完成
     * 一次快指针循环，获取到后半部分，利用slow同时借用pre,prepare实现前半部分的反转---优秀
     * 1,99%,47,5
     */
    public static boolean palindrome_liked_list_v4(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while (pre != null && slow != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }


    /**
     * 思路：递归实现，先入后出的栈机制
     * 递归内部回溯时实际按反转顺序循环链表，然后外置一个正序链表，判断不等则为非回文链表
     */
    public static boolean palindrome_liked_list_v5(ListNode currentNode) {
        if (currentNode != null) {
            if (!palindrome_liked_list_v5(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int value) {
            this.val = value;
        }
    }
}

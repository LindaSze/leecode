import java.util.Stack;

public class min_stack {
    /**
     * 设计一个结构支持push，pop，top 操作，并能检索到最小元素的栈。
     *重点解决pop后如何更新当前最小值
     */
    public static void main(String[] args) {
    }

    /**
     * 思路：辅助栈，用来存储最小值，注意出栈控制更新
     * helper stack至少保存两层最小值，以防pop最小值时
     * 7ms,90%,32,97%
     */
    class MinStack_v1 {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> hepler = new Stack<>();

        public void pop() {
            if (stack.pop() == hepler.peek())
                hepler.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            if (hepler.empty())
                return 0;
            return hepler.peek();
        }

        public void push(int x) {
            stack.push(x);
            if (hepler.empty())
                hepler.push(x);
            else if (x <= hepler.peek()) {
                hepler.push(x);
            }
        }
    }


    /**
     * 采用一个栈解决最小值出栈后的新的最小值问题，以空间换时间
     * 保存最小值，并在新的最小值入栈前将前最小值再次入栈保存，即第二层是second最小的值，并在出栈时更新最小值
     * 同时，如果出栈的是最小值，还要保持属于一致性将临时的保存second最小的值也弹出
     * 注意，“等于”要考虑进去，因出栈的时候，连续相等的并且是最小值的元素要同步出栈， 即更新到最后的最小值
     * 7ms,90%,41,18%
     */
    class MinStack_v2 {
        Stack<Integer> stack = new Stack<Integer>();
        int min = Integer.MAX_VALUE;

        public void pop() {
            if (stack.pop() == min) {
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public int getMin() {
            return min;
        }
    }


    /**
     * 链表结构解决问题,降维数据结构
     * 使链表符合栈结构，即后入先出，所以将push的数据放在表头，同时存储当前链表的最小值，这样pop无需再处理
     * 学习链表的插入--插入中间和插入在表头，因为链表的next顺序特性
     * 5ms,100%36,94.4%
     */
    class MinStack_v3 {
        ListNode listNode;
        int min = Integer.MAX_VALUE;


        public void pop() {
            if(listNode!=null)
                listNode =listNode.next;
        }

        public void push(int x) {
            if(null==listNode){
                listNode = new ListNode(x,x);
            }else{
                ListNode n = new ListNode(x, Math.min(x,listNode.min));
                n.next=listNode; //在表头插入一个节点
                listNode=n;
            }
        }


        public int top() {
            if(listNode!=null)
                return listNode.val;
            return -1;
        }

        public int getMin() {
            if (listNode != null)
                return listNode.min;
            return -1;

        }

        class ListNode {
            int val;
            int min;
            ListNode next;

            ListNode(int val, int min) {
                this.val = val;
                this.min = min;
            }

        }
    }


}

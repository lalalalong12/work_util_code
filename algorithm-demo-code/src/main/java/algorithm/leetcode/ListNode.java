package algorithm.leetcode;

/**
 * @author yangWenlong
 * @date 2021/2/9- ${time}
 */
public class ListNode {

        int val;

        public int getVal() {
                return val;
        }

        public void setVal(int val) {
                this.val = val;
        }

        public ListNode getNext() {
                return next;
        }

        public void setNext(ListNode next) {
                this.next = next;
        }

        ListNode next;   // 下一个链表对象
        ListNode(int x) { val = x; }  //赋值链表的值

}

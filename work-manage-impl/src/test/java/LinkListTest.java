/**
 * @author yangWenlong
 * @date 2021/4/15- ${time}
 */
public class LinkListTest {


    static class ListNode {

        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }

        // 链表节点的构造函数
        // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
        public ListNode(int[] arr){

            if(arr == null || arr.length == 0)
                throw new IllegalArgumentException("arr can not be empty");

            this.val = arr[0];
            ListNode cur = this;
            for(int i = 1 ; i < arr.length ; i ++){
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        // 以当前节点为头结点的链表信息字符串
        @Override
        public String toString(){

            StringBuilder s = new StringBuilder();
            ListNode cur = this;
            while(cur != null){
                s.append(cur.val + "->");
                cur = cur.next;
            }
            s.append("NULL");
            return s.toString();
        }
    }

    public static ListNode removeElements(ListNode head, int val) {

        if(head == null)
            return head;

        ListNode res = removeElements(head.next, val);
        if(head.val == val)
            return res;
        else{
            head.next = res;
            return head;
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = removeElements(head, 6);
        System.out.println(res);
    }
}

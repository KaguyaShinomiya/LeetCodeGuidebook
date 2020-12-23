package 链表与双指针.其他链表;

import utils.ListNode;

/**
 * 328. 奇偶链表
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OddEvenListTest {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return null;
        ListNode rear = head.next;
        ListNode odd = head;
        ListNode even = head.next;
        int count = 0;
        int move = 0;

        while (rear.next != null) {
            rear = rear.next;
            count++;
        }
        while (move <= count / 2) {
            odd.next = even.next;
            rear.next = even;
            even.next = null;
            rear = rear.next;
            odd = odd.next;
            even = odd.next;
            move++;
            if (even == null) break;
        }
        return head;
    }
}

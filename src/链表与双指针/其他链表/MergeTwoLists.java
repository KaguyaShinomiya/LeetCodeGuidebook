package 链表与双指针.其他链表;

import utils.ListNode;

/**
 * The Class: MergeTwoLists
 * 剑指 Offer 25. 合并两个排序的链表
 *
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-10 01:28
 */
public class MergeTwoLists {
    /**
     * 类似于归并排序，用两个指针分别指向两个链表，逐个进行比较，每次将较小的加入到当前合并链表的尾部
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 利用哑结点，用于指向合并链表的头结点
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        // 依次比较两个链表中较小的节点，作为当前节点的后继
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = curr.next;
                l2 = l2.next;
            }
        }
        // 如果还有链表有剩余，直接将该链表的头结点插入到合并链表的后面即可
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummy.next;
    }
}

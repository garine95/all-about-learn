package leetcode.list;

import leetcode.common.IOUtil;

import java.util.List;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class DeleteRepeatNode {

    public static void main(String[] args) {
        List<ListNode> listNodes = IOUtil.transferToListNodes(IOUtil.readOneLine());
        Long begin = System.currentTimeMillis();
        ListNode merageed = deleteDuplicates(listNodes.get(0));
        IOUtil.printWithUsetime(merageed.toString(), begin);
    }
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur!=null && cur.next != null){
            if (cur.next.val == cur.val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }

        }
        return head;
    }
}

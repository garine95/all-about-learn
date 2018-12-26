package leetcode.list;

import leetcode.common.IOUtil;

import java.util.List;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author zhoujy
 * @date 2018年12月26日
 **/
public class ReverseList {
    public static void main(String[] args) {
        List<ListNode> listNodes = IOUtil.transferToListNodes(IOUtil.readOneLine());
        Long begin = System.currentTimeMillis();
        ListNode deteted = reverseList(listNodes.get(0));
        IOUtil.printWithUsetime(deteted.toString(), begin);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode pt = null;
        ListNode sa = head;
        ListNode temp = null;
        while (sa != null){
            temp = sa.next;
            sa.next=pt;
            pt=sa;
            sa=temp;
        }
        return pt;
    }
}

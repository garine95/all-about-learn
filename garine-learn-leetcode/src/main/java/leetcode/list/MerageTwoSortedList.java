package leetcode.list;

import leetcode.common.IOUtil;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
class MerageTwoSortedList {
    public static void main(String[] args) {
        List<ListNode> listNodes = IOUtil.transferToListNodes(IOUtil.readOneLine());
        Long begin = System.currentTimeMillis();
        ListNode merageed = mergeTwoLists(listNodes.get(0), listNodes.get(1));
        IOUtil.printWithUsetime(merageed.toString(), begin);
    }

    /**
     * 个人实现
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null){
            return l2;
        }
        if (l2 ==null){
            return l1;
        }
        ListNode cl1 = l1;
        ListNode cl2 = l2;
        ListNode l3 = null;
        ListNode cl3 =null;
        if(cl1.val <= cl2.val){
            l3=cl1;
            cl3 = cl1;
            cl1=cl1.next;
        }else {
            l3=cl2;
            cl3=cl2;
            cl2=cl2.next;
        }
        while(cl1!=null || cl2!=null){
            if (cl1 ==null){
                cl3.next=cl2;
                cl2=cl2.next;
                cl3=cl3.next;
                continue;
            }
            if (cl2 == null){
                cl3.next=cl1;
                cl1=cl1.next;
                cl3=cl3.next;
                continue;
            }
            //比较当前两个节点
            if(cl1.val <= cl2.val){
                cl3.next=cl1;
                cl1=cl1.next;
                cl3=cl3.next;
            }else {
                cl3.next=cl2;
                cl2=cl2.next;
                cl3=cl3.next;
            }
        }
        return l3;
    }

    /**
     * leetcode上的实现
     * */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // 类似归并排序中的合并过程
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表,减少不必要循环次数，提升速度
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }
}
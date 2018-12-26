package leetcode.list;

import leetcode.common.IOUtil;

import java.util.List;

/**
 *编写一个程序，找到两个单链表相交的起始节点。
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 4->1,5->0->1->4->5
 * @author zhoujy
 * @date 2018年12月25日
 **/
public class FindInternalNode {

    public static void main(String[] args) {
        List<ListNode> listNodes = IOUtil.transferToListNodes(IOUtil.readOneLine());
        ListNode inter = new ListNode(8);
        //设置交叉，这里长度至少3
        listNodes.get(0).next.next=inter;
        ListNode ts = listNodes.get(1).next.next.next;listNodes.get(1).next.next.next=inter;inter.next=ts;
        //输出交叉
        System.out.println(listNodes.get(0));
        System.out.println(listNodes.get(1));

        Long begin = System.currentTimeMillis();
        ListNode has = getIntersectionNode(listNodes.get(0), listNodes.get(1));
        IOUtil.printWithUsetime(String.valueOf(has), begin);
    }

    /**
     * 采用交替循环，抹平相交之前的长度差
     * 考虑不相交的情况，最终是null ==null
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2){//终止条件
            l1 = l1==null ? headB : l1.next;//l1 = l1.next==null ? headB : l1.next;会导致无法结束无环情况
            l2 = l2==null ? headA : l2.next;
        }
        return l1;
    }
}

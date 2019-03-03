package leetcode.list;

import leetcode.common.IOUtil;

import java.util.List;

/**
 *
 *删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @author zhoujy
 * @date 2018年12月26日
 **/
public class DeleteSpeciNode {

    public static void main(String[] args) {
        List<ListNode> listNodes = IOUtil.transferToListNodes(IOUtil.readOneLine());
        Long begin = System.currentTimeMillis();
        ListNode deteted = removeElements2(listNodes.get(0), 6);
        IOUtil.printWithUsetime(deteted.toString(), begin);
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        ListNode headNode = head;
        ListNode curNode = head;
        ListNode lastNode = null;
        while (curNode !=null){
            if (curNode.val==val){
                if (headNode == curNode){
                    headNode = curNode.next;
                    curNode = curNode.next;
                }else {
                    lastNode.next=curNode.next;
                    curNode=curNode.next;
                }
            }else {
                lastNode = curNode;
                curNode=curNode.next;
            }
        }
        return headNode;
    }


    /**
     * leetcode实现，节省一个last指针
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = header;
        while(cur.next != null){
            if(cur.next.val == val ){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return header.next;
    }
}

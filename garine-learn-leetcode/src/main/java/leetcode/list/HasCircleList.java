package leetcode.list;

import leetcode.common.IOUtil;

import java.util.List;

/**
 *为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * @author zhoujy
 * @date 2018年12月25日
 **/
public class HasCircleList {

    public static void main(String[] args) {
        List<ListNode> listNodes = IOUtil.transferToListNodes(IOUtil.readOneLine());
        //listNodes.get(listNodes.size() - 1).next=listNodes.get(0);//设置环
        Long begin = System.currentTimeMillis();
        boolean has = hasCycle(listNodes.get(0));
        IOUtil.printWithUsetime(String.valueOf(has), begin);
    }

    /**
     * 使用快慢指针方式，有环最终就会相遇
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (head!= null &&fast !=null && fast.next != null){
            slow= slow.next;//1步
            fast= fast.next.next;//2步
            if (slow == fast){
                return true;
            }
        }
        return false;
    }
}

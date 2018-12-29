package leetcode.list;

import leetcode.common.IOUtil;

import java.util.List;
import java.util.Stack;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 *
 * @author zhoujy
 * @date 2018年12月29日
 **/
public class IsCircleList {
    public static void main(String[] args) {
        List<ListNode> listNodes = IOUtil.transferToListNodes(IOUtil.readOneLine());
        IOUtil.print(isPalindrome(listNodes.get(0)));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }
        Stack<ListNode> hstack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        int jo=-1;
        while (true){
            hstack.push(slow);
            if (fast.next==null){
                jo=1;
                break;
            }
            if (fast.next.next==null){
                jo=0;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode hd = null;
        if (jo == 1){
            //奇数个
            hd= slow;
        }
        if (jo == 0){
            //偶数个
             hd = slow.next;
        }


        while (hd !=null && !hstack.empty()){
            if (hd.val == hstack.pop().val){
                hd=hd.next;
            }else {
                return false;
            }
            if (hd == null && hstack.empty()){
                return true;
            }
        }
        return false;
    }
}

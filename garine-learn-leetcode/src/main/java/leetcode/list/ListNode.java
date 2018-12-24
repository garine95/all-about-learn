package leetcode.list;

public class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int x){
        val=x;
    }

    @Override
    public String toString(){
        StringBuilder sb =new StringBuilder();
        ListNode cur = this;
        while (true){
            sb.append(cur.val);
            if (cur.next != null){
                sb.append("->");
                cur = cur.next;
            }else {
                return sb.toString();
            }
        }
    }
}
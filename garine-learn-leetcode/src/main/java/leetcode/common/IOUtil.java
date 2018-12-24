package leetcode.common;

import leetcode.list.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOUtil {

    public static String readOneLine(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static List<ListNode> transferToListNodes(String str){
        List<ListNode> listNodes = new ArrayList<>();
        String[] nss = str.trim().split(",");
        for (String s : nss) {
            String[] ns = s.trim().split("->");
            ListNode curNode = null;
            for (String n : ns) {
                if (curNode == null){
                    curNode = new ListNode(Integer.parseInt(n));
                    listNodes.add(curNode);
                }else {
                    curNode.next = new ListNode(Integer.parseInt(n));
                    curNode = curNode.next;
                }
            }
        }
        return listNodes;

    }

    public static void printWithUsetime(String str, long begin){
        long cur = System.currentTimeMillis();
        long used =  cur - begin;
        System.out.println("耗时：" + (used) +"输出："+ str);
    }
}

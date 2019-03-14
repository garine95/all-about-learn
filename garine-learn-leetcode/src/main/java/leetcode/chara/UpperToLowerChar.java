package leetcode.chara;

public class UpperToLowerChar {

    public static void main(String[] args) {
        System.out.println(transfer('g'));
    }

    public static char transfer(char c){
        int diff = 'a' - 'A';
        if (c >='A' && c<='Z'){
            return c +=diff;
        }else {
            return c-=diff;
        }

    }
}

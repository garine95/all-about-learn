package leetcode.search;

public class HalfSearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,6,7};
        System.out.println(halfSearch(arr, 0, arr.length-1, 9));
        System.out.println(halfSearch2(arr, 9));
    }

    public static int halfSearch(int[] arr, int lop, int hip, int searchVal){
        if (lop > hip){
            return -1;
        }
        int midP = (lop + hip)/ 2;
        int midVal = arr[midP];
        if (midVal == searchVal){
            return midP;
        }
        if (midVal > searchVal){
            return halfSearch(arr, lop, midP - 1, searchVal);
        }else {
            return halfSearch(arr, midP + 1, hip, searchVal);
        }
    }

    public static int halfSearch2(int[] arr, int val){
        int lo = 0;
        int hi = arr.length -1;
        while (lo <= hi){
            int midP = (lo+hi)/2;
            if (arr[midP] == val){
                return midP;
            }
            if (arr[midP] > val) {
                hi = midP -1;
            }else {
                lo = midP + 1;
            }
        }
        return -1;
    }
}

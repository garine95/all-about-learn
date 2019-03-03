package leetcode.sort;

import leetcode.common.IOUtil;

public class InsertSort {

    public static void main(String[] args) {
        int[] array = new int[]{5,7,2,7,8,13,56,6,22,0,-1};
        IOUtil.printArray(insertSort(array));
    }

    public static  int[] insertSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            //轮数
            for (int j = i+1; j < array.length && j >= 1 ; j--) {
                int temp = array[j];
                if (array[j] < array[j - 1]){
                    array[j]=array[j-1];
                    array[j-1]=temp;
                }else {
                    break;
                }
            }
        }
        return array;
    }
}

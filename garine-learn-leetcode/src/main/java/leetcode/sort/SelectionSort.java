package leetcode.sort;

import leetcode.common.IOUtil;

public class SelectionSort {
    public static void main(String[] args) {
        int[] array = new int[]{5,7,999,7,8,13,56,6,22,0,-1};
        IOUtil.printArray(selectionSort(array));
    }
    public static int[] selectionSort(int[] array){
        for (int i = 1; i <= array.length; i++) {
            //轮数
            int minPoint = i - 1;
            int minValue = array[i -1];
            for (int j = i - 1; j <  array.length; j++) {
                if (array[j] < minValue){
                    minPoint = j;
                    minValue = array[j];
                }
            }
            int temp = array[i - 1];
            array[i-1]=minValue;
            array[minPoint]=temp;
        }
        return array;
    }
}

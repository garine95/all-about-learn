package leetcode.sort;

import leetcode.common.IOUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 冒泡排序，相邻比较，大的冒上区
 * 小到大
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = new int[]{5,7,2,7,8,13,56,6,22,0,-1};
        IOUtil.printArray(bulleSort(array));
    }

    public static int[] bulleSort(int[] array){
        for (int i = 1; i <= array.length ; i++) {
            //轮数
            for (int j = 0; j < array.length - i; j++) {
                int temp =array[j];
                if (array[j] > array[j+1]){
                    array[j] = array[j+1];
                    array[j+1] =temp;
                }
            }
        }
        return array;
    }
}

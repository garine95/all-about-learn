package leetcode.sort;

import leetcode.common.IOUtil;

/**
 * 快速排序，基于递归实现
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[]{5,7,2,7,8,13,56,6,22,0,-1,323};
        quickSort(array, 0, array.length - 1);
        IOUtil.printArray(array);
    }

    public static void quickSort(int[] arr, int lop, int hip) {
        if (lop > hip) {
            return;
        }
        int basePoint = lop;
        int left = lop;
        int right = hip;
        while (lop < hip) {
            while (lop < right&& arr[basePoint] >= arr[lop]) {
                lop++;
            }
            while ( hip > left && arr[basePoint] <= arr[hip]) {
                hip--;
            }
            if (lop < hip){
                int temp = arr[lop];
                arr[lop] = arr[hip];
                arr[hip] = temp;
            }
        }
        int temp=arr[hip];
        arr[hip] = arr[basePoint];
        arr[basePoint] = temp;
        quickSort(arr,left, hip -1);
        quickSort(arr,hip + 1, right);
    }
}

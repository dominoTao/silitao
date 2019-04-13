package com.liangtao.core.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {49,38,65,97,23,22,76,0,1,5,8,2,-1,22};
        System.out.println(Arrays.toString(arr));
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    private static void quickSort(int[] arr,int low,int high) {
        if(low < high) {
            int index = getIndex(arr,low,high);
            quickSort(arr,low,index-1);
            quickSort(arr,index+1,high);
        }
    }
    private static int getIndex(int[] arr,int low,int high) {
        int temp = arr[low];
        while(low < high) {
            while(low < high && arr[high] >= temp) {
                high--;
            }
            arr[low] = arr[high];
            while(low < high && arr[low] <= temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }
}

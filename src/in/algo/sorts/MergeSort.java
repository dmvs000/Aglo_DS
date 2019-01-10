package in.algo.sorts;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        int l = 0;
        int r = arr.length-1;
        System.out.println(Arrays.toString(mergeSortAux(arr,l,r)));
    }

    public static int[] mergeSortAux(int[] arr, int l, int r) {
        if(l == r) {
            int[] mergedArr = new int[1];
            mergedArr[0] = arr[l];
            return mergedArr;
        }
        int m = l + ((r-l)/2);
        int[] leftArr = mergeSortAux(arr, l, m);
        int[] rightArr = mergeSortAux(arr, m+1, r);
        int leftArrLen = leftArr.length;
        int rightArrLen = rightArr.length;
        int[] mergedArr = new int[leftArrLen + rightArrLen];
        int leftPointer = 0;
        int rightPointer = 0;
        int mergePointer = 0;
        while(leftPointer < leftArrLen && rightPointer < rightArrLen) {
            if(leftArr[leftPointer]<rightArr[rightPointer]) {
                mergedArr[mergePointer++] = leftArr[leftPointer++];
            }
            else {
                mergedArr[mergePointer++] = rightArr[rightPointer++];
            }
        }
        while(leftPointer < leftArrLen) {
            mergedArr[mergePointer++] = leftArr[leftPointer++];
        }
        while(rightPointer < rightArrLen) {
            mergedArr[mergePointer++] = rightArr[rightPointer++];
        }
        return mergedArr;
    }

}

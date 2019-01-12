package in.algo.search;

import java.util.Arrays;

/*
Min Heap. -> Sorts in ascending order.
Add all the elements one by one into the heap.
Remove all the elements one by one from the heap.
Will work with all numbers in Java Integer Range.
*/
public class HeapSort {

    private class Heap {
        int[] heapArr;
        int lastElementIndex;
        int n;
        Heap(int n) {
            heapArr = new int[n];
            this.n = n;
            lastElementIndex = -1;
            Arrays.fill(heapArr,Integer.MAX_VALUE);
        }
        public void printHeapArr(){
            System.out.println(Arrays.toString(heapArr) + " : " + lastElementIndex);
        }
        public void addToHeap(int val) {
            int indexToAdd = lastElementIndex + 1;
            heapArr[indexToAdd] = val;
            lastElementIndex = indexToAdd;
            int child = lastElementIndex;
            while(child > 0) {
                int parent = (child-1)/2;
                if(heapArr[child]>=heapArr[parent]) break;
                int temp = heapArr[child];
                heapArr[child] = heapArr[parent];
                heapArr[parent] = temp;
                child = parent;
            }
        }

        public int getRoot() {
            return heapArr[0];
        }

        public int removeRoot() {
            int retVal = heapArr[0];
            heapArr[0] = heapArr[lastElementIndex];
            heapArr[lastElementIndex] = Integer.MAX_VALUE;
            lastElementIndex--;
            int curPointer = 0;
            while(true) {
                int leftChildIndex = (2*curPointer)+1;
                int rightChildIndex = (2*curPointer)+2;
                int indexToSwap = leftChildIndex;
                int smallest = heapArr[curPointer];
                if(leftChildIndex < n && heapArr[leftChildIndex] < smallest) {
                    smallest = heapArr[leftChildIndex];
                    indexToSwap = leftChildIndex;
                }
                if(rightChildIndex < n && heapArr[rightChildIndex] < smallest) {
                    smallest = heapArr[rightChildIndex];
                    indexToSwap = rightChildIndex;
                }
                if(smallest != heapArr[curPointer]) {
                    int temp = heapArr[indexToSwap];
                    heapArr[indexToSwap] = heapArr[curPointer];
                    heapArr[curPointer] = temp;
                    curPointer = indexToSwap;
                }
                else
                    break;
            }
            return retVal;
        }

    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        Heap h = new HeapSort().new Heap(arr.length);
        for(int i=0;i<arr.length;i++) h.addToHeap(arr[i]);
        h.printHeapArr();
        for(int i=0;i<arr.length;i++) arr[i] = h.removeRoot();
        System.out.println(Arrays.toString(arr));
    }

}

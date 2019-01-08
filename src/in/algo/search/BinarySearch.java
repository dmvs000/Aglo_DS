package in.algo.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {2,5,8,12,16,23,38,56,72,91};
        binarySearch(arr,5);
    }

    public static void binarySearch(int[] arr, int x) {
        int l = 0;
        int h = arr.length;
        boolean found = false;
        while(l<=h) {
            int m = l+((h-l)/2); //keeping the overflow in mind
            if(arr[m] == x) {
                found = true;
                System.out.println("Element found at : "+m+" index");
                break;
            }
            if(arr[m] > x) {
                h = m-1;
            }
            else {
                l = m+1;
            }
        }
        if(!found) {
            System.out.println("Element not found. Could be inserted at : "+l+" index");
        }
    }

}

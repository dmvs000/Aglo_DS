package in.algo.trees;

import java.util.Arrays;

public class MinHeap {

	static class minHeap {
		// Stores the Heap in a Array.
		int[] heap;

		// Maintain the size of Heap.
		int size = 0;

		// Next Index to store an element. At start, it would be zero.
		int nextIndex = 0;

		//Initialize the Heap Array with N elements
		minHeap(int N) {
			heap = new int[N];
		}

		// Returns the Minimum Element
		int getMin() {
			return heap[0];
		}

		//Add a new Element.
		void add(int val) {
			//Add to nextIndex. Increment size
			heap[nextIndex] = val;
			size++;
			int curIndex = nextIndex;
			nextIndex++;
			//Heapify all the way up.
			while(curIndex!=0&&(heap[curIndex]<heap[parentIndex(curIndex)]))
			{
				heapify(parentIndex(curIndex));
				curIndex = parentIndex(curIndex);
			}
			System.out.println(Arrays.toString(heap));
		}
		
		//Replace the heap[index] with this new Value
		void decreaseKey(int index, int val)
		{
			heap[index] = val;
			//Heapify all the way up starting with parent of curIndex. If required the above ones too.
			while(index!=0&&heap[parentIndex(index)]>heap[index])
			{
				heapify(parentIndex(index));
				index = parentIndex(index);
			}
		}
		
		//Delete the Key at this particular position
		void deleteKey(int index)
		{
			//Replace the key wiht Min VALUE, which pushes it to top of the Heap. Extract Min to get that out.
			decreaseKey(index,Integer.MIN_VALUE);
			extractMin();
		}
		
		//Removes the first element in Heap.
		void extractMin()
		{
			//Get the first one out.
			int lastIndex = nextIndex-1;
			nextIndex--;
			size--;
			swap(lastIndex,0);
			//heapify the tree with root starting at zero.
			heapify(0);
			System.out.println(Arrays.toString(heap));
		}

		// Heapify the subTree with its root as Index
		void heapify(int index) {
			//Get the left and right Child Indexes
			int left_index = (2 * index) + 1;
			int right_index = (2 * index) + 2;
			int smallest_index = index;
			//Check if the left child has smaller elements that curIndex
			if (left_index < size && heap[left_index] < heap[smallest_index]) {
				smallest_index = left_index;
			}
			//Check if the right child has smaller elements than min uptill now. i.e., curIndex and left child
			if (right_index < size && heap[right_index] < heap[smallest_index]) {
				smallest_index = right_index;
			}
			//If the index itself is the smallest element. No changes and no heapify required. 
			//Else swap the left/right child with current element and heapify subTrees.
			if (smallest_index != index) {
				swap(smallest_index, index);
				heapify(smallest_index);
			}
		}

		// Swap the index elements in Heap Array
		void swap(int index1, int index2) {
			int temp = heap[index1];
			heap[index1] = heap[index2];
			heap[index2] = temp;
		}
		
		int leftChildIndex(int i)
		{
			return (2*i)+1;
		}
		
		int rightChildIndex(int i)
		{
			return (2*i)+2;
		}
		
		int parentIndex(int i)
		{
			return (i-1)/2;
		}

	}

	public static void main(String[] args) {
		
		int[] arr = {11,3,2,1,15,5,4,45};
		minHeap mh = new minHeap(arr.length);
		for(int i=0;i<arr.length;i++)
			mh.add(arr[i]);
		System.out.println();
		mh.extractMin();
		mh.deleteKey(4);
		mh.deleteKey(5);
	}

}

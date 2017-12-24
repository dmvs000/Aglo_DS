package in.algo.trees;

import java.util.Arrays;

public class SegmentTree {

	static class SegTree
	{
		int[] segTree;
		int[] input;
		SegTree(int [] arr)
		{
			this.input = arr;
			int maxLeafNodes = (int) Math.pow(Math.ceil((Math.log(input.length)/Math.log(2))),2);
			segTree = new int[(2*maxLeafNodes)-1];
		}
		
		//Segment Tree for Range Sum.
		void constructSegTree()
		{
			constructSegTreeAux(0,input.length-1,0);
			System.out.println(Arrays.toString(segTree));
		}
		
		//Split the inputArray in half each time and construct the tree recrusively for left Array and right Array.
		void constructSegTreeAux(int l, int h, int curSegIndex)
		{
			//If we reach a position where only one element is left. Sum is the value of that element.
			if(l==h)
			{	
				segTree[curSegIndex] = input[l];
				return;
			}
			//Calculate the mid
			int m = l+((h-l)/2);
			constructSegTreeAux(l,m,2*curSegIndex+1);	//Calling the construct on left sub array
			constructSegTreeAux(m+1,h,2*curSegIndex+2);	//Calling the construct on right sub array
			//The total sum for this range is leftRangeSum + rightRangeSum.
			segTree[curSegIndex] = segTree[2*curSegIndex+1]+segTree[2*curSegIndex+2];
		}
		
		void getRangeSum(int l, int h)
		{
			System.out.println(getRangeSumAux(l,h,0,input.length-1,0));
		}
		
		//Given L, Given H, curL, CurH and segIndex
		int getRangeSumAux(int l, int h, int curL, int curH, int segIndex)
		{
			//If the traversing range lies completely outside the requested range. Return 0.
			if((curH<l)||(curL>h))
				return 0;
			//If teh traversing range lies completely wihtin the requested range. Include this in result. Return the value;
			if(curL>=l&&curH<=h)
				return segTree[segIndex];
			//If the traversing range is not completely out nor in. Split the array further
			//and take the sum of their individuals results.
			int curM = curL + ((curH-curL)/2);
			int leftRange = getRangeSumAux(l,h,curL,curM,2*segIndex+1);		//Recursively call for left Array.
			int rightRange = getRangeSumAux(l,h,curM+1,curH,2*segIndex+2);	//Recursively call for right Array
			//Add the leftRange and rightRangs.
			//they return the value only if the traversing range is inside the requested range.
			//So, Even if this is not completely in. We'll get the value for only completely in range.
			return leftRange + rightRange;
		}
		
		//Update a range. Add the val to the elements form range l to r.
		void update(int l, int r, int val)
		{
			updateAux(l,r,0,input.length-1,0,val);
			System.out.println(Arrays.toString(segTree));
		}
		
		void updateAux(int l, int h, int curL, int curH, int segIndex, int val)
		{
			//System.out.println(curL+" "+curH+" "+segIndex);
			//Check if out of bounds.
			if(curL<0||curL>input.length-1||curH<0||curH>input.length-1)
				return;
			//Check if outside the range.
			if((curH<l)||(curL>h))
				return;
			//If only one element is present and inside the range. Add the val to the segTree.
			if(curL>=l&&curH<=h&&(curL==curH))
			{
				segTree[segIndex]+=val;
				return;
			}
			int curM = curL + ((curH-curL)/2);
			updateAux(l,h,curL,curM,2*segIndex+1,val);		//Recursively call for Left
			updateAux(l,h,curM+1,curH,2*segIndex+2,val);	//Recursively call for Right.
			//ReCalculate the SegTree. If the values are changed. New ones will be reflected.
			segTree[segIndex]=segTree[2*segIndex+1]+segTree[2*segIndex+2];
		}
	}
	
	public static void main(String[] args) {
		
		int[] arr = {1,3,5,7,9,11};
		SegTree sgt = new SegTree(arr);
		sgt.constructSegTree();
		sgt.getRangeSum(1, 3);
		sgt.update(0, 2, 5);
	}

}

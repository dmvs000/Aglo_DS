package in.algo.stack;

import java.util.Stack;

public class MaxRectangleInHistogram {
	
	public static int MaxRectHistogram(int[] arr)
	{
		int n = arr.length;
		//Create two stacks. 
		Stack<Integer> pos = new Stack<Integer>();		//For storing from where a particular heighted rectangle could start
		Stack<Integer> height = new Stack<Integer>();	//For storing the corresponding height that we encountered on our way from left to right.
		//Neglect all zero heights in the beginning. 
		//And check if the histogram has rectangle of zero. No Bars.
		int cur_pos = 0;
		while(cur_pos<n&&arr[cur_pos]==0)
			cur_pos++;
		if(cur_pos>=n)
			return 0;
		//MaxHeight will store the Maximum Height of Histogram at every point
		int maxHeight = 0;
		height.push(arr[cur_pos]);
		pos.push(cur_pos);
		for(int i=cur_pos+1;i<n;i++)
		{
			//If the heights stack is empty or cur element is greater than the stored height. Blindly push the elements.
			if(height.isEmpty()||(arr[i]>height.peek()))
			{
				height.push(arr[i]);
				pos.push(i);
			}
			//If zero empty the contents of stack and also area of rectangles.
			else if(arr[cur_pos]==0)
			{
				while(!height.isEmpty())
				{
					int t_h = height.pop();
					int t_p = pos.pop();
					if((t_h*(i-t_p))>maxHeight)
						maxHeight = (t_h)*(i-t_p);
				}
			}
			else
			{
				//Pushing Dummy Element Just once.
				pos.push(0);
				//This while should execute atleast once. So, dummy element will be gone.
				while(!height.isEmpty()&&arr[i]<height.peek())
				{
					pos.pop();
					int t_h = height.pop();
					int t_p = pos.peek();
					if(t_h*(i-t_p)>maxHeight)
						maxHeight = (t_h*(i-t_p));
				}
				height.push(arr[i]);
			}
			System.out.print("Height : ");printStack(height);
			System.out.print("Pos    : ");printStack(pos);
			System.out.println("Max H: "+maxHeight);
		}
		//After all this. Empty of contents of Stack if any and find MaxHeight.
		while(!height.isEmpty())		
		{
			int t_h = height.pop();
			int t_p = pos.pop();
			if(t_h*(n-t_p)>maxHeight)
				maxHeight = (t_h*(n-t_p));
		}
		return maxHeight;
	}
	
	public static void printStack(Stack<Integer> stack)
	{
		for(int i=0;i<stack.size();i++)
			System.out.print(stack.get(i)+" ");
		System.out.println();
		
	}
	
	public static void main(String[] args)
	{
		//arr[i] contains the height of each Bar in histogram.
		int[] arr = {1,2,1,3,2,0,1};
		System.out.println(MaxRectHistogram(arr));
	}
	
}

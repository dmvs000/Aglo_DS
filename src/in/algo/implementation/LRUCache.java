package in.algo.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class LRUCache {

	class cache {

		/*
		 * Type of cacheNode. Contains KEY and VALUE and the pointers to the
		 * next and prev locations
		 */
		class cacheNode {
			int key;
			String value;

			cacheNode(int key, String val) {
				this.key = key;
				this.value = val;
			}

			cacheNode next;
			cacheNode prev;
		}

		private int capacity = 0;
		// Initially the size is zero;
		private int curSize = 0;

		// HashMap that contains the Integer(Key) and the reference to the
		// particular cacheNode
		HashMap<Integer, cacheNode> hashMap = new HashMap<Integer, cacheNode>();

		// Initialize with a user's choice capacity
		cache(int cap) {
			this.capacity = cap;
			head = new cacheNode(0, ""); // Dummy Node for more clear logic
			last = head; // Initially last = head
		}

		private cacheNode head;
		private cacheNode last;

		private int DLL_removeLast() {
			// if head is last. then nothing is there in cache
			if (head == last) {
				System.out.println("The Cache is currently empty. Removal not possible");
				return -1;
			}
			// update the last variable so that it points to the new one.
			// return the last node key back
			int retKey = last.key;
			last = last.prev;
			last.next.prev = null;
			last.next = null;
			return retKey;
		}

		private void DLL_replace(cacheNode temp) {
			// If curSize is One. Nothing to do. Return back
			if (curSize == 1) {
				return;
			}
			cacheNode tempNode = temp;
			// If this is the last Node. Update the last variable, else normal
			// deletion in middle
			if (tempNode.next == null) {
				last = last.prev;
				last.next.prev = null;
				last.next = null;
			} else {
				tempNode.prev.next = tempNode.next;
				tempNode.next.prev = tempNode.prev;
			}
			// Insert the deleted node at front.
			DLL_insertFirst(tempNode);
		}

		private void DLL_insertFirst(cacheNode temp) {
			// If the curSize == 1, this is the first Node that is getting
			// added. Hence update the last variable and take care of null
			// pointers, Else normal addition at First
			if (curSize == 1) {
				head.next = temp;
				temp.prev = head;
				last = temp;
				return;
			}
			temp.next = head.next;
			head.next.prev = temp;
			head.next = temp;
			temp.prev = head;
		}

		private void DLL_print() {
			cacheNode temp = head.next;
			System.out.print("DLL : ");
			while (temp != null) {
				System.out.print("[" + temp.key + " " + temp.value + "] ");
				temp = temp.next;
			}
			System.out.println();
		}

		private void HM_print() {
			Set<Integer> keySet = hashMap.keySet();
			Iterator<Integer> keys = keySet.iterator();
			System.out.print("HashMap : ");
			while (keys.hasNext()) {
				int curKey = keys.next();
				System.out.print(curKey + " ");
			}
			System.out.println();
		}

		public boolean isPresent(int key) {
			if (hashMap.containsKey(key))
				return true;
			return false;
		}

		public String get(int key) {
			// If the requested key is present in the HashMap, then it's present
			// in cache, else no
			if (hashMap.containsKey(key)) {
				cacheNode tempNode = hashMap.get(key);
				DLL_replace(tempNode);
				HM_print();
				DLL_print();
				return tempNode.value;
			} else {
				System.out.println("The requested key : " + key + " is not present in the cache");
				return null;
			}
		}

		public void put(int key, String val) {
			cacheNode newNode = new cacheNode(key, val);
			// Check if the cache is already at it's full capacity
			// If yes, removeLast Element from DLL and HashMap and then insert
			// the new value, else directly insert at first as it has space to
			// accomodate
			if (curSize == capacity) {
				int retKey = DLL_removeLast();
				hashMap.remove(retKey);
			} else {
				curSize++;
			}
			DLL_insertFirst(newNode);
			hashMap.put(key, newNode);
			HM_print();
			DLL_print();
		}
	}

	/*
	 * Driver Function
	 * 1 -> Get the Value of a particular KEY
	 * 2 -> Insert new key and value to the cache
	 */
	public static void main(String[] args) {
		LRUCache lru = new LRUCache();
		cache c = lru.new cache(3);
		FastReader fr = new FastReader();
		while (true) {
			// System.out.println(c.capacity + " " + c.curSize);
			int k = fr.nextInt();
			if (k == 1) {
				int key = fr.nextInt();
				c.get(key);
			} else if (k == 2) {
				int key = fr.nextInt();
				String val = fr.nextLine();
				c.put(key, val);
			} else
				break;
		}
	}

	// Better IO Class
	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public Double nextDouble() {
			return Double.parseDouble(next());
		}

		public Float nextFloat() {
			return Float.parseFloat(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				System.out.println(e);
			}
			return str;
		}
	}
}

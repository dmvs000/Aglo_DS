package in.algo.trie;

/*
 * https://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
 */
public class Boggle {

	class Trie {
		class TrieNode {
			char thisChar;
			TrieNode[] trieNodeArr = new TrieNode[26];
			boolean isEnd = false;

			TrieNode(char c) {
				this.thisChar = c;
			}
		}

		TrieNode root = new TrieNode(' ');

		void addStringToTrie(String str) {
			TrieNode temp = root;
			for (int i = 0; i < str.length(); i++) {
				char curChar = str.charAt(i);
				if (temp.trieNodeArr[curChar - 97] == null) {
					temp.trieNodeArr[curChar - 97] = new TrieNode(curChar);
				}
				temp = temp.trieNodeArr[curChar - 97];
			}
			temp.isEnd = true;
			// System.out.println(temp.thisChar + " ");
		}

		void printTrie() {
			TrieNode temp = root;
			if (null == root) {
				System.out.println("Trie is Empty !");
				return;
			}
			for (int i = 0; i < 26; i++) {
				if (temp.trieNodeArr[i] != null) {
					printTrie_Aux(temp.trieNodeArr[i], "");
				}
			}
		}

		void printTrie_Aux(TrieNode trieNode, String s) {
			if (trieNode.isEnd)
				System.out.println(s + trieNode.thisChar);
			for (int i = 0; i < 26; i++) {
				if (trieNode.trieNodeArr[i] != null) {
					printTrie_Aux(trieNode.trieNodeArr[i], s + trieNode.thisChar);
				}
			}
		}

		void findWordsInBoggle(char[][] charArr) {
			TrieNode temp = root;
			if (root == null) {
				System.out.println("Trie is Empty");
				return;
			}
			for (int i = 0; i < 26; i++) {
				if (temp.trieNodeArr[i] != null)
					searchAndFindInitialIndex(charArr, temp.trieNodeArr[i]);
			}
		}

		private int m;
		private int n;
		private boolean[][] traversed;

		void searchAndFindInitialIndex(char[][] charArr, TrieNode temp) {
			m = charArr.length;
			n = charArr[0].length;
			traversed = new boolean[m][n];
			char curChar = temp.thisChar;
			// System.out.println("curChar : " + curChar);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					// System.out.println(i + " " + j + " " + charArr[i][j] + "
					// " + curChar);
					if (charArr[i][j] == curChar) {
						searchAndFindAux(charArr, i, j, temp, "");
					}
				}
			}
		}

		void searchAndFindAux(char[][] charArr, int i, int j, TrieNode temp, String str) {
			if (i >= m || j >= n || i < 0 || j < 0)
				return;
			if (temp == null)
				return;
			if (traversed[i][j])
				return;
			if (temp.thisChar != charArr[i][j])
				return;
			traversed[i][j] = true;
			String curStr = str + temp.thisChar;
			if (temp.isEnd) {
				System.out.println(curStr);
			}
			for (int k = 0; k < 26; k++) {
				searchAndFindAux(charArr, i - 1, j - 1, temp.trieNodeArr[k], curStr);
				searchAndFindAux(charArr, i - 1, j, temp.trieNodeArr[k], curStr);
				searchAndFindAux(charArr, i - 1, j + 1, temp.trieNodeArr[k], curStr);
				searchAndFindAux(charArr, i, j - 1, temp.trieNodeArr[k], curStr);
				searchAndFindAux(charArr, i, j + 1, temp.trieNodeArr[k], curStr);
				searchAndFindAux(charArr, i + 1, j - 1, temp.trieNodeArr[k], curStr);
				searchAndFindAux(charArr, i + 1, j, temp.trieNodeArr[k], curStr);
				searchAndFindAux(charArr, i + 1, j + 1, temp.trieNodeArr[k], curStr);
			}
			traversed[i][j] = false;
		}

	}

	public static void main(String[] args) {
		Boggle b = new Boggle();
		Trie t = b.new Trie();
		t.addStringToTrie("geeks");
		t.addStringToTrie("quiz");
		t.addStringToTrie("for");
		t.addStringToTrie("geeksforgeeks");
		// t.printTrie();
		char[][] input = { { 'g', 'i', 'z' }, { 'u', 'e', 'k' }, { 'q', 's', 'e' } };
		t.findWordsInBoggle(input);
	}

}

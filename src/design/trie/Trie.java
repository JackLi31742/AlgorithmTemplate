package design.trie;

/**
 * 208. 实现 Trie (前缀树)
 *	lintcode 442. 实现 Trie（前缀树）
 *
 *Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class Trie {

	
	private class Node{
        // isWord表示这个结点是否为一个单词的结尾
        // next[]表示这个结点的下一个26个字母结点
        public boolean isWord;  
        public Node[] next; 
        
        public Node() {
            this.isWord = false;
            this.next = new Node[26];
        }
    }
	
	private Node root;
    
	/** Initialize your data structure here. */
    public Trie() {

    	root = new Node(); 
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {

    	Node now = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            // 依次寻找每个字符
            // 如果所有下一个结点中没有当前字符，则增加新结点到下一个next[pos]
            int pos = word.charAt(i) - 'a';
            if (now.next[pos] == null) {
                now.next[pos] = new Node();
            }
            now = now.next[pos];
        }
        now.isWord = true;
        
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	
    	// 查找单词
        int n = word.length();
        Node now = root;
        for (int i = 0; i < n; i++) {
            int ch = word.charAt(i) - 'a';
            // 如果有下一个对应字符的结点，那么继续查找下一个结点
            // 如果没有下一个对应字符的结点，那么说明查找单词失败
            if (now.next[ch] != null) {
                now = now.next[ch];
            }
            else {
                return false;
            }
        }
        // 如果每个字符都有且是完整单词，才说明查找单词成功
        return now.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	
    	// 查找前缀
        int n = prefix.length();
        Node now = root;
        for (int i = 0; i < n; i++) {
            int ch = prefix.charAt(i) - 'a';
            // 如果有下一个对应字符的结点，那么继续查找下一个结点
            // 如果没有下一个对应字符的结点，那么说明查找前缀失败
            if (now.next[ch] != null) {
                now = now.next[ch];
            }
            else {
                return false;
            }
        }
        return true;
    }
    }
}

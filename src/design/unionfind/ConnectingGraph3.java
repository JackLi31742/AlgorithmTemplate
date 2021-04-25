package design.unionfind;

/**
 * lintcode 591 · 连接图 III
 *  给一个图中的 n 个节点, 记为 1 到 n . 在开始的时候图中没有边.
你需要完成下面两个方法:

connect(a, b), 添加一条连接节点 a, b的边
query(), 返回图中联通区域个数

并查集。 实时维护区域的个数，即若在某一次合并中两个区域合并成一个，那么数量-1。
 */
public class ConnectingGraph3 { 

    private int[] father = null;
    private int count;


    public ConnectingGraph3(int n) {
        // initialize your data structure here.
        father = new int[n + 1];
        count = n;
        for (int i = 1; i <= n; ++i) {
            father[i] = i;
        }
    }

    private int find(int x) {
    	if (father[x] == x) {
    		return x;
    	}
    	return father[x] = find(father[x]);
    }
    
    
    public void connect(int a, int b) {
        // Write your code here
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
            count --;
        }
    }
        
    public int query() {
        // Write your code here
        return count;
    }
}

package design.unionfind;

/**
 * lintcode 590 连接图 II · Connecting Graph II
 * 
 * 给一个图中的 n 个节点, 记为 1 到 n .在开始的时候图中没有边.
你需要完成下面两个方法：

connect(a, b), 添加一条连接节点 a, b的边
query(a), 返回图中含 a 的联通区域内节点个数



589.连接图的拓展题，区别在于多了一个nodeNum数组记录块的大小，初始每个块的大小都为1，每次合并，将两个块的大小相加得到合并后的块的大小

初始化每一个点的nodeNum为1
每次合并，将nodeNum合并到一个root上去
查询时只要找到块内最小root，nodeNum[root]即是答案
复杂度分析

时间复杂度O(n)
路径压缩优化可以将查询视作O(1）
空间复杂度O(n)
需要一个大小为n的数组去记录每一个点的father
 *
 */
public class ConnectingGraph2 {

    private int[] father = null;
    private int[] nodeNum = null;
    
    public ConnectingGraph2(int n) {//初始化
        father = new int[n + 1];
        nodeNum = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            father[i] = i;
            nodeNum[i] = 1;
        }

    }
    
    private int find(int x) { //查询
        int x2 = x;
        if (father[x] == x) {
            return x;
        }
        
        while (father[x] != x) {
            x = father[x];
        }
        //路径压缩
        while (x2 != x) {
            int temp = father[x2];
            father[x2] = x;
            x2 = temp;
        }
        return x;
    }
    /*递归版本
    private int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    */
    
    public void connect(int a, int b) {//合并
        int roota = find(a),rootb = find(b);
        if (roota != rootb) {
            father[roota] = rootb;
            //记录root块的点数
            nodeNum[rootb] += nodeNum[roota];
        }
    }

    public int query(int a) {//询问
        return nodeNum[find(a)];
    }
}  

package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {

	/**
	 * lintcode 137. 克隆图

		克隆一张无向图. 无向图的每个节点包含一个 label 和一个列表 neighbors. 保证每个节点的 label 互不相同.

你的程序需要返回一个经过深度拷贝的新图. 新图和原图具有同样的结构, 并且对新图的任何改动不会对原图造成任何影响.
	 * @param node
	 * @return
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
		
		if (node==null) {
			return null;
		}
		
		//采用BFS 方式 先遍历所有的节点
		
		List<UndirectedGraphNode> nodes=getAllGraphNodes(node);
		
		//克隆节点
//		List<UndirectedGraphNode> newNodes=copyNodes(nodes);
		
		Map<UndirectedGraphNode, UndirectedGraphNode> map=copyNodes2(nodes);
		
		//克隆边
		
		copyNeighbors(nodes, map);
		
		return map.get(node);
		
    }
	
	public List<UndirectedGraphNode> getAllGraphNodes (UndirectedGraphNode node){
		
		
		Queue<UndirectedGraphNode> queue=new ArrayDeque<UndirectedGraphNode>();
		
		Set<UndirectedGraphNode> set =new HashSet<UndirectedGraphNode>();
		
		queue.add(node);
		set.add(node);
		
		while(!queue.isEmpty()) {
			
			UndirectedGraphNode tempNode=queue.poll();
			
			List<UndirectedGraphNode> neighbors = tempNode.neighbors;
			
			for (UndirectedGraphNode undirectedGraphNode : neighbors) {
				
				if (set.contains(undirectedGraphNode)) {
					continue;
				}
				
				queue.add(undirectedGraphNode);
				set.add(undirectedGraphNode);
				
			}
		}
		
		
		return new ArrayList<UndirectedGraphNode>(set);
	}
	
	
	public List<UndirectedGraphNode> copyNodes(List<UndirectedGraphNode> nodes) {
		
		
		List<UndirectedGraphNode> newNodes=new ArrayList<UndirectedGraphNode>();
		
		for (UndirectedGraphNode undirectedGraphNode : nodes) {
			
			UndirectedGraphNode node=new UndirectedGraphNode(undirectedGraphNode.label);
			
			newNodes.add(node);
		}
		
		return newNodes;
	}
	
	public Map<UndirectedGraphNode, UndirectedGraphNode> copyNodes2(List<UndirectedGraphNode> nodes) {
		
		//用map更好点
		Map<UndirectedGraphNode, UndirectedGraphNode> map=new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		
		
		for (UndirectedGraphNode undirectedGraphNode : nodes) {
			
			map.put(undirectedGraphNode,new UndirectedGraphNode(undirectedGraphNode.label));

		}
		
		return map;
	}
	
	
	public void copyNeighbors(List<UndirectedGraphNode> nodes,Map<UndirectedGraphNode, UndirectedGraphNode> map) {
		
		for (UndirectedGraphNode node : nodes) {
			
			List<UndirectedGraphNode> neighbors=map.get(node).neighbors;
			
			for (UndirectedGraphNode undirectedGraphNode : node.neighbors) {
				
				neighbors.add(map.get(undirectedGraphNode));
			}
		}
		
	}
	
	
}

class UndirectedGraphNode{
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}
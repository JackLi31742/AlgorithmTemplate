package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class TopologicalSorting {
	
	
	public static void main(String[] args) {
		
		
		TopologicalSorting topologicalSorting=new TopologicalSorting();
//		int[][]prerequisites= {{1,0}};
//		
//		
//		int[] org= {5,3,2,4,1};
//		int[][]seqs= {{5,3,2,4},{4,1},{1},{3},{2,4}, {1000000000}};
//		
//		
//		topologicalSorting.sequenceReconstruction(org, seqs);
		
		
		String[] words= {"abc","ab"};
		topologicalSorting.alienOrder(words);
		
	}

	/**
	 * 127. 拓扑排序
	   *       针对给定的有向图找到任意一种拓扑排序的顺序.
	 * 
	 * @param graph
	 * @return
	 */
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		// write your code here
		ArrayList<DirectedGraphNode> result=new ArrayList<DirectedGraphNode>();
		if (graph==null||graph.size()==0) {
			return result;
		}
		
		//统计每个点的入度
		HashMap<DirectedGraphNode, Integer> map=new HashMap<DirectedGraphNode, Integer>();
		
		for (DirectedGraphNode node : graph) {
			
			ArrayList<DirectedGraphNode> neighbors = node.neighbors;
			//图的链表法表示中，对于每个点的每个邻居，如果在链表中存在，那么入度就+1
			for (DirectedGraphNode neighbor : neighbors) {
				
				map.put(neighbor, map.getOrDefault(neighbor, 0)+1);
			}
		}
		
		Queue<DirectedGraphNode> queue=new ArrayDeque<DirectedGraphNode>();
		
		//对图的链表的节点，如果入度为0，加入到queue中
		for (DirectedGraphNode node : graph) {
			
			if (!map.containsKey(node)||map.get(node)==0) {
				
				queue.add(node);
				result.add(node);
			}
		}
		
		//BFS
		while(!queue.isEmpty()) {
			
			DirectedGraphNode node = queue.poll();
			
			ArrayList<DirectedGraphNode> neighbors = node.neighbors;

			//对于所有的邻居，入度都减1
			for (DirectedGraphNode neighbor : neighbors) {
				
				map.replace(neighbor, map.get(neighbor), map.get(neighbor)-1);
//				map.put(neighbor, map.get(neighbor)-1);
				
				if (map.get(neighbor)==0) {
					
					queue.add(neighbor);
					result.add(neighbor);
				}
			}
		}
		
		
		return result;
		
	}

	
	
	
	/**
	 * 605. 序列重构
	 *   判断是否序列 org 能唯一地由 seqs重构得出.
	* org是一个由从1到n的正整数排列而成的序列，1 <= n <=10^4。 
	 *   重构表示组合成seqs的一个最短的父序列 (意思是，一个最短的序列使得所有 seqs里的序列都是它的子序列).
		判断是否有且仅有一个能从 seqs重构出来的序列，并且这个序列是org。
		
		输入:org = [1,2,3], seqs = [[1,2],[1,3]]
		输出: false
		解释:
		[1,2,3] 并不是唯一可以被重构出的序列，还可以重构出 [1,3,2]
		
		
		和课程的题目类似，包含seqs的最短父序列，说明最后构建的父序列中，
		包含的seqs的顺序是不变的，也就是说seqs中的每个元素是有顺序的
		
		
	 * @param org
	 * @param seqs 正序
	 * @return
	 */
	public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here
		
		if (org==null||seqs==null||seqs.length==0) {
			return false;
		}
		//由于seqs不是像课程题一样是按照0到n-1的编号，所以需要用映射
		//要用hashset，因为可能会有重复
		Map<Integer, HashSet<Integer>> graph =new HashMap<Integer, HashSet<Integer>>();
		
		Map<Integer, Integer> inDegree =new HashMap<Integer, Integer>();
		
		
		buildGraph(org, seqs, graph, inDegree);
		
		//不能仅仅以大小进行判断
		if (org.length!=graph.size()) {
			
			return false;
		}
		
		
		ArrayList<Integer> result=getResult(org,graph, inDegree);
		
		
		if (result==null||result.size()!=org.length) {
			return false;
		}
		
		//得到答案后逐一对比
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i)!=org[i]) {
				return false;
			}
		}
		
		
		return true;
    }
	
	
	public void buildGraph(int[] org, int[][] seqs,
			Map<Integer, HashSet<Integer>> graph,Map<Integer, Integer> inDegree) {
		
//		for (int i = 0; i < org.length; i++) {
//			
//			graph.put(org[i], new ArrayList<Integer>());
//			
//			
//		}
		
		for (int i = 0; i < seqs.length; i++) {
			
			for (int j = 0; j < seqs[i].length; j++) {
				
				if (!graph.containsKey(seqs[i][j])) {
					
					graph.put(seqs[i][j], new HashSet<Integer>());
				}
			}
		}
		
		for (int i = 0; i < seqs.length; i++) {
			
			for (int j = 1; j < seqs[i].length; j++) {
				
				//都是从同一个点来到这个点的话，就只记录一次
				if (!graph.get(seqs[i][j-1]).contains(seqs[i][j])) {
					
					graph.get(seqs[i][j-1]).add(seqs[i][j]);
					
					inDegree.put(seqs[i][j], inDegree.getOrDefault(seqs[i][j], 0)+1);
				}
				
			}
		}
		
		
	}
	
	
	public ArrayList<Integer> getResult(int[] org,
			Map<Integer, HashSet<Integer>> graph,Map<Integer, Integer> inDegree){
	
		Queue<Integer> queue=new ArrayDeque<Integer>();
		
		ArrayList<Integer> result =new ArrayList<Integer>();
		
		
		for (int i = 0; i < org.length; i++) {
			
			if (!inDegree.containsKey(org[i])) {
				
				queue.add(org[i]);
				result.add(org[i]);
			}
		}
		
		
		while(!queue.isEmpty()) {
			//不能仅仅以大小进行判断
			if (queue.size()>1) {
				return null;
			}
			
			Integer node = queue.poll();
			
			HashSet<Integer> set = graph.get(node);
			
			for (Integer next : set) {
				inDegree.put(next, inDegree.get(next)-1);
				
				if (inDegree.get(next)==0) {
					queue.add(next);
					result.add(next);
				}
			}
			
		}
		return result;
	}
	
	
	/**
	 * 892. 外星人字典
	 * 有一种新的使用拉丁字母的外来语言。但是，你不知道字母之间的顺序。
	 * 你会从词典中收到一个非空的单词列表，其中的单词在这种新语言的规则下按字典顺序排序。请推导出这种语言的字母顺序。
	 * 
	 * 你可以假设所有的字母都是小写。
		如果a是b的前缀且b出现在a之前，那么这个顺序是无效的。
		如果顺序是无效的，则返回空字符串。
		这里可能有多个有效的字母顺序，返回以正常字典顺序看来最小的。
		
		
		字母有顺序，说明字母有先后，说明是拓扑排序
		最后返回时要以正常字典顺序看来最小的，需要在从queue中poll出来的时候，用优先队列，出来的时候就是正常的字典序
		
		
		这道题构建图的时候光从题目的规则，不明确，所以是构建图的问题，不是拓扑排序的问题
	 * @param words
	 * @return
	 */
	public String alienOrder(String[] words) {
        // Write your code here
		
		Map<Character, Set<Character>> graph=new HashMap<Character, Set<Character>>();
		
		Map<Character, Integer> indegree=new HashMap<Character, Integer>();
		
		Map<Character, Set<Character>> graph2=buildGraph(words, graph, indegree);
		
		if (graph2==null) {
			return "";
		}
		
		PriorityQueue<Character> queue=new PriorityQueue<Character>();
		
		StringBuilder result=new StringBuilder();
		
		result.append("");
		
		for (Entry<Character, Integer> entry : indegree.entrySet()) {
			
			
			if (entry.getValue()==0) {
				
				queue.add(entry.getKey());
				
			}
		}
		
		
		while(!queue.isEmpty()) {
			
			Character character = queue.poll();
			
			result.append(character);
			
			Set<Character> set = graph.get(character);
			
			for (Character next : set) {
				
				indegree.put(next, indegree.get(next)-1);
				
				if (indegree.get(next)==0) {
					
					queue.add(next);
				}
			}
		}
				
		if (result.length() != graph.size()) {
            return "";
        }
		
		
		return result.toString();
    }
	
	
	public Map<Character, Set<Character>> buildGraph(String[] words,
			Map<Character, Set<Character>> graph,
			Map<Character, Integer> indegree) {
		
		for (String word : words) {
			
			char[] arr = word.toCharArray();
			
			for (int i = 0; i < arr.length; i++) {
				
				if (!graph.containsKey(arr[i])) {
					graph.put(arr[i], new HashSet<Character>());
					
					indegree.put(arr[i], 0);
				}
			}
		}
		
		//是对前后的单词进行对比
		
//		for (int i = 0; i < words.length-1; i++) {
//			
////			for (int j = i+1; j < words.length; j++) {
//				int j = i+1;
//				int len=Math.min(words[i].length(), words[j].length());
//				
//				for (int k = 0; k < len; k++) {
//					
//					if (words[i].charAt(k)!=words[j].charAt(k)) {
//						
//						if (!graph.get(words[i].charAt(k)).contains(words[j].charAt(k))) {
//							
//							graph.get(words[i].charAt(k)).add(words[j].charAt(k));
//							
//							indegree.put(words[j].charAt(k), indegree.get(words[j].charAt(k))+1);
//							
//							break;
//						}
//						
//					}
//				}
////			}
//		}
		
		
		for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    indegree.put(words[i + 1].charAt(index), indegree.get(words[i + 1].charAt(index))+1);
                    break;
                }
                index++;
            }
            if (index == Math.min(words[i].length(), words[i + 1].length())) {
                if (words[i].length() > words[i + 1].length()) {
                    return null;
                }
            }
        }
		
		return graph;
		
	}
}

class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;

	DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}
}

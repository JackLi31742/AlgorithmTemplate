package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Courses {

	
	
	/**
	 * 616. 安排课程
		课被标号为 0 到 n-1 。
有一些课程需要“前置课程”，比如如果你要上课程0，你需要先学课程1，我们用一个匹配来表示他们： [0,1]

给你课程的总数量和一些前置课程的需求，返回你为了学完所有课程所安排的学习顺序。
	 * @param numCourses
	 * @param prerequisites  入和出是反过来的
	 * @return
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
		
		int[] result=new int[numCourses];
		int index=0;
		
		//图
		ArrayList<ArrayList<Integer>> graph =new ArrayList<ArrayList<Integer>>(numCourses);
		//入度
		int[] inDegree=new int[numCourses];		
		
		//构建图
		buildGraph(numCourses, prerequisites, graph, inDegree);
		//拓扑排序
		Queue<Integer> queue=new ArrayDeque<Integer>();
		
		//将入度为0的加入队列
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i]==0) {
				queue.add(i);
				result[index]=i;
				index++;
			}
		}
		
		
		while(!queue.isEmpty()) {
			
			Integer num = queue.poll();
			
			ArrayList<Integer> nexts = graph.get(num);
			
			for (Integer next : nexts) {
				
				inDegree[next]--;
				
				if (inDegree[next]==0) {
					queue.add(next);
					result[index]=next;
					index++;
				}
			}
		}
		
		if (index==numCourses) {
			return result;
		}
		
		return new int[] {};
    }
	
	//构建图的链表形式
	public void buildGraph(int numCourses, int[][] prerequisites,
			ArrayList<ArrayList<Integer>> graph,int[] inDegree){
		
		for (int i = 0; i < numCourses; i++) {
			
			graph.add(new ArrayList<Integer>());
		}
		
		for (int[] edge : prerequisites) {
			
			graph.get(edge[1]).add(edge[0]);
			//跟上边的类似，只是由于编号确定下标
			inDegree[edge[0]]++;
		}
		
	}
}

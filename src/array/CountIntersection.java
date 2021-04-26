package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountIntersection {

	/**
	 * 391 · 数飞机
	 * 给出飞机的起飞和降落时间的列表，用序列 interval 表示. 请计算出天上同时最多有多少架飞机？
	 * 输入: [(1, 10), (2, 3), (5, 8), (4, 7)]
		输出: 3
		解释: 
		第一架飞机在1时刻起飞, 10时刻降落.
		第二架飞机在2时刻起飞, 3时刻降落.
		第三架飞机在5时刻起飞, 8时刻降落.
		第四架飞机在4时刻起飞, 7时刻降落.
		在5时刻到6时刻之间, 天空中有三架飞机.
		
		如果多架飞机降落和起飞在同一时刻，我们认为降落有优先权。
		
		将每架飞机的起降时间作为区间左右端点，建立两个事件
		• 对所有事件排序，相同时间的事件降落排在起飞前面
		• 扫描线，定义计数器C=0
		– 遇到起飞事件，C+=1
		– 遇到降落事件，C-=1
		• C的最大值即为答案
		• FollowUp：如果同时起降，认为先起飞，怎么修改算法
	 * @param airplanes
	 * @return
	 */
	public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
		List<Point> list = new ArrayList<>(airplanes.size() * 2);
        for (Interval i : airplanes) {
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }

        Collections.sort(list, Point.PointComparator);
        int count = 0, ans = 1;
        for (Point p : list) {
            if(p.flag == 1) 
                count++;
            else 
                count--;
            ans = Math.max(ans, count);
        }

        return ans;
    }
	
	
	/**
	 * lintcode 821 时间交集 · Time Intersection
	 * 
	 * 目前有两个用户的有序在线时间序列，
	 * 每一个区间记录了该用户的登录时间点x和离线时间点y，
	 * 请找出这两个用户同时在线的时间段，输出的时间段请从小到大排序。
	 * 你需要返回一个intervals的列表
	 * 
	 * 输入：seqA = [(1,2),(5,100)], seqB = [(1,6)]
		输出：[(1,2),(5,6)]
		解释：在 (1,2), (5,6) 这两个时间段内，两个用户同时在线。
	 * 
	 * @param seqA
	 * @param seqB
	 * @return
	 */
	public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
        // Write your code here
        int [] visit = new int[1000001];
        for(int i = 0; i < 1000001; i++) {
            visit[i] = 0;
        }
        for(int i = 0; i < seqA.size(); i++) {
            for(int j = seqA.get(i).start; j <= seqA.get(i).end; j++) {
                visit[j] ++;
            }
        }
        for(int i = 0; i < seqB.size(); i++) {
            for(int j = seqB.get(i).start; j <= seqB.get(i).end; j++) {
                visit[j] ++;
            }
        }
        List<Interval> ans = new ArrayList<>();
        for(int i = 0; i < 1000001; i++) {
            if(visit[i] >= 2) {
                int x = i;
                int y = i;
                while(y + 1 < 1000001 && visit[y + 1] >= 2) {
                    y++;
                }
                ans.add(new Interval(x, y));
                i = y + 1;
            }
        }
        return ans;
    }
	
	
}


class Point{
    int time;//时间
    int flag;//1代表起飞，0代表降落

    Point(int t, int s) {
        this.time = t;
        this.flag = s;
    }
    public static Comparator<Point> PointComparator = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if(p1.time == p2.time) 
                return p1.flag - p2.flag;
            else 
                return p1.time - p2.time;
      }
    };
}

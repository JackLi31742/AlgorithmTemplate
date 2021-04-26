package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {

	
	/**
	 * 920 · 会议室
	 * 给定一系列的会议时间间隔，包括起始和结束时间[[s1,e1]，[s2,e2]，…(si < ei)，确定一个人是否可以参加所有会议。
	 * 
	 * 输入: intervals = [(0,30),(5,10),(15,20)]
		输出: false
		解释:
		(0,30), (5,10) 和 (0,30),(15,20) 这两对会议会冲突
		
		
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
        // Write your code here
		if(intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        int end = intervals[0].end;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start < end) {
                return false;
            }
            end = Math.max(end, intervals[i].end);
        }
        return true;
    }
	
	/**
	 * 919 · 会议室 II
	 * 给定一系列的会议时间间隔intervals，
	 * 包括起始和结束时间[[s1,e1],[s2,e2],...] (si < ei)，
	 * 找到所需的最小的会议室数量。
	 * 
	 * 输入: intervals = [(0,30),(5,10),(15,20)]
		输出: 2
		解释:
		需要两个会议室
		会议室1:(0,30)
		会议室2:(5,10),(15,20)
		
		
		最少会议室的数量就是同时在天上最多飞机的数量
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
		List<Point> list = new ArrayList<>(intervals.size()*2);
        for(Interval i : intervals){
          list.add(new Point(i.start, 1));
          list.add(new Point(i.end, 0));
        }
    
        Collections.sort(list,Point.PointComparator );
        int count = 0, ans = 0;
        for(Point p : list){
            if(p.flag == 1) {
                count++;
            }
            else {
                count--;
            }
            ans = Math.max(ans, count);
        }
    
        return ans;
    }
	
	
	/**
	 * 1897 · 会议室 3
	 * 你有一个当前会议列表intervals，里面表明了每个会议的开始和结束时间，
	 * 以及一些会议室rooms。现在有一系列会议ask需要加入，
	 * 逐个判断他们能否被安排进当前的会议列表中而不产生冲突。
	 * 一个会议室在同一时间只能进行一场会议。每个询问都相互独立。
	 * 
	 * 输入:
		Intervals:[[1,2],[4,5],[8,10]],rooms = 1,ask: [[2,3],[3,4]]
		输出: 
		[true,true]
		解释:
		对于[2,3]的询问，我们可以安排一个会议室room0。
		以下是room0的会议列表：
		[[1,2],[2,3],[4,5],[8,10]]
		对于[3,4]的询问，我们可以安排一个会议室room0。
		以下是room0的会议列表：
		[[1,2],[3,4],[4,5],[8,10]]
		
		扫描线+前缀和
		首先， 开一个范围那么大的数组， 然后对于每个interval， mark一下不available。 也就是用了+1没用-1.

然后， 其实可以另外开一个数组， 等等再常数级别优化， 这个时候， 我们有了整个所有time的占用情况， 这个时候知道每个时间， 占用了几个。

然后我们根据占用情况， 反推出可用情况， 就是占用跟总房间去比。

然后妙的来啦， 怎么样在常数时间， 知道每个ask行不行呢？这里我们直接把available的时间标成1，
 不available标成0. 然后再去算一个前缀和。 然后看一下前缀和和出来， 和区间长度比较，
  如果区间里面都是1，那么加起来肯定等于区间长度。 那么这个题目答案就出来了
	 * @param intervals
	 * @param rooms
	 * @param ask
	 * @return
	 */
	int[] sum = new int[50050];
    int[] vis = new int[50050];
    public boolean[] meetingRoomIII(int[][] intervals, int rooms, int[][] ask) {
        // Write your code here.
        int length = ask.length; 
        boolean[] ans = new boolean[length];
        sum[0] = 0;
        int maxn = 0;
        int i;
        for (i = 0; i < intervals.length; i++) {
            vis[intervals[i][0]]++;
            vis[intervals[i][1]]--;
            maxn = Math.max(maxn, intervals[i][1]);
        }
        for (i = 0; i < ask.length; i++) {
            maxn = Math.max(maxn, ask[i][1]);
        }
        int tmp = 0;
        for (i = 1; i <= maxn; i++) {
            tmp += vis[i];
            if (tmp < rooms) {
                sum[i] = 0;
            }
            else {
                sum[i] = 1;
            }
        }
        for (i = 1; i <= maxn; i++) sum[i] += sum[i - 1];
        for (i = 0; i < ask.length; i++) {
            if (sum[ask[i][0] - 1] == sum[ask[i][1] - 1]) {
                ans[i] = true;
            }
            else ans[i] = false;
        }
        return ans;
    }
    
    
    /**
     * lintcode 300 · 会议室4
     * 给定一系列会议的开始时间、结束时间[[s1,e1]，[s2,e2]，…(si < ei)及他们的价值。
     * 	你在同一时刻只能参加一个会议，请问你可以获得的最大价值是多少
     * 输入:
	meeting = [[10,40],[20,50],[30,45],[40,60]]
	value = [3,6,2,4]
	输出: 7
	说明: 你可以参加第0个会议和第3个会议，总价值为3 + 4 = 7.


     * dp[i]为到i时刻为止的最大价值。

我们可以先预处理一下每个时间点结束的会议有哪些。然后从对应的开始点进行状态转移。
	
	dp[i]=max(dp[i-1],dp[start]+val)
     * @param meeting
     * @param value
     * @return
     */
    public int maxValue(int[][] meeting, int[] value) {
        // write your code here
        int n = meeting.length;
        int maxEndTime = 0;
        for (int i = 0; i < n; i++) {
            maxEndTime = Math.max(maxEndTime, meeting[i][1]);
        }
        ArrayList<ArrayList<Integer>> meetings = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= maxEndTime; i++){
            meetings.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) {
            meetings.get(meeting[i][1]).add(i);
        }
        int[] dp = new int[maxEndTime + 1];
        for (int i = 1; i <= maxEndTime; i++) {
            dp[i] = dp[i - 1];
            for (int j: meetings.get(i)) {
                dp[i] = Math.max(dp[i], dp[meeting[j][0]] + value[j]);
            }
        }
        return dp[maxEndTime];
    }
}

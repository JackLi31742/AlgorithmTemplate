package binarysearch;

public class CopyBooks {

	
	/**
	 * 437 · 书籍复印
	 * 给定n本书，第i本书有[i]页。有k个人来抄这些书。

这些书排成一行，每个人都可以索取连续一段的书。
例如，一个复印机可以连续地将书从第i册复制到第j册，但是他不能复制第1册、第2册和第4册（没有第3册）。

他们在同一时间开始抄书，每抄一页书都要花1分钟。为了让最慢的复印机能在最早的时间完成书的分配，最好的策略是什么？

输入: pages = [3, 2, 4], k = 2
输出: 5
解释: 第一个人复印前两本书, 耗时 5 分钟. 第二个人复印第三本书, 耗时 4 分钟.


答案的范围在 max(pages)~sum(pages) 之间，
每次二分到一个时间 time_limit 的时候，
用贪心法从左到右扫描一下 pages，看看需要多少个人来完成抄袭。
 如果这个值 <= k，那么意味着大家花的时间可能可以再少一些，如果 > k 则意味着人数不够，需要降低工作量。

时间复杂度 O(nlog(sum))是该问题时间复杂度上的最优解法
	 * @param pages
	 * @param k
	 * @return
	 */
	public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        
        int start = 0, end = getSum(pages);

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (getNumberOfCopiers(pages, mid) <= k) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (getNumberOfCopiers(pages, start) <= k) {
            return start;
        }
        return end;
    }

    private int getNumberOfCopiers(int[] pages, int limit) {
        int copiers = 0;
        int lastCopied = limit;
        
        for (int page : pages) {
            if (page > limit) {
                return Integer.MAX_VALUE;
            }
            if (lastCopied + page > limit) {
                copiers++;
                lastCopied = 0;
            }
            
            lastCopied += page;
        }
        
        return copiers;
    }
    
    private int getSum(int[] pages) {
        int sum = 0;
        for (int page : pages) {
            sum += page;
        }
        return sum;
    }
}

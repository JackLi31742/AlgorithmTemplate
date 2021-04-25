package unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AccountsMerge {

	/**
	 * 1070 · 账户合并
	 * 给定一个帐户列表，每个元素accounts [i]是一个字符串列表，其中第一个元素accounts [i] [0]是账户名称，其余元素是这个帐户的电子邮件。
现在，我们想合并这些帐户。
如果两个帐户有相同的电子邮件地址，则这两个帐户肯定属于同一个人。
请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为两个不同的人可能会使用相同的名称。
一个人可以拥有任意数量的账户，但他的所有帐户肯定具有相同的名称。
合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按字典序排序后的电子邮件。
帐户本身可以按任何顺序返回。
	


凡是这种动态的加入，查询某个集合的问题都可以考虑并查集

一种做法是将每个原始账户作为一个节点，但是两个账户可以连接取决于它们共享
至少一个邮箱，处理起来比较麻烦
我们可以将每个邮箱作为一个节点，同一个原始账户中的邮箱之间连边。并用老大
哥节点存储用户名

这题需要通过map来作为father并用owner记录拥有者，
find数组中，当father[x]！=[x]时，不断循环查询，找到最小x,同时更新路径上的所有点的最小指向（路径压缩）来达到最快的查询速度
connet中，若a,b未连接，通过father数组将a,b指向同一个块
我们通过并查集找到每个人拥有的邮箱，并将它们通过一个map存下来，用set去重，最后遍历这个map将答案输出
复杂度分析

时间复杂度O(n)
用了路径压缩，查询效率为O(1)
空间复杂度O(n)
n为邮箱的数目
	 */
	private Map<String, String> father = new HashMap<String, String>();
    private Map<String, String> owner = new HashMap<String, String>();
    private Map<String, TreeSet<String>> mp = new HashMap<>();
    
    private String find(String s) {
        if (father.get(s) == s) {
            return s;
        }
        //path compression
        father.put(s, find(father.get(s)));
        return father.get(s);
    }
    private void connect(String a,String b) {
        String fa = find(a);
        String fb = find(b);
        if (fa!=(fb)) {
            father.put(fa,fb);
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        for (List<String> account:accounts) {
            for (int i = 0; i < account.size(); i++) {
                father.put(account.get(i), account.get(i));
                owner.put(account.get(i), account.get(0));
            }
        }
        // union all the emails under the same account
        for (List<String> account:accounts) {
            for (int i = 2; i < account.size(); i++) {
                // let email1 be the root of all other emails under the same account
                connect(account.get(i),account.get(1));
            }
        }
        
        for (List<String> account:accounts) {
            String tmp = find(account.get(1));
            if (!mp.containsKey(tmp)) {
                    mp.put(tmp, new TreeSet<>());
                }
            for (int i = 1; i < account.size(); i++) {
                //account[1] <-> set of account[i]s
                mp.get(tmp).add(account.get(i));
            }
        }
        for(String key : mp.keySet()){
            List<String> record = new ArrayList(mp.get(key));
            record.add(0, owner.get(key));
            result.add(record);
        }
        return result;
    }
}

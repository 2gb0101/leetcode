package Array.Middle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//Offer：字符串 S 和单词字典 words
//Target：求 words[i] 中是 S 的子序列的单词个数

//所有在words和 S 里的单词都只由小写字母组成
//S 的长度在 [1, 50000]
//words 的长度在 [1, 5000]
//words[i]的长度在[1, 50]
public class D23_792_numMatchingSubseq {
//    解法1： 桶原理
    public int numMatchingSubseq1(String S, String[] words) {
        List<int[]>[] buckets = new List[26];
        int res = 0;
        for(int i = 0 ; i < 26; i++){
            buckets[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < words.length; i++){
            // 这里的int数组，第二个位置是用来给后面的循环计数用的，所以初始化为0
            // 这里的i，代表的是第几个子字符串
            // 根据字符串的第一个字符，建立映射
            buckets[words[i].charAt(0) - 'a'].add(new int[]{i, 0});
        }
        for(char c: S.toCharArray()){
            List<int[]> pre = buckets[c - 'a'];
            buckets[c - 'a'] = new ArrayList<>();
            for(int[] item: pre){
                item[1]++;
                if(item[1] >= words[item[0]].length()){
                    res++;
                } else {
                    // 每次循环，都将子字符串的第一个字母截掉，进入下一轮循环
                    buckets[words[item[0]].charAt(item[1]) - 'a'].add(item);
                }
            }
        }
        return res;
    }

//    解法2：二分搜索
//    我们记录每个字符在S中出现的位置，每个字符在S中出现的位置列表一定是增序序列
//    对于每个word, 从前向后遍历时
//        我们需要查找该字符在S中的最小位置
//        此时可以直接使用二分搜索的方式
//        找到的位置就是下一个字符的索引下界，下界的初始值为-1。
//    时间复杂度 ：O(m * len(m) * lg(n)), 略慢于方法一
    // n = S.length(), m = words.length

    @Test
    public void test(){
        String S = "abcde";
        String[] words = new String[]{"a", "bb", "acd", "ace"};
        new D23_792_numMatchingSubseq().numMatchingSubseq2(S,words);
    }

    public int numMatchingSubseq2(String S, String[] words) {
        List<Integer>[] indexs = new List[26];
        for(int i = 0; i < 26; i++){
            indexs[i] = new ArrayList<>();
        }
        int n = S.length(), m = words.length;
        for(int i = 0 ; i < n ; i++){
            indexs[S.charAt(i) - 'a'].add(i);
        }
        int res = 0;
        outer:
        for(String word: words){
            int id = -1;
            for(char c: word.toCharArray()){
                int nextId = getNextId(indexs[c - 'a'], id);
                if(nextId == -1){
                    continue outer;
                }
                id = nextId;
            }
            res++;
        }
        return res;
    }

    private int getNextId(List<Integer> indexs, int cur){
        int s = 0, e = indexs.size() - 1;
        //二分法
        while(s <= e){
            int m = s + ((e - s) >> 1);
            if(indexs.get(m) > cur){
                e = m - 1;
            } else { // indexs.get(m) <= cur
                s = m + 1;
            }
        }
        return s == indexs.size() ? -1: indexs.get(s);
    }

}

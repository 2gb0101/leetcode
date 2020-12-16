package Array.Easy;


import java.util.ArrayList;
import java.util.List;

//Offer ：一个单词数组，两个单词
//Target 求这两个单词之间的最小距离，限定了两个单词不同，而且都在数组中
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//Given word1 = “coding”, word2 = “practice”, return 3.
public class D8_243_ShortestWordDistance {

//    哈希表法（最先想到的笨方法）：建立每个单词和其所有出现位置数组的映射

//    辅助变量法：
//        反正建立哈希表映射的时候，需要遍历一遍数组
//        那不如直接遍历一遍数组
//        直接把两个给定单词所有出现的位置存到辅助变量里

    //写法①：利用两个List，分別存放两个给定单词，然后再对两个列表进行两两比较更新结果
    public static int shortestDistance1(String[] words, String word1, String word2) {
        List<Integer> idx1 = new ArrayList<Integer>();
        List<Integer> idx2 = new ArrayList<Integer>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            if (words[i] == word1) idx1.add(i);
            else if (words[i] == word2) idx2.add(i);
        }
        for (int i = 0; i < idx1.size(); ++i) {
            for (int j = 0; j < idx2.size(); ++j) {
                res = Math.min(res, Math.abs(idx1.get(i) - idx2.get(j)));
            }
        }
        return res;
    }

    //写法②：利用两个int变量
    public static int shortestDistance2(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            if (words[i] == word1) p1 = i;
            else if (words[i] == word2) p2 = i;
            if (p1 != -1 && p2 != -1) res = Math.min(res, Math.abs(p1 - p2));
        }
        return res;
    }

    //写法③：利用一个int变量
    public static int shortestDistance3(String[] words, String word1, String word2) {
        int idx = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            if (words[i] == word1 || words[i] == word2) {
                if (idx != -1 && words[idx] != words[i]) {
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }
}

package Array.Easy;

import java.util.ArrayList;
import java.util.List;

//Offer：给定仅有小写字母组成的字符串数组 A
//Target：返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表
//例如
//如果一个字符在每个字符串中出现 3 次，但不是 4 次
//则需要在最终答案中包含该字符 3 次
public class D22_1002_Find_Common_Characters {
//    辅助数组法：
//    新建一个数组hash，记录每个字符出现的次数
//    该数组利用（字符的ASCII码-97的值）做数组下标
//    遍历字符串数组A
//    对A[0]：直接记录每个字符出现的个数
//    对数组A中其他的字符串：新建临时数组tmpHash来记录当前字符数组每个字符出现的次数
//                       比较hash数组和tmpHash数组中对应字符出现的次数
//                       更新hash数组，记录比较后的较小值
//    最后组装返回结果即可
    public List<String> commonChars(String[] A) {

        // 入参检查
        if(A == null || A.length == 0 || A.length == 1) {
            return null;
        }
        // 记录每个字符出现的次数（字符的ASCII码-97的值做数组下标）
        int[] hash = new int[26];
        // 是否第一次维护hash数组
        boolean firstFlag = true;
        // 遍历字符串
        for (String word : A) {
            // 拆分成字符数组
            char[] wordChars = word.toCharArray();
            // 如果是第一次维护hash数组
            if (firstFlag) {
                // 直接记录每个字符出现的个数
                for (char wordChar : wordChars) {
                    hash[wordChar - 97]++;
                }
                // 标志置为否
                firstFlag = false;
                // 如果不是第一次维护，即hash数组中有值时
            }else {
                // 新建临时数组tmpHash来记录当前字符数组每个字符出现的次数
                int[] tmpHash = new int[26];
                for (char wordChar : wordChars) {
                    tmpHash[wordChar - 97]++;
                }

                // 维护hash数组
                for(int i = 0; i < hash.length; ++i) {
                    // 比较hash数组和tmpHash数组
                    // hash记录每次每个字符出现的最小次数
                    if(hash[i] > tmpHash[i]) {
                        hash[i] = tmpHash[i];
                    }
                }
            }
        }
        // 组装返回结果
        List<String> res = new ArrayList<>();
        for(int i = 0; i < hash.length; ++i) {
            if(hash[i] != 0) {
                String tmp = String.valueOf((char)(i + 97));
                for(int j = 0; j < hash[i]; ++j) {
                    res.add(tmp);
                }
            }
        }

        return res;
    }
}

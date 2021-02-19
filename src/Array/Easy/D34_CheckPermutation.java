package Array.Easy;

//实现一个算法，确定一个字符串 s 的所有字符是否全都不同

import java.util.HashSet;
import java.util.Set;

//限制：
//    0 <= len(s) <= 100
//    如果你不使用额外的数据结构，会很加分
public class D34_CheckPermutation {
//    解题思路1：字符串替换
//    用replace后的长度做判断即可
    public boolean isUnique1(String astr) {
        for (int i=0;i<astr.length();i++){
            String s=astr;
            s=s.replace(String.valueOf(s.charAt(i)),""); //把字符串s中位置为i的字母都换成""
            if (s.length()!=astr.length()-1)
                return false;
        }
        return true;
    }


//    解题思路2：位运算
//    由于ASCII码字符个数为128个，而且题目说了如果你不使用额外的数据结构，会很加分
//    因此可以使用两个64位的long变量来存储是否出现某个字符，二进制位1表示出现过， 0表示未出现过
    public boolean isUnique2(String astr) {
        long low64 = 0;
        long high64 = 0;

        for (char c : astr.toCharArray()) {
            if (c >= 64) {
                long bitIndex = 1L << c - 64;
                if ((high64 & bitIndex) != 0) {
                    return false;
                }

                high64 |= bitIndex;
            } else {
                long bitIndex = 1L << c;
                if ((low64 & bitIndex) != 0) {
                    return false;
                }

                low64 |= bitIndex;
            }

        }
        return true;
    }


//    解题思路3：利用String类型的indexOf方法
//    String.indexOf(String str，int index)
//    从index的地方开始找，返回第一次出现的索引
    public boolean isUnique3(String astr) {
        for(int i = 0; i < astr.length() - 1; i++) {
            if(astr.indexOf(astr.charAt(i), i+1) != -1) {
                return false;
            }
        }
        return true;
    }


//        解题思路4：双指针暴力循环
        public boolean isUnique4(String astr) {
            boolean isSame = true;
            for(int i = 0; i < astr.length() - 1; i++)
            {
                for(int j = i + 1; j < astr.length(); j++)
                {
                    if(astr.charAt(i) == astr.charAt(j))
                    {
                        return false;
                    }
                }
            }
            return isSame;
        }


//        解题思路5：利用set去重
//        利用set去重来判断是否有相同字符
        public boolean isUnique5(String astr) {
            Set set = new HashSet();
            for (int i = 0; i <astr.length() ; i++) {
                set.add(astr.charAt(i));
            }
            return set.size() == astr.length();
        }
}

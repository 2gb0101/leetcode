package String.Easy;

//已知：
//    罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
//    字符          数值
//    I             1
//    V             5
//    X             10
//    L             50
//    C             100
//    D             500
//    M             1000

//例如
//    罗马数字 2 写做 II ，即为两个并列的 1
//    12 写做 XII ，即为 X + II
//    27 写做  XXVII, 即为 XX + V + II
//
//通常情况下，罗马数字中小的数字在大的数字的右边。
//但也存在特例，例如 4 不写做 IIII，而是 IV。
//数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
//同样地，数字 9 表示为 IX。
//这个特殊的规则只适用于以下六种情况：
//    I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
//    X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
//    C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

//Offer: 给定一个罗马数字
//Target: 将其转换成整数
//输入确保在 1 到 3999 的范围内

import java.util.HashMap;
import java.util.Map;

public class D36_13_romanToInt {
//    解法1：
//    按照题目的描述，可以总结如下规则：
//        1.罗马数字由 I,V,X,L,C,D,M 构成；
//        2.当小值在大值的左边，则减小值，如 IV=5-1=4；
//        3.当小值在大值的右边，则加小值，如 VI=5+1=6；
//        4.由上可知，右值永远为正，因此最后一位必然为正。
//    一言蔽之，把一个小值放在大值的左边，就是做减法，否则为加法。
//    在代码实现上
//        方式1：可以往后看多一位
//            对比当前位与后一位的大小关系，从而确定当前位是加还是减法
//            当没有下一位时，做加法即可
//        方式2：也可保留当前位的值（本次采用）
//            当遍历到下一位的时，对比保留值与遍历位的大小关系，再确定保留值为加还是减。最后一位做加法即可。
    public int romanToInt1(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

//  解法2：直接把特殊情况列出来
    public int romanToInt2(String s) {
        s = s.replace("IV","a");
        s = s.replace("IX","b");
        s = s.replace("XL","c");
        s = s.replace("XC","d");
        s = s.replace("CD","e");
        s = s.replace("CM","f");

        int result = 0;
        for (int i=0; i<s.length(); i++) {
            result += which(s.charAt(i));
        }
        return result;
    }

    public int which(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            case 'a': return 4;
            case 'b': return 9;
            case 'c': return 40;
            case 'd': return 90;
            case 'e': return 400;
            case 'f': return 900;
        }
        return 0;
    }

//    解法3：分类讨论，借助Hash表
//    首先将所有的组合可能性列出并添加到哈希表中
//    对字符串进行遍历，由于组合只有两种\
//        一种是 1 个字符，一种是 2 个字符
//        其中 2 个字符优先于 1 个字符
//    先判断两个字符的组合在哈希表中是否存在
//        存在: 将值取出加到结果 ans 中，并向后移2个字符
//        不存在: 将判断当前 1 个字符是否存在，存在则将值取出加到结果 ans 中，并向后移 1 个字符
//    遍历结束返回结果 ans
    public int romanToInt3(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for(int i = 0;i < s.length();) {
            if(i + 1 < s.length() && map.containsKey(s.substring(i, i+2))) {
                ans += map.get(s.substring(i, i+2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i+1));
                i ++;
            }
        }
        return ans;
    }
}

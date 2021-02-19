package Array.Easy;

import java.util.Arrays;

// 给定两个字符串 s1 和 s2
// 请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串
public class D34_isUnique {
    //    解题思路：借助Arrays.sort()
    public boolean CheckPermutation(String s1, String s2) {
        char[] c1=s1.toCharArray();
        Arrays.sort(c1);
        char[] c2=s2.toCharArray();
        Arrays.sort(c2);
        return new String(c1).equals(new String(c2));
    }
}


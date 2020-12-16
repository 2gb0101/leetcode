package Array.Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Offer：给定非负整数 X 的数组形式 A，和一个整数K
//Target：返回整数 X+K 的数组形式
public class D22_989_Add_to_Array_Form_of_Integer {
//    逐位相加法：
//    逐位将数字加在一起
//            举例子
//    如果要计算 123 与 912 的和
//    顺次计算 3+2、2+1、1+9
//    任何时候，当加法的结果大于等于 10
//    我们要将进位的 1 加入下一位的计算中去
//    所以最终结果等于 1035
    public List<Integer> addToArrayForm(int[] A, int K) {
        int N = A.length;
        int cur = K;
        List<Integer> ans = new ArrayList();

        int i = N;
        while (--i >= 0 || cur > 0) {
            if (i >= 0)
                cur += A[i];
            ans.add(cur % 10);
            cur /= 10;
        }

        Collections.reverse(ans);
        return ans;
    }
}

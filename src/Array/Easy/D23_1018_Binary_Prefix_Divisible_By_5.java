package Array.Easy;

import java.util.ArrayList;
import java.util.List;

//Offer：
//给定由若干 0 和 1 组成的数组 A
//定义 N_i 为从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数
//（从最高有效位到最低有效位）
//Target：
//返回布尔值列表 answer
//只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false
public class D23_1018_Binary_Prefix_Divisible_By_5 {
//    首先，想用暴力法依次把每个值求和出来是不行的
//    举例： [1,1,1,0,1]
//    第一步：[1] = 0*2+1 = 1;                //被5取模=1
//    第二步：[1,1] = 1*2+1 = 3;              //被5取模=3
//    第三步：[1,1,1] = 3*2+1 = 7;            //被5取模=2
//    第四步：[1,1,1,0] = 7*2+0 = 14;         //被5取模=4
//    第五步：[1,1,1,0,1] = 14*2+1 = 29;      //被5取模=4
//
//    如果每步计算 2 的幂，结果会越来越大
//    java 没有基本类型可以承受如此大的结果，考虑采用每次结果的模进行计算

//    二进制前缀法：
//    为了降低运算成本，考虑计算可被 5 整除的二进制前缀
//    第一步：[1] = (0*2+1)%5 = 1;            //被5取模=1
//    第二步：[1,1] = (1*2+1)%5 = 3;          //被5取模=3
//		（这一步1*2中的1是第一步的计算结果）
//    第三步：[1,1,1] = (3*2+1)%5 = 2;        //被5取模=2
//		（这一步3*2中的1是第二步的计算结果，这种算法其实相当于：为了加入第三个1，前两个1代表的值得往前移动1位，在二进制中就表现为*2）
//    第四步：[1,1,1,0] = (2*2+0)%5 = 4;      //被5取模=4
//		（这一步2*2中的1是第三步的计算结果）
//    第五步：[1,1,1,0,1] = (4*2+1)%5 = 4;    //被5取模=4
//		（这一步4*2中的1是第四步的计算结果
//			[1,1,1,0]作为[1,1,1,0,1]的前缀
//    所以计算的步骤是4*2+1）
//
//    上面的算法也可以总结成，在二进制中的特点为：
//    每一项都是之前项的两倍+当前项的值。
//    另外，为了防止溢出，每一次的运算都要对5求余。
    public List<Boolean> prefixesDivBy5_1(int[] A) {
        List<Boolean> list = new ArrayList<>(A.length);
        int mod = 0;
        for(int i = 0;i<A.length;i++) {
            //下面这句可以优化：mod = (mod << 1)%5 + A[i] ;
            //因为A[i]本来就是1和0，所以不用判断
            mod = (mod << 1)%5 + (A[i] == 1 ? 1 : 0);
            list.add(mod%5==0);
        }
        return list;
    }

//    末尾计算法：
//    根据题目的特点，我们还可以只考虑十进制最后一位的运算
//    因为能被5整除的数末位只能是0或5，所以我们只需计算最后一位的变化
//        判断最后一位是否为0或5就能得到结果了
//    理论上二进制可以是无限位
    public List<Boolean> prefixesDivBy5_2(int[] A) {
        List<Boolean> res = new ArrayList<Boolean>();
        int tail = 0;
        for(int i: A) {
            tail = tail * 2 + i;
            tail = (tail > 9) ? tail - 10 : tail;
            if(tail == 0 || tail == 5) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

//    利用MOD运算的分配律：
//    这个解法有点像解法一，但是思路是不一样的，注意两个解法MOD 5的时机，是不一样的
//    遍历求值，对于i, mod 保存 A[0...i-1] 所表示数值 MOD5 的结果
//    MOD运算对于加法的分配律：(a+b) MOD c=(a MOD c+ b MOD c) MOD c
    public List<Boolean> prefixesDivBy5_3(int[] A) {
        List<Boolean> answer = new ArrayList<>();
        int temp = 0, mod = 0;
        for (int i = 0; i < A.length; i++) {
            // <<的优先级低于+;MOD运算的分配律
            temp = (mod << 1) + A[i];
            mod = temp % 5;
            answer.add((mod == 0) ? true : false);
        }
        return answer;
    }
}

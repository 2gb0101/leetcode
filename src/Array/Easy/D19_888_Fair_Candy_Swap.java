package Array.Easy;

import java.util.HashSet;
import java.util.Set;

//Offer：
//    爱丽丝和鲍勃有不同大小的糖果棒：
//    A[i] 是爱丽丝拥有的第 i 块糖的大小
//    B[j] 是鲍勃拥有的第 j 块糖的大小
//Target：
//    两人交换一个糖果棒，使交换后两人有相同的糖果总量
//    （一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
//    返回一个整数数组 ans
//    其中 ans[0] 是爱丽丝必须交换的糖果棒的大小
//    ans[1] 是 Bob 必须交换的糖果棒的大小
//    如果有多个答案，可以返回其中任何一个
//    保证答案存在
public class D19_888_Fair_Candy_Swap {
//    若仔细观察题目中给的例子，可以发现
//    所有例子中起始时
//    Alice 和 Bob 两人的糖果总重量的差值一定是偶数
//        因为最终两人的糖果总量时要相同的，那么起始时的重量差就应该能平均分为两部分
//        （能平均分成两部分，说明差值一定能整除2，即为偶数）
//        一部分让轻的一方变重一点
//        一部分让重的一方变轻一点
//        这样最后能使两方一样重

//    这个思路可以写成算式：
//        设爱丽丝和鲍勃分别总计有 SA, SB的糖果。
//        如果爱丽丝给了糖果 x，并且收到了糖果 y
//        那么鲍勃收到糖果 x 并给出糖果 y
//        那么有SA - x + y = SB - y + x
//        则y=x+ (SB-SA)/2
//        有了这个 diff
//        只需要在两个数组中查找差值为 diff 的两个数字了
//        其实本题就是 Two Sum 的变种

//    实现：
//        使用一个 HashSet 先来保存数组 A 中所有的数字
//        遍历数组 B 中的每个数字 num
//        查找 HashSet 中否存在 num+diff 即可
    public int[] fairCandySwap(int[] A, int[] B) {
        int diff = (accumulate(A) - accumulate(B)) / 2;
        /* 我原来是这样写的，也能通过OJ验证
            HashMap<Integer,Integer> st = new HashMap<Integer,Integer>();
            for(int i = 0;i < A.length;i++){
                st.put(A[i],i);
            }
            for (int num : B) {
                if (st.get(num + diff) != null)
                    return new int[]{num + diff, num};
            }
            return new int[]{};
        */

        //下面这种写法是OJ官方的写法
        Set<Integer> setA = new HashSet();
        for (int x: A)
            setA.add(x);
        //因为前面计算差值diff的时候，是用SumA-SumB除二得到的
        //所以这里找差值，一定是在A中找x+diff的数，不然就会出错
        //可以这样理解，因为计算差值diff的时候，假定是SumA > SumB
        //这样就能假设diff是正数
        //那么要在A中找交换数的话，就是找和x相差diff的数
        //由于SumA > SumB，则A中的交换数一定大于x
        //所以A中的交换数是x + diff
        for (int x: B)
            if (setA.contains(x + diff))
                return new int[]{x + diff, x};
        return new int[]{};
    }

    public int accumulate(int[] a){
        int sum = 0;
        for(int tmp : a){
            sum += tmp;
        }
        return sum;
    }
}

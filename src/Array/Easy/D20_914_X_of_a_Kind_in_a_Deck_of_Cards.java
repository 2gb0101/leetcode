package Array.Easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Offer：给一堆卡牌
//Target：
//    问能不能将这副牌分成若干堆
//    每堆均有X个
//    且每堆的牌数字都相同（不考虑花色）
public class D20_914_X_of_a_Kind_in_a_Deck_of_Cards {
//    哈希表法：
//    要将相同的牌归类，肯定要统计每种牌出现的个数
//    使用一个 HashMap，来建立牌跟其出现次数之间的映射

//    由于每堆X个
//    若某张牌的个数小于X，肯定无法分
//    所以X的范围是可以确定的，为 [2, mn]，mn 是数量最少的牌的个数
//    遍历一遍 HashMap，找出【最小】的映射值 mn
//    若 mn 小于2，可以直接返回 false
//    否则，从2遍历到 mn，依次来检验候选值X
//    检验方法
//        看其他每种牌的个数是否能整除候选值X
//        不一定非要相等
//            如 [1, 1, 2, 2, 2, 2]
//            K=2 时就可以分为三堆
//            [1, 1], [2, 2], [2, 2]
//            即相同的牌也可以分到其他堆里
//        一旦有牌数不能整除X了，则当前X一定不行，继续检验下一个X值；
//    若所有牌数都能整除X，可以返回 true
//    循环结束后返回 false
    public boolean hasGroupsSizeX1(int[] deck) {
        java.util.HashMap<Integer, Integer> cardCnt = new HashMap<Integer,Integer>();
        for (int card : deck){
            if(cardCnt.get(card) == null){
                cardCnt.put(card,1);
            }else{
                cardCnt.put(card,cardCnt.get(card)+1);
            }
        }
        //上面利用hashmap来建立映射的方法，其实也可以借助数组
        //因为题目声明了卡牌的数量<= 10000
        //所以可以这样写
        // int[] count = new int[10000];
        // for (int c: deck)
        //     count[c]++;

        int mn = Integer.MAX_VALUE;
        for (Integer tmp : cardCnt.values())
            mn = Math.min(mn, tmp);
        if (mn < 2)
            return false;
        for (int i = 2; i <= mn; ++i) {
            boolean success = true;
            for (Integer tmp : cardCnt.values()) {
                if (tmp % i != 0) {
                    success = false;
                    break;
                }
            }
            if (success)
                return true;
        }
        return false;
    }

//    辅助数组法：
//        之前建立数与数之间的映射，一直是通过HashMap
//        但一直觉得在Java中用HashMap建立映射，语法上略难看
//        看leetcode的官方解法中，对于映射
//        则根据题目的要求，利用了数组和List，也可以参考下
//        思路同哈希表法一致
    public boolean hasGroupsSizeX2_1(int[] deck) {
        //解法一利用hashmap来建立映射的方法，其实也可以借助数组
        int N = deck.length;
        //因为题目声明了卡牌的数量<= 10000
        //所以可以这样写
        int[] count = new int[10000];
        for (int c: deck)
            count[c]++;

        List<Integer> values = new ArrayList();
        for (int i = 0; i < 10000; ++i)
            if (count[i] > 0)
                values.add(count[i]);

        search: for (int X = 2; X <= N; ++X)
            if (N % X == 0) {
                for (int v: values)
                    if (v % X != 0)
                        continue search;
                return true;
            }

        return false;
    }

//    也可以不用List，直接使用数组
//    不过看OJ，好像两者的时间和空间耗时差不多
    public boolean hasGroupsSizeX2_2(int[] deck) {
        int N = deck.length;
        int[] count = new int[10000];
        for (int c: deck)
            count[c]++;

        search: for (int X = 2; X <= N; ++X)
            if (N % X == 0) {
                for (int v: count)
                    if (v % X != 0)
                        continue search;
                return true;
            }

        return false;
    }

//    最大公约数 Greatest Common Divisor：
//    需要记住最大公约函数的写法，或者直接使用内置的 gcd 函数（C++中有）
//    原理
//        找每种牌数之间的最大公约数
//        只要这个 gcd 是大于1的
//        就表示可以找到符合题意的X


//    怎么求最大公约数：查Utils_GCD.java
//    （Java官方也有内置的gcd函数用于求最大公约数）
    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int c: deck)
            count[c]++;

        int g = -1;
        for (int i = 0; i < 10000; ++i)
            if (count[i] > 0) {
                if (g == -1)
                    g = count[i];
                else
                    g = gcd(g, count[i]);
            }

        return g >= 2;
    }

    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y%x, x);
    }

}

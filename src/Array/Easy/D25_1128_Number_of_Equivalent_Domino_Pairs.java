package Array.Easy;

import java.util.Arrays;
import java.util.HashMap;

Offer：
给你一个由一些多米诺骨牌组成的列表dominoes
如果其中某一张多米诺骨牌可以通过旋转 0度或 180 度得到另一张多米诺骨牌
我们就认为这两张牌是等价的
Target：
在0 <= i < j < dominoes.length的前提下
找出满足dominoes[i] 和dominoes[j]等价的骨牌对 (i, j) 的数量
public class D25_1128_Number_of_Equivalent_Domino_Pairs {
    排序统计法：
    for循环遍历
    先排序，消除等价骨牌差异性
            接着统计
    每发现一个A骨牌的等价骨牌
    就给当前对数 贡献了目前A骨牌的个数
    public int numEquivDominoPairs1(int[][] dominoes) {
        int ans = 0;
        int[] cp = new int[100];
        for(int[] arr:dominoes){
            Arrays.sort(arr);
            ans+=cp[arr[0]*10+arr[1]]++;
        }
        return ans;
    }
    双重HashMap法：
    本题对算法复杂度的要求较高，使用暴力的方式用时太大
            利用HashMap的特性来优化算法的复杂度
    改变数字对内两个数字的顺序不会影响最终结果
    所以将所有多米诺骨牌 数值均改成 从小到大的顺序
            一级map的key1为数字对第一位的值,value1为二级map
    二级map的key2为数字对第二位的值,value2为该数字对的出现次数
            当往map中插入数值时
    若已存在(即value2的值为 >=1 的数值)
    便将总数sum加上value2的值
    再将value2的值+1
    若不存在,则创建,并将value2的值初始化为1
    public int numEquivDominoPairs2(int[][] dominoes) {
        //初始化Map集合
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        //定义总数sum，并初始化为0
        int sum = 0;
        //循环遍历二维数组dominoes
        for (int i = 0; i < dominoes.length ; i++)  {
            //获取当前「数字对」的值，并将较小的值设置为a,较大的值设置为b
            Integer a = dominoes[i][0];
            Integer b = dominoes[i][1];
            if (a > b) {
                a = dominoes[i][1];
                b = dominoes[i][0];
            }
            //将a 作为Map1的key1
            //而b 作为Map1的value1 （即map2) 的key2

            //判断Map1中是否有key1 为 a 的值
            if (map.containsKey(a)) {
                HashMap<Integer, Integer> list = map.get(a);
                //判断Map2中是否有key2 为 b 的值
                if (list.containsKey(b)) {
                    //获取value2的值并加入到sum中
                    int count = list.get(b);
                    sum = sum + count;
                    //对应的value的值+1
                    list.put(b, count+1);
                    map.put(a, list);

                } else {
                    //初始化数值为1
                    list.put(b, 1);
                    map.put(a, list);
                }
            } else {
                //初始化数值为1
                HashMap<Integer, Integer> list = new HashMap<>();
                list.put(b, 1);
                map.put(a, list);
            }
        }
        return sum;
    }
}

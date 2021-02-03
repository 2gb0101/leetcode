package Array.Easy;

import java.util.ArrayList;

//Offer：
//    给一个只有0和1且长度为n的数组，代表n个座位
//    定义其中0表示空座位，1表示有人坐
//Target：
//    找个位置坐下，但是希望能离最近的人越远越好
//    求返回他到离他最近的人的最大距离
public class D18_849_Maximize_Distance_to_Closest_Person {
//    根据例子分析可知
//        先找出最大的连续空位长度
//    若发现连续空位靠着墙（示例2），直接挨着墙坐
//    若两边都有人，坐到空位的中间位置
//    如何能快速知道连续空位的长度？
//        只要知道了两边人的位置
//        相减就是中间连续空位的个数

//    辅助数组法（双遍历）：
//    用一个数组来保存所有1的位置，即有人坐的位置
//    用相邻的两个位置相减，就可以得到连续空位的长度
//    靠墙这种特殊情况要另外处理一下
//    当把所有1位置存入数组 nums 之后
//    开始遍历 nums 数组
//        第一个人的位置有可能不靠墙
//            那么他的位置坐标就是他左边靠墙的连续空位个数
//            直接更新结果 res 即可
//            因为靠墙连续空位的个数就是离右边人的最远距离
//        对于其他的位置
//            减去前一个人的位置坐标
//            除以2，更新结果 res（坐在中间）
//        还有最右边靠墙的情况
//            用 n-1 减去最后一个人的位置坐标
//            更新结果 res 即可
    public int maxDistToClosest1(int[] seats) {
        int n = seats.length, res = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) nums.add(i);
        }
        for (int i = 0; i < nums.size(); ++i) {
            if (i == 0)
                res = Math.max(res, nums.get(0));
            else
                res = Math.max(res, (nums.get(i) - nums.get(i - 1)) / 2);
        }
        if (nums.size() != 0) res = Math.max(res, n - 1 - nums.get(nums.size() - 1));
        return res;
    }

//    单遍历：
//    需要在遍历过程中统计出连续空位的个数，即连续0的个数
//    可采用双指针来做
//    start 指向连续0的起点，初始化为0，i为当前遍历到的位置
//    遍历 seats 数组
//    跳过0的位置，当遇到1的时候
//    此时先判断下 start 的值
//        若是0的话
//            表明当前这段连续的空位是靠着墙的
//            要用连续空位的长度 i-start 来直接更新结果 res
//        否则的话
//            就是两头有人的中间的空位
//            用长度加1除以2来更新结果 res
//            此时 start 要更新为 i+1，指向下一段连续空位的起始位置
//    循环退出后，还要处理最右边靠墙的位置
//    用 n-start 来更新结果 res 即可
    public int maxDistToClosest2(int[] seats) {
        int n = seats.length, start = 0, res = 0;
        for (int i = 0; i < n; ++i) {
            if (seats[i] != 1) continue;
            if (start == 0)
                res = Math.max(res, i - start);
            else
                res = Math.max(res, (i - start + 1) / 2);
                start = i + 1;
        }
        res = Math.max(res, n - start);
        return res;
    }
}

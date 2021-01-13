package Array.Easy;

//Offer：一些有序数组
//Target：
//    从不同的数组中各取一个数字
//    使得这两个数字的差的绝对值最大
//    求最大值
public class D13_624_MaximumDistanceinArrays {
//    思路：
//    数组都是有序的
//        那么差的绝对值最大的两个数字肯定是分别位于数组的首和尾
//    注意题目中说要从不同的数组中取数
//    那么即使某个数组的首尾差距很大，也不行

//    使用堆：
//    建立最大堆和最小堆
//        最大堆存每个数组的尾元素
//        最小堆存每个数组的首元素
//    实现：
//    由于最大的数字和最小的数字有可能来自于同一个数组
//    在堆中存数字时，还要存入当前数字所在的数组的序号
//    分别在最大堆和最小堆中取数字
//    判断两个数字是否在一个数组
//    是
//        比较以下两者，返回较大值
//            第二大数字和最小数字绝对差
//            最大数字和第二小数字绝对差
//    否
//        直接返回二者的绝对差即可
//    public:
//    int maxDistance(vector<vector<int>>& arrays) {
//        priority_queue<pair<int, int>> mx, mn;
//        for (int i = 0; i < arrays.size(); ++i) {
//            mn.push({-arrays[i][0], i});
//            mx.push({arrays[i].back(), i});
//        }
//        auto a1 = mx.top(); mx.pop();
//        auto b1 = mn.top(); mn.pop();
//        if (a1.second != b1.second) return a1.first + b1.first;
//        return max(a1.first + mn.top().first, mx.top().first + b1.first);
//    }
//    };

//    辅助变量法：
//    创建两个变量
//        start表示当前遍历过的数组中最小的首元素
//    end表示最大的尾元素
//        遍历数组
    public static void main(String[] args){
            int[][] arrays = new int[][] {{1,2,3},
            {4,5},
            {1,2,3}};
            System.out.println("res = " + maxDistance(arrays));
    }

    static int maxDistance(int[][] arrays) {
        int res = 0, length = arrays[0].length,start = arrays[0][0], end = arrays[0][length - 1];
        for (int i = 1; i < arrays.length; ++i) {
            length = arrays[i].length;
            res = Math.max(res, Math.max(Math.abs(arrays[i][length - 1] - start), Math.abs(end - arrays[i][0])));
            start = Math.min(start, arrays[i][0]);
            end = Math.max(end, arrays[i][length - 1]);
        }
        return res;
    }
}

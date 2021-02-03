package Array.Easy;

//Offer：
//    平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]
//    你可以按照下面的规则在平面上移动：
//        每一秒沿水平或者竖直方向移动一个单位长度
//        或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）
//    必须按照数组中出现的顺序来访问这些点
//Target：请你计算访问所有这些点需要的最小时间（以秒为单位）
public class D28_1266_Minimum_Time_Visiting_All_Points {
//    切比雪夫距离:
//    对于平面上的两个点 x = (x0, x1) 和 y = (y0, y1)
//    设它们横坐标距离之差为 dx = |x0 - y0|
//    纵坐标距离之差为 dy = |x1 - y1|
//    对于以下三种情况，可以分别计算出从 x 移动到 y 的最少次数：
//    (这里的计算基于题目说明：跨过对角线可以看作在一秒内向水平和竖直方向各移动一个单位长度)
//        dx < dy：
//            沿对角线移动 dx 次
//            再竖直移动 dy - dx 次
//            总计 dx + (dy - dx) = dy 次
//        dx == dy：
//            沿对角线移动 dx 次
//        dx > dy：
//            沿对角线移动 dy 次
//            再水平移动 dx - dy 次
//            总计 dy + (dx - dy) = dx 次

//    可以发现
//        对于任意一种情况，从 x 移动到 y 的最少次数为dx 和 dy 中的较大值 max(dx, dy)
//        这也被称作 x 和 y 之间的 切比雪夫距离

//    由于题目要求，需要按照数组中出现的顺序来访问这些点
//    因此遍历整个数组
//    对于数组中的相邻两个点，计算出它们的切比雪夫距离
//    所有的距离之和即为答案
    public int minTimeToVisitAllPoints(int[][] points) {
        int x0 = points[0][0], x1 = points[0][1];
        int ans = 0;
        for (int i = 1; i < points.length; ++i) {
            int y0 = points[i][0], y1 = points[i][1];
            ans += Math.max(Math.abs(x0 - y0), Math.abs(x1 - y1));
            x0 = y0;
            x1 = y1;
        }
        return ans;
    }
}

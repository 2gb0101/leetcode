package Array.Easy;

//Offer：
//在一个XY 坐标系中有一些点，用数组coordinates来分别记录它们的坐标
//其中coordinates[i] = [x, y]表示横坐标为 x、纵坐标为 y的点。
//Target：
//请你来判断，这些点是否在该坐标系中属于同一条直线上
//是则返回 true，否则请返回 false

//-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
public class D27_1232_Check_If_It_Is_a_Straight_Line {
//    斜率法：求斜率
//    除法会有浮点精度问题，使用乘法替代
//    直线斜率
//        k = (y1 - y0) / (x1 - x0) = (yi - y0) / (xi - x0)
//    转化为乘法
//        (y1 - y0) * (xi - x0) = (yi - y0) * (x1 - x0)
//    但是斜率可能无限大（本题是因为有题目限制，所以不存在斜率无限大的情况）
//    补充：
//        直线垂直于x轴：斜率不存在
//        直线平行于x轴：斜率为0
    public boolean checkStraightLine1(int[][] coordinates) {
        int k1 = coordinates[1][0] - coordinates[0][0];
        int k2 = coordinates[1][1] - coordinates[0][1];
        for(int i = 1; i < coordinates.length - 1; i++) {
            if((coordinates[i + 1][0] - coordinates[i][0]) * k2 != (coordinates[i + 1][1] - coordinates[i][1]) * k1) {
                return false;
            }
        }
        return true;
    }

//    ToLeftTest算法：
//    参考计算几何中，ToLeftTest算法
//    用其检测三点是否共线
    public boolean checkStraightLine2(int[][] coordinates) {
        if (coordinates.length <= 2) return true;
        for (int i = 2; i < coordinates.length; ++i) {
            int a1 = coordinates[i - 2][0];
            int b1 = coordinates[i - 2][1];
            int a2 = coordinates[i - 1][0];
            int b2 = coordinates[i - 1][1];
            int a3 = coordinates[i][0];
            int b3 = coordinates[i][1];

            // toleft test
            if (a1 * b2 - a2 * b1 + a2 * b3 - a3 * b2 + a3 * b1 - a1 * b3 != 0)
                return false;
        }
        return true;
    }
}

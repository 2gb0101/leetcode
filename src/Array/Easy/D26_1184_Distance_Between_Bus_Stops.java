package Array.Easy;

//Offer：
//环形公交路线上有n个站，按次序从0到n - 1进行编号
//已知每一对相邻公交站之间的距离
//distance[i]表示编号为i的车站和编号为(i + 1) % n的车站之间的距离
//环线上的公交车都可以按顺时针和逆时针的方向行驶
//
//Target：
//返回乘客从出发点 start 到目的地 destination 之间的最短距离

public class D26_1184_Distance_Between_Bus_Stops {
//    让小数为起始点，大数为结束点
//    进行遍历
//    在起始点和结束点之间的存为d1，即顺时针
//    剩下的存为d2，即逆时针
//    之后比较两个数
//    留下较小的数返回就可以了
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int d1 = 0;
        int d2 = 0;
        if (start > destination) {
            int n = destination;
            destination = start;
            start = n;
        }
        for (int i = 0; i < distance.length; i++) {
            if (i >= start && i < destination) {
                d1 += distance[i];
            } else {
                d2 += distance[i];
            }
        }
        int value = 0;
        if (d1 < d2) {
            value = d1;
        } else {
            value = d2;
        }
        return value;
    }
}

package Array.Easy;

import java.util.ArrayList;
import java.util.List;

//Offer：
//    一个只含0和1的数组，和整数n
//    1表示已经放了花
//    0表示可以放花的位置
//    限制:花不能相邻
//Target：判断是否能在该数组中放n盆花
public class D13_605_CanPlaceFlowers {
//    思路：先举例分析
//    如果是000，能放几盆花
//        要取决约左右的位置的
//        如果是10001——只能放1盆,10101
//        如果是00000——能放两盆，01010
//    则，若想通过计算连续0的个数，直接算出能放花的个数，必须要对边界进行处理


//    实现：
//    首位添值法：
//    如果首位置是0，那么前面再加上个0
//        （意义是，如果首位是0，那么当第二位是0时，首位可放花）
//    如果末位置是0，就在最后面再加上个0
//        （同上）
//    这样处理之后，就默认连续0的左右两边都是1了
//    这样如果有k个连续0，那么就可以通过(k-1)/2来快速计算出能放的花的数量
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        if (flowerbed.length == 0) return false;
        List<Integer> list = new ArrayList<Integer>(flowerbed.length);
        for (Integer s : flowerbed) {
            list.add(s);
        }
        if (flowerbed[0] == 0){
            list.add(0,0);
        }
        if (flowerbed[flowerbed.length - 1] == 0){
            list.add(list.size(),0);
        }
        int len = list.size(), cnt = 0, sum = 0;
        for (int i = 0; i <= len; ++i) {
            if (i < len && list.get(i).equals(0)) ++cnt;
            else {
                sum += (cnt - 1) / 2;
                cnt = 0;
            }
        }
        return sum >= n;
    }

//    暴力法一：直接通过修改flowerbed的值来做
//    遍历花床
//    如果某个位置为0
//        看其前面一个和后面一个位置的值
//        注意处理首位置和末位置的情况
//        如果pre和next均为0，那么说明当前位置可以放花
//        修改flowerbed的值，且n自减1
//    最后看n是否小于等于0
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; ++i) {
            if (n == 0) return true;
            if (flowerbed[i] == 0) {
                int next = (i == flowerbed.length - 1 ? 0 : flowerbed[i + 1]);
                int pre = (i == 0 ? 0 : flowerbed[i - 1]);
                if (next + pre == 0) {
                    flowerbed[i] = 1;
                    --n;
                }
            }
        }
        return n <= 0;
    }

//    暴力法二：
//    为了不用特殊处理首末位置
//    直接先在首尾各加了一个0
//    三个三个的来遍历
    public boolean canPlaceFlowers3(int[] flowerbed, int n) {
        List<Integer> list = new ArrayList<Integer>(flowerbed.length);
        for (Integer s : flowerbed) {
            list.add(s);
        }
        //先在首尾各加了一个0
        list.add(0,0);
        list.add(list.size(),0);
        for (int i = 1; i < list.size() - 1; ++i) {
            if (n == 0) return true;
            if (list.get(i - 1) + list.get(i) + list.get(i + 1) == 0) {
                --n;
                ++i;
            }
        }
        return n <= 0;
    }
}

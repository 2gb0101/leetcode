package Array.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

//实现一个 MajorityChecker 的类，它应该具有下述几个 API：
//构造函数(int[] arr) ：
//      用给定的数组 arr 来构造一个 MajorityChecker 的实例。
//int query(int left, int right, int threshold)
//      0 <= left <= right < arr.length 表示数组 arr 的子数组的长度。
//      2 * threshold > right - left + 1，也就是说阈值 threshold 始终比子序列长度的一半还要大。
//每次查询 query(...) 会返回在 arr[left], arr[left+1], ..., arr[right] 中至少出现阈值次数 threshold 的元素，如果不存在这样的元素，就返回 -1。
public class D9_1157_MajorityChecker {
//    解法：HashMap
//    map的key是元素，value是一个ArrayList，里面存的元素的index
//    执行query方法时，遍历map的keySet，对每一个元素的list进行遍历
//    list.get(p1)是大于等于left的下标
//    list.get(p2)是小于等于right的下标
//    p2-p1+1就是该元素在这个区间内出现的次数
//    如果大于等于threshold，则返回该元素的值
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public D9_1157_MajorityChecker(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            ArrayList<Integer> list = map.get(arr[i]);
            if (list == null) {
                list = new ArrayList<Integer>();
            }
            list.add(i);
            map.put(arr[i], list);
        }
    }

    public int query(int left, int right, int threshold) {
        Set<Integer> set = map.keySet();
        for (Integer a : set) {
            ArrayList<Integer> list = map.get(a);
            //注意，这里的p1和p2代表的是list里面的下标
            int p1 = 0;
            int p2 = list.size() - 1;
            while (p1 < list.size() && list.get(p1) < left) p1++;
            while (p2 >= 0 && list.get(p2) > right) p2--;
            //p2 - p1 + 1代表的是List中符合条件的下标个数
            if (p2 - p1 + 1 >= threshold) {
                return a;
            }
        }
        return -1;
    }
}

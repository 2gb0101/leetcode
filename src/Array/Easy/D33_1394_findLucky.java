package Array.Easy;

import java.util.HashMap;
import java.util.Map;

//在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」
//Offer: 一个整数数组 arr
//Target: 从中找出并返回一个幸运数
//      如果数组中存在多个幸运数，只需返回 最大 的那个
//      如果数组中不含幸运数，则返回 -1
public class D33_1394_findLucky {
//    解法1：桶排法
//    提示信息很重要：
//        1 <= arr.length <= 500
//        1 <= arr[i] <= 500
    public int findLucky1(int[] arr) {
        int[] count = new int[510];
        for(int a: arr) {
            count[a] ++;
        }
        for(int i = count.length -1; i > 0; i --) {
            if(i == count[i]) {
                return i;
            }
        }
        return -1;
    }


//    解法2:使用HashMap
//    由题目对幸运数字的定义，我们需要先统计每一个元素出现的次数
//    这很显然使用HashMap就可以：键为元素，值为该元素出现的次数
//    统计某个元素的出现次数，如果该元素的值与出现次数相同，他就是一个幸运数字
//    对map中的键进行遍历。题目要求返回最大的幸运数组，所以当遇到幸运数字时，需要进行更新res = Math.max(res, key);
//    注意，没有幸运数的时候返回-1，所以初始状态的res = -1
//    在循环中没有找到幸运数，将其返回时才不会出现错误
//    时间复杂度为O(n)，空间复杂度为O(n)
    public int findLucky2(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : arr)
            map.put(i, map.getOrDefault(i, 0) + 1);
        int res = -1;
        for(int key : map.keySet()){
            if(key == map.get(key))
                res = Math.max(res, key);
        }
        return res;
    }

}

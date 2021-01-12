package Array.Middle;

import org.junit.Test;

import java.util.Arrays;

//给出一个区间的集合，请合并所有重叠的区间
public class D7_56_merge {
    @Test
    public void test0(){
        Integer[] a = new Integer[]{3,4,2};
        Arrays.sort(a, (v1, v2) -> v1 - v2);
        System.out.println(Arrays.toString(a)); //结果为：[2, 3, 4]
    }

//    解法1：排序法
//    合并2个区间：2 个区间的关系有 6 种
//    合并 n 个区间：先根据区间的起始位置排序，再进行 n−1 次 两两合并
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }

//    补充：Arrays.copyOf() 用法
    public void test() {
        int[] arr1 = {1, 2, 3, 4, 5};
        // Arrays的copyOf()：传回的数组是新的数组对象，改变传回数组中的元素值，不会影响原来的数组
        // copyOf()的第二个自变量指定要建立的新数组长度
        int[] arr2 = Arrays.copyOf(arr1, 5);  // 结果为：1 2 3 4 5
        int[] arr3 = Arrays.copyOf(arr1, 10); // 结果为：1 2 3 4 5 0 0 0 0 0
    }
}


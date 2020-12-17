package Array.Easy;

//Offer：一群学生
//Target：
//要求学生按照 非递减 的高度顺序排列
//请返回至少有多少个学生没有站在正确位置数量
//该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数

//1 <= heights.length <= 100
//1 <= heights[i] <= 100
public class D24_1051_Height_Checker {
//    非递减 排序也就是升序排列，最直观的一种解法就是排序后对比计数每个位置的不同数量。
//    但是涉及到比较排序，时间复杂度最低也有 O(NlogN)
//
//    我们真的需要排序吗？
//
//    首先我们其实并不关心排序后得到的结果，我们想知道的只是在该位置上，与最小的值是否一致
//    题目中已经明确了值的范围 1 <= heights[i] <= 100
//    这是一个在固定范围内的输入，比如输入： [1,1,4,2,1,3]
//    输入中有 3 个 1,1 个 2，1 个 3 和 1 个 4，3 个 1 肯定会在前面，依次类推
//    所以，我们需要的仅仅只是计数而已
//
//    时间复杂度：O(n)
//        计数过程为 O(n)
//        比较过程外层循环次数固定为 100
//        里层循环一共也只会执行 n 次
//        O(100*n)，即 O(n)
//    空间复杂度：O(1)
//        其中一个固定长度的计数数组与一个统计变量
//        与输入 N 的大小无关

    public int heightChecker(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (heights[j++] != i) count++;
            }
        }
        return count;
    }
}
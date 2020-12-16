package Array.Easy;

//Offer：一个按非递减顺序排序的整数数组 A
//Target：返回每个数字的平方组成的新数组，要求也按非递减顺序排序

import java.util.Arrays;

public class D21_977_Squares_of_a_Sorted_Array {
//    排序法：
//    创建一个新的数组
//        它每个元素是给定数组对应位置元素的平方
//    然后排序这个数组
//
//    时间复杂度：O(NlogN)，其中 N 是数组 A 的长度
//    （目前已知的最快比较排序算法：快速排序时间复杂度是O(NlogN)，其他的像归并排序也是，但是快速排序实际应用上最快）
//    空间复杂度：O(N)
    public int[] sortedSquares1(int[] A) {
        int N = A.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; ++i)
            ans[i] = A[i] * A[i];

        Arrays.sort(ans);
        return ans;
    }
//    双指针法：
//    因为数组 A 已经排好序了，数组中的 负数、非负数 已经按照平方值升序排好了
//    策略：
//    从前向后遍历数组中的非负数部分
//    且反向遍历数组中的负数部分

//    时间复杂度：O(N)，其中 N 是数组 A 的长度
//    空间复杂度：O(N)
    public int[] sortedSquares2(int[] A) {
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0)
            j++;
        int i = j-1;

        int[] ans = new int[N];
        int t = 0;

        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }

        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;
    }
}

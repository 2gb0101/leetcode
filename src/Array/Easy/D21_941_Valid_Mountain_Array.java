package Array.Easy;

//Offer：一个整数数组
//Target：判断给定数组是否是山脉数组
public class D21_941_Valid_Mountain_Array {
//    线性扫描法：
//    从数组的最左侧开始扫描
//        直到找到第一个不满足 A[i] < A[i + 1] 的 i
//        那么 i 就是这个数组的最高点
//        如果 i = 0 或者不存在这样的 i
//        说明整个数组都是单调递增的
//        就返回 false
//    接着从 i 开始继续扫描
//        判断接下来的的位置 j 是否都满足 A[j] > A[j + 1]
//        若都满足就返回 true
//        否则返回 false

//    复杂度分析
//    时间复杂度：O(N)，其中 N 是数组 A 的长度
//    空间复杂度：O(1)
    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // walk up
        while (i+1 < N && A[i] < A[i+1])
            i++;

        // peak can't be first or last
        if (i == 0 || i == N-1)
            return false;

        // walk down
        while (i+1 < N && A[i] > A[i+1])
            i++;

        return i == N-1;
    }
}



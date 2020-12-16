package Array.Easy;

//Offer：一个整数数组A、一个查询数组queries
//Target：
//对于第i次查询，有
//val =queries[i][0]
//index= queries[i][1]
//会把val加到A[index]上
//第i次查询的答案是 A 中偶数值的和
//（此处给定的index = queries[i][1]是从 0 开始的索引,每次查询都会永久修改数组A）
//返回所有查询的答案
//答案应当以数组answer给出，answer[i]为第i次查询的答案

//Example 1:
//Input: A = [1,2,3,4], queries = [[1,0],[-3,1],[-4,0],[2,3]]
//Output: [8,6,2,4]
//Explanation:
//At the beginning, the array is [1,2,3,4].
//After adding 1 to A[0], the array is [2,2,3,4], and the sum of even values is 2 + 2 + 4 = 8.
//...
public class D21_985_Sum_of_Even_Numbers_After_Queries {
//    调整数组和法：
//    尝试不断调整 S，即每一步操作之后整个数组的偶数和。
//    操作数组中的某一个元素 A[index] 的时候
//    数组 A 其他位置的元素都应保持不变
//    如果 A[index] 是偶数
//    就从 S 中减去它
//    计算 A[index] + val 对 S 的影响（如果是偶数则在 S 中加上它）
//    例子：
//    A = [2,2,2,2,2]、S = 10时，同时需要执行 A[0] += 4 操作
//    先令 S -= 2
//    然后令 S += 6
//    最后得到 A = [6,2,2,2,2] 与 S = 14
//
//    A = [1,2,2,2,2]、S = 8时，同时需要执行 A[0] += 3 操作
//    会跳过第一次更新 S 的步骤（因为 A[0] 是奇数）
//    然后令 S += 4
//    最后得到 A = [4,2,2,2,2] 与 S = 12
//
//   ....
//
//    复杂度分析：
//    时间复杂度：O(N+Q)，其中 N 是数组 A 的长度， Q 是询问 queries 的数量。
//    空间复杂度：O(N+Q)，事实上只使用了 O(1)的额外空间。
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int S = 0;
        for (int x: A)
            if (x % 2 == 0)
                S += x;

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; ++i) {
            int val = queries[i][0], index = queries[i][1];
            if (A[index] % 2 == 0) S -= A[index];
            A[index] += val;
            if (A[index] % 2 == 0) S += A[index];
            ans[i] = S;
        }

        return ans;
    }
}

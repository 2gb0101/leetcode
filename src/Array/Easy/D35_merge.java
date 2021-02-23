package Array.Easy;

//Offer: 两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B
//Target: 编写一个方法，将 B 合并入 A 并排序
//初始化 A 和 B 的元素数量分别为 m 和 n
public class D35_merge {
//    解题思路1
//    需要比较 m + n个数的大小，所以先保证其中至少 m 或 n 个数比较完毕并放在正确位置，再考虑剩下的数
//    注意 m 和 n 是未遍历的、有效的数字的个数，不是下标，作为下标要 −1
//    写的时候偷懒，可能有些地方可读性比较差
    public void merge1(int[] A, int m, int[] B, int n) {
        // 先确保将其中一个数组中的数字遍历完
        while (m > 0 && n > 0) {
            // 对比选出较大的数放在 m + n - 1 的位置，并将选出此数的指针向前移动
            A[m + n - 1] = A[m - 1] > B[n - 1] ? A[m-- - 1] : B[n-- - 1];
        }
        // 剩下的数都比已经遍历过的数小
        // 如果 m 不为 0，则 A 没遍历完，都已经在 A 中不用再管
        // 如果 n 不为 0，则 B 没遍历完，直接全移到 A 中相同的位置
        while (n > 0) {
            A[n - 1] = B[n - 1];
            n--;
        }
    }

//    解题思路2
//    灵感来自插入排序，将数组B中的每个数插入到A中
//    取B的每一个数与A中的数比较
//    从A的最后一个数开始比较，如果B中的数小于A中的数，则将A的数后移给B留出一个插入位置
//    直到满足B的数大于A的数，就可以直接插入到当前位置以此类推，将B中所有数插入到A中
    public void merge2(int[] A, int m, int[] B, int n) {
        for(int i = 0; i < n; i++){
            int b = B[i];
            int j = m - 1 + i;

            while(j >= 0 && b <= A[j]){
                A[j+1] = A[j];
                j--;
            }

            A[j+1] = b;
        }
    }


//    解题思路3
//    先将A数组中的m个元素移动到末尾；
//    然后利用归并排序的merge思想，每次取出A和B数组头元素中的最小值逐个放到A数组中
//    如果不理解，就看笔记【面试题 10.01. 合并排序的数组】
    public void merge3(int[] A, int m, int[] B, int n) {
        // 先将A右移到末尾
        System.arraycopy(A, 0, A, n, m);

        int index = 0;
        int indexA, indexB;
        for (indexA = n, indexB = 0; indexA < m + n && indexB < n;) {
            if (A[indexA] <= B[indexB]) {
                A[index++] = A[indexA++];
            } else {
                A[index++] = B[indexB++];
            }
        }

        while (indexA < m + n) {
            A[index++] = A[indexA++];
        }

        while (indexB < n) {
            A[index++] = B[indexB++];
        }
    }
//    复杂度分析
//    时间复杂度：O(m + n)
//    空间复杂度：O(1)
}

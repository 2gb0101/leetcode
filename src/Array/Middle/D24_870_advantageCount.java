package Array.Middle;

import java.util.Arrays;

//Offer: 两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
//Target: 返回 A 的任意排列，使其相对于 B 的优势最大化。
public class D24_870_advantageCount {
//    本题核心思想：田忌赛马

//    解法1：多指针+辅助类
//    先对A、B数组排序，l1、l2指向数组第一个值，r1、r2指向数组最后一个值，然后开始比较它们的值。
//
//    当A[r1] > B[r2]时，这两个值匹配，因为此时A[r1]不仅能击败B[r2]还能把B中最大的值消耗掉。
//
//    当A[r1] < B[r2]时，因为A中的值都无法击败B[r2],索性就把最小的值A[l1]跟B[r2]匹配好了，反正不能赢，减少损失。
//
//    当A[r1] == B[r2]时，分两种情况:
//
//    ①当A[l1] > B[l2]时，这两个值匹配。
//    ②否则，A[l1] 与 B[r2] 匹配
//        因为当A[l1] < B[l2]时，A[l1]必输，所以A[l1]跟最大值B[r2]匹配
//        当A[l1] == B[l2]时，A[l1]还是无法赢，跟B[r2]匹配之后
//        A[r1]可以跟后面比较小的数字匹配获得胜利

    class Temp implements Comparable<Temp> {
        int num, index; //存储B数组的值和下标
        public Temp(int num, int index) {
            this.index = index;
            this.num = num;
        }

        @Override
        //得到的排序结果是【升序】
        public int compareTo(Temp o) {
            return this.num - o.num;
        }
    }

    public int[] advantageCount1(int[] A, int[] B) {
//        因为B数组排序之后打乱了之前的顺序，所以要存储B排序之前的顺序
//        这里使用Temp保存B排序之前的顺序
        Temp[] arr = new Temp[B.length];
        for (int i = 0; i < B.length; ++i) {
            arr[i] = new Temp(B[i], i);
        }
        Arrays.sort(A);
        Arrays.sort(arr);
        int l1 = 0, r1 = A.length - 1;
        int l2 = 0, r2 = B.length - 1;
        //B数组的值都保存在arr了，现在的B可以存放答案。
        while (l1 <= r1) {
            if (A[r1] > arr[r2].num) {
                B[arr[r2].index] = A[r1];
                --r1; --r2;
            } else if (A[r1] < arr[r2].num) {
                B[arr[r2].index] = A[l1];
                --r2; ++l1;
            } else {
                if (A[l1] > arr[l2].num) {
                    B[arr[l2].index] = A[l1];
                    ++l1; ++l2;
                } else {
                    B[arr[r2].index] = A[l1];
                    --r2; ++l1;
                }
            }
        }
        return B;
    }

//    解法2：二分法、标记数组
//    先将A排序，然后遍历B
//    1、在A中找到第一个大于B[i]的数，这里可以用二分法；
//    2、找到第一个大于B的数之后，还要判断它是否被使用过，这里用了一个boolean数组来标记被使用过的元素
//    3、二分法的返回值是第一个大于B[i]且没有被使用过的数字。如果找不到，返回-1
//    4、如果二分法找不到满足条件的值，则在A中找一个最小的且没有被使用过的数字
    boolean[] flag;
    public int[] advantageCount2(int[] A, int[] B) {
        //田忌赛马
        int n = A.length;
        Arrays.sort(A);
        int[] res = new int[n];
        flag = new boolean[n];
        int idx = 0;
        int cur = 0;
        for(int i = 0; i < n; i++) {
            int[] tmp = firstBigger(A, B[i]);
            if(tmp[1] == -1) {
                while(flag[idx])
                    idx++;
                res[cur++] = A[idx];
                flag[idx++] = true;
            } else {
                res[cur++] = tmp[0];
                flag[tmp[1]] = true;
            }
        }
        return res;
    }

    private int[] firstBigger(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            //题意：找第一个大于目标的数；相反：小于等于
            if(nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if(nums[left] <= target)
            return new int[] {-1, -1};
        else {
            // 确定该left指向的数还没有被使用过
            // 如果被使用过（flag[left]=true），就利用while循环将指针往后移
            while(left < nums.length && flag[left])
                left++;
            if(left == nums.length)
                return new int[] {-1, -1};
            return new int[] {nums[left], left};
        }
    }
}

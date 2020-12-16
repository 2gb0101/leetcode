package Array.Hard;

//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2
//请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))
//nums1 和 nums2 不会同时为空
public class _4_findMedianSortedArrays {

//  思路①：先合并，再求中位数
//  解法1：暴力法
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {
                return nums2[n / 2];
            }
        }
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }

        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }

//  解法2：改用指针对解法1进行改进
//  我们不需要将两个数组真的合并，只需要找到中位数在哪里就可以了
    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        //left表示上次遍历的结果，right表示本次遍历的结果
        //最终left=len/2，right=len/2+1或者len/2
        int aStart = 0, bStart = 0; //分别表示当前指向 A 数组和 B 数组的位置
//      首先思考怎么将数组长度为奇数和偶数的情况合并，用 len 表示合并后数组的长度
//      如果是奇数，我们需要知道第 （len+1）/2 个数就可以了，如果遍历的话需要遍历 int(len/2 ) + 1 次
//      如果是偶数，我们需要知道第 len/2和 len/2+1 个数，也是需要遍历 len/2+1 次
//      所以遍历的话，奇数和偶数都是 len/2+1 次。
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++]; //此时A[aStart]比较小
            } else {
                right = B[bStart++]; //此时B[bStart]比较小
            }
        }
        if ((len & 1) == 0) //判断长度是否为偶数
            //长度为偶数
            //取第 len/2和 len/2+1 两者的平均数
            return (left + right) / 2.0;
        else
            //长度为奇数
            //取第 len/2+1 个数
            return right;
    }

//    思路②：
//    解法1、2的时间复杂度都达不到题目的要求 O(log(m+n)
//    看到 log 自然会想到用到二分法
//    换思路：题目是求中位数，其实就是求第 k 小数的一种特殊情况

//    解法3：二分法
//    解法二中，我们一次遍历就相当于去掉不可能是中位数的一个值，也就是一个一个排除
//    由于数列是有序的，其实我们完全可以一半儿一半儿的排除
//    假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数
//    给个规律： A[1] ，A[2] ，A[3]，A[k/2] ... ，B[1]，B[2]，B[3]，B[k/2] ...
//    如果 A[k/2]<B[k/2] ，那么A[1]，A[2]，A[3]，A[k/2]都不可能是第 k 小的数字
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        //本方法的最后一个参数：即我要查找的第K个数
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

//  解法三虽然用到了递归，但是属于尾递归，所以编译器不需要不停地堆栈
//  再理解一下中位数：
//  中位数把数组分成两部分，根据当A数组和B数组的总长度，又分两种情况
//  是偶数时：
//      中位数=（左半部分最大值 + 右半部分最小值）/ 2
//      即(max(A[i-1],B[j-1]) + min(A[i],B[j]))/2
//      此时还得保证max(A[i-1],B[j-1])小于min(A[i],B[j])
//      只要讨论B[j-1] <= A[i]，和A[i-1] <= B[j]即可
//      怎么保证？——画图分类讨论，找到i和j的位置，此时可以用二分法找
//          初始化 i 为中间的值，然后减半找中间的，减半找中间的，减半找中间的直到答案
//  是奇数时：
//      中位数=左半部分最大值（前提是，我们假定左半部分比右半部分大1）
//      即max(A[i-1],B[j-1])

    public double findMedianSortedArrays3(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) {
            return findMedianSortedArrays(B,A); // 保证 m <= n
        }
        int iMin = 0, iMax = m;
        //现在用二分法，找合适的i的位置，所以二分法初始化时，iMin设为0，iMax设为m
        while (iMin <= iMax) {
            //在划分i、j的时候，保证左半部分比右半部分长1
            //即i+j=(m-i)+(n-j)+1
            //最后得到j = (m + n + 1) / 2 - i
            //补充：如果左右部分相等，则有j = (m + n) / 2 - i
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && B[j-1] > A[i]){ // i 需要增大
                iMin = i + 1;
            } else if (i != 0 && j != n && A[i-1] > B[j]) { // i 需要减小
                iMax = i - 1;
            } else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; } // 奇数的话不需要考虑右半部分

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
            }
        }
        return 0.0;
    }
}

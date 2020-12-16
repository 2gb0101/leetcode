package Array.Easy;

//Offer：
//    两个有序数组
//Target：
//    把两个数组合并，并把结果放到数组1中
//思路：
//混合插入有序数组，由于两个数组都是有序的，所以只要按顺序比较大小即可。
//题目中说：nums1 数组有足够大的空间，说明不用 resize 数组
//从 nums1 和 nums2 数组的末尾开始一个一个比较，把较大的数，按顺序从后往前加入混合之后的数组末尾
public class D4_88_MergeSortedArray {
//  解法一（两层循环）
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j])
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }
        while (j >= 0)
            nums1[k--] = nums2[j--];
    }

//    解法二（单层循环）
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            nums1[k--] = (i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }
    }
}

package Array.Easy;

//在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i
//Offer: 一个有序整数数组
//Target: 编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1
//        若有多个魔术索引，返回索引值最小的一个
public class D35_findMagicIndex {
//    解题思路：使用二分+递归
//    此题目乍一看一个for循环就解决啊
//    再仔细瞧一眼，有序数组，到底是升序还是降序呢？
//    数组有序上二分，立马设置left = 0， right = nums.length - 1
//    如果mid刚好等于nums[mid]，那就往左边搜索（求最小的魔法索引）
//    如果mid不等于nums[mid]的话
//        那这个魔法索引可能是在左边，也可能是在右边
//        所以你不能光搜索某一边的数字（许多解答通过测试，却有漏洞大概就是这个原因）
//    既然要重复搜索各个部分的话，上递归吧(因为可能有多个魔术索引，所以要搜索各个部分)
    int ans = -1;
    public int findMagicIndex(int[] nums) {
        search(nums, 0, nums.length - 1);
        return ans;
    }

    private void search(int[] nums, int left, int right){
        if(left > right) return;

        int mid = (left + right) / 2;

        if(nums[mid] == mid){
            ans = mid;
            search(nums, left, mid - 1);
        }
        else {
            search(nums, left, mid - 1);
            if(ans == -1) search(nums, mid + 1, right);
        }
    }
}

package Array.Easy;

import java.util.Arrays;

//Offer：一个数组
//Target：求最短的无序连续子数组的长度，当对该子数组排序后，整个数组都会有序
//Input: [2, 6, 4, 8, 10, 9, 15]
//Output: 5
//Explanation: [6, 4, 8, 10, 9]
public class D13_581_ShortestUnsortedContinuousSubarray {

//    可分析出实际是让找出数组中无序的部分
    
//    【辅助变量法】：确定无序子数组的起始和结束位置，这样就能知道子数组的长度了
//    用一个变量start来记录起始位置，开始遍历数组
//    当发现某个数字比其前面的数字要小的时候，说明此时数组不再有序
//
//    将此数字向前移动，移到其应该在的地方，用另一个变量j来记录移动到的位置
//    考虑要不要用这个位置来更新start的值
//
//    当start还是初始值-1时
//        肯定要更新
//        因为这是出现的第一个无序的地方
//    如果当前位置小于start
//        也要更新
//        这说明此时的无序数组比之前的更长了
//
//    举个例子，比如数组[1,3,5,4,2]
//    第一个无序的地方是数字4
//        它移动到的正确位置是坐标2
//        此时start更新为2
//    下一个无序的地方是数字2
//        它的正确位置是坐标1
//        所以此时start应更新为1
//    这样每次用i - start + 1来更新结果res时才能得到正确的结果
    public int findUnsortedSubarray1(int[] nums) {
        int res = 0, start = -1, n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                int j = i;
                while (j > 0 && nums[j] < nums[j - 1]) {
                    //下面这几句相当于swap(nums[j], nums[j - 1]);
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                    --j;
                }
                if (start == -1 || start > j) start = j;
                res = i - start + 1;
            }
        }
        return res;
    }

//    辅助数组法：
//    （这种方式在leetcode上验证，会超出时间限制，所以了解思路就好）
//    新建一个跟原数组一摸一样的数组，然后排序
//    从数组起始位置开始，两个数组相互比较
//    当对应位置数字不同的时候停止
//    同理再从末尾开始，对应位置上比较，也是遇到不同的数字时停止
//    这样中间一段就是最短无序连续子数组了
    public int findUnsortedSubarray2(int[] nums) {
        int n = nums.length, i = 0, j = n - 1;
        //注意，Java中数组拷贝的时候不能这样写：int[] t = nums;
        int[] t = new int[n];
        for(int m = 0;m < nums.length;i++) {
            t[m] = nums[m];
        }
        Arrays.sort(t);
        while (i < n && nums[i] == t[i]) ++i;
        while (j > i && nums[j] == t[j]) --j;
        return j - i + 1;
    }

//    只用四个变量：
//    时间复杂度：O(n)
//    空间复杂度：O(1)
//    用两个变量mx和mn来代替上面的有序数组
//        将最小值mn初始化为数组的最后一个数字
//        将最大值mx初始化为第一个数字
//    从第二个数字开始遍历
//    mx和nums[i]之间取较大值赋值给mx
//        比较此时mx和nums[i]之间的大小关系
//        如果mx大于nums[i]，就把i赋值给end
//        如果第一个数字小于第二个，mx就会赋值为第二个数字
//        这时候mx和nums[i]就相等了，不进行任何操作
//        因为此时是有序的
//    mn和nums[n-1-i]之间取较小值赋给mn
//    比较此时mn和nums[n-1-i]之间的大小关系
//        如果mn小于nums[n-1-i]，就把n-1-i赋值给start
//        当倒数第二个数字大于最后一个数字，进行赋值
//        这样mn还是最后一个数字，而nums[n-1-i]就会大于mn
//    可以看出start是不断往前走的，end是不断往后走的
//    整个遍历完成后，start和end就分别指向了最短无序连续子数组的起始和结束位置
    public int findUnsortedSubarray3(int[] nums) {
        int n = nums.length, start = -1, end = -2;
        int mn = nums[n - 1]; //最小值mn
        int mx = nums[0]; //最大值mx
        for (int i = 1; i < n; ++i) {
            mx = Math.max(mx, nums[i]);
            mn = Math.min(mn, nums[n - 1 - i]);
            if (mx > nums[i]) end = i;
            if (mn < nums[n - 1 - i]) start = n - 1 - i;
        }
        return end - start + 1;
    }
}

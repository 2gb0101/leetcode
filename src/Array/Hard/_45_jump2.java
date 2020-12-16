package Array.Hard;

//Offer:
//  一个非负整数数组
//  最初位于数组的第一个位置
//  数组中的每个元素代表你在该位置可以跳跃的最大长度
//Target:
//  使用最少的跳跃次数，到达数组的最后一个位置
public class _45_jump2 {
//    解法1 ：贪婪算法
//    每次在可跳范围内选择可以使得跳的更远的位置
//    代码中用 end 表示当前能跳的边界
//    遍历数组的时候，到了边界，就重新更新新的边界
    public int jump1(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        //注意细节:for 循环中，i < nums.length - 1，少了末尾
        // 因为开始的时候边界是第 0 个位置，steps 已经加 1 了
        // 如果最后一步刚好跳到了末尾，此时 steps 其实不用加 1 了
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    //    解法2：倒着找
//    我们知道最终要到达最后一个位置，然后我们找前一个位置，遍历数组，找到能到达它的位置，离它最远的就是要找的位置。
//    然后继续找上上个位置，最后到了第 0 个位置就结束了。
//    至于离它最远的位置，其实我们【从左到右】遍历数组，第一个满足的位置就是我们要找的。
    public int jump2(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}

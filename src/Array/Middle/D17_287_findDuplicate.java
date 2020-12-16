package Array.Middle;

//Offer：一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n）
//Target：假设只有一个重复的整数，找出这个重复的数

//说明：
//不能更改原数组（假设数组是只读的）
//只能使用额外的 O(1) 的空间
//时间复杂度小于 O(n2)
//数组中只有一个重复的数字，但它可能不止重复出现一次

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class D17_287_findDuplicate {

//    思路：如果题目不限制：
//    1.不能更改原数组（假设数组是只读的）；
//    2.只能使用额外的 O(1) 的空间
//    容易想到的方法有：
//    ① 哈希表：使用哈希表判重，违反了限制 2；
//    ② 排序：将原始数组排序，排序以后，重复的数相邻，即找到了重复数，这违反了限制 1；
//    ③ 抽屉法：当两个数发现要放在同一个地方的时候，就发现了这个重复的元素，这违反了限制 1；
//            抽屉原理：
//            桌上有十个苹果，要把这十个苹果放到九个抽屉里
//            无论怎样放，我们会发现至少会有一个抽屉里面放不少于两个苹果
//    ④ 二分法定位数：
//            既然要定位数，这个数恰好是一个整数
//            可以在“整数的有效范围内”做二分查找
//            但是比较恶心的一点是得反复看整个数组好几次
//    ⑤ 快慢指针
//    ⑥ 二进制法

//    排序（先不考虑题目要求）
//    最简单的，先排序，然后两两判断即可
    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

//    java.util.HashSet（先不考虑题目要求）
//    判断重复数字，可以用 HashSet，这个方法经常用了
    public int findDuplicate2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }

//    二分查找
//    本题二分法与其他问题的不同点：
//        正着思考是容易的，即思考哪边区间存在重复数是容易的，因为依然是有抽屉原理做保证
//    举例：
//        [1, 2, 2, 3, 4, 5, 6, 7] ，一共 8 个数，n + 1 = 8，n = 7
//        根据题目意思，每个数都在 1 和 7 之间
//        区间 [1, 7] 的中位数是 4，遍历整个数组，统计小于等于 4 的整数的个数，至多应该为 4 个
//        换句话说，整个数组里小于等于 4 的整数的个数
//        如果严格大于 4 个，就说明重复的数存在于区间 [1, 4]，它的反面是：重复的数存在于区间 [5, 7]
//    于是，二分法的思路是：
//        先猜一个数（有效范围 [left, right]里的中间数 mid）
//        然后统计原始数组中小于等于这个中间数的元素的个数 cnt
//        如果 cnt 严格大于 mid
//        依然根据抽屉原理，重复元素就应该在区间 [left, mid] 里
//    说明：下面的算法运行时间肯定不会高，因为这个算法是空间敏感的，「用时间换空间」是反常规做法，大家了解一下即可
//    平常大家写算法，一般情况下都是「空间换时间」
    @Test
    public void test(){
        new D17_287_findDuplicate().findDuplicate3(new int[]{1,3,4,2,2});
    }

    public int findDuplicate3(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        //这里的 left 和 right 其实和 nums 中数据的顺序无关
        //nums中的数据只会在for循环统计时使用到
        //这里的left和right相当于抽屉的下标

        // 在Leetcode系统上，使用 while (left <= right)就提示超时了，因为会陷入死循环，可以用上面的test() debug看下
        // 从逻辑上讲：循环结束的时候，当left=right时，就会唯一定位到【装了重复数据的抽屉】
        // 因此循环条件只能写left<right而不能写left<=right
        while (left <right) {
            // 在 Java 里可以这么用，当 left + right 溢出的时候，无符号右移保证结果依然正确
            int mid = (left + right) >>> 1;

            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt += 1;
                }
            }

            // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个
            // 此时重复元素一定出现在 [1, 4] 区间里

            if (cnt > mid) {
                // 重复元素位于区间 [left, mid]
                right = mid;
            } else {
                // if 分析正确了以后，else 搜索的区间就是 if 的反面
                // [mid + 1, right]
                left = mid + 1;
            }
        }
        // 因为题目说：一定存在重复的数，所以直接返回 left/right 即可，不用做额外的判断了
        return left;
    }
//    时间复杂度：O(NlogN)
//            二分法的时间复杂度为 O(logN)
//            在二分法的内部，执行了一次 for 循环，时间复杂度为 O(N)，故时间复杂度为 O(NlogN)
//    空间复杂度：O(1)，使用了一个 cnt 变量，因此空间复杂度为 O(1)
//

//    解法4：二进制
//    1、把数字转成二进制
//    2、依次统计数组中每一位 1 的个数，记为 a[i]
//    3、依次统计 1 到 n 中每一位 1 的个数，记为 b[i]

//    i 代表的是哪一位，因为是 int，所以范围是 0 到 32
//    记重复的数字是 res
//    如果 a[i] > b[i] 也就意味着 res 当前位是 1
//    否则的话，res 当前位就是 0
    public int findDuplicate4(int[] nums) {
        int res = 0;
        int n = nums.length;
        //统计每一列 1 的个数
        for (int i = 0; i < 32; i++) {
            int a = 0;
            int b = 0;
            int mask = (1 << i);
            for (int j = 0; j < n; j++) {
                //统计原数组当前列 1 的个数
                if ((nums[j] & mask) > 0) {
                    a++;
                }
                //统计 1 到 n 序列中当前列 1 的个数
                if ((j & mask) > 0) {
                    b++;
                }
            }
            if (a > b) {
                res = res | mask;
            }
        }
        return res;
    }
//
//    解法5：指针法
//    把数组的值看成 next 指针，数组的下标看成节点的索引因为数组中至少有两个值一样，也说明有两个节点指向同一个位置，所以一定会出现环
//    举个例子，3 1 3 4 2 可以看成下图的样子
//
//    nums[0] = 3
//    nums[3] = 4
//    nums[4] = 2
//    nums[2] = 3
//    所以我们要做的就是找到上图中有环链表的入口点 3
//
//    我们需要快慢指针，同时从起点出发，慢指针一次走一步，快指针一次走两步，然后记录快慢指针相遇的点
//    之后再用两个指针，一个指针从起点出发，一个指针从相遇点出发，当他们再次相遇的时候就是入口点了
//    相遇点 ≠ 入口点
    @Test
    public void test1(){
        new D17_287_findDuplicate().findDuplicate5(new int[]{3,1,3,4,2});
    }

    public int findDuplicate5(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        //寻找相遇点
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        //slow 从起点出发, fast 从相遇点出发, 一次走一步
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

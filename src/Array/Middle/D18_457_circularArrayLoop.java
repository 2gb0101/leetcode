package Array.Middle;

//Offer: 一个含有正整数和负整数的环形数组 nums
//       如果某个索引中的数 k 为正数，则向前移动 k 个索引
//       相反，如果是负数 (-k)，则向后移动 k 个索引
//       因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素
//Target: 确定 nums 中是否存在循环（或周期）
//        循环必须在相同的索引处开始和结束并且循环长度 > 1
//        此外，一个循环中的所有运动都必须沿着同一方向进行
//        换句话说，一个循环中不能同时包括向前的运动和向后的运动

//示例：
//输入：[2,-1,1,2,2]
//输出：true
//解释：存在循环，按索引 0 -> 2 -> 3 -> 0 ，循环长度为 3

//提示：
//    -1000 ≤ nums[i] ≤ 1000
//    nums[i] ≠ 0
//    0 ≤ nums.length ≤ 5000
//
//进阶：你能写出时间时间复杂度为 O(n) 和额外空间复杂度为 O(1) 的算法吗？
public class D18_457_circularArrayLoop {
//    解法1：双指针
//    对于查找数组或链表中有没有环的问题，多可以朝快慢指针的方向去想，本题也不例外
//    基本思想是快慢指针
//    1）数组长度为0时，认为无环
//    2）快慢指针的思想是慢指针走一步，快指针走两步，若他俩相遇肯定是在环中的某一个节点上相遇，则证明存在环
//    3）假设位置i的元素是环中的一点，通过快慢指针的思想，假定慢指针为j，快指针为k，那么一定会有j==k的时候
//        问题是位置i可能不是环中的一个节点鉴于此肯定是要遍历全部的节点的
//        for(i = 0; i < nums.length;++i>)
//        对于这样的i都要试一试是否是环中的一个节点
//        若是则return true，否则则return false
//        如果这样的话，肯定做不到时间复杂度是O(n)的程度，要稍微“剪下枝”
//    4）根据提示，nums中所有元素都不可能是0这是剪枝可以进行的关键
//       另一个原则是如果存在环，那么环中的所有数字的符号都必须是一致，否则不满足题意
//       这时我们认为本链条不处于环上
//       然后将节点i到当前位置的所有元素置0，以标记这些节点都不在环上
//    5）另一点是：当某节点j指向已经确定不在环中的节点时，就不必继续走下去了，节点j肯定也不在环上
//    6）当循环长度为1是，也是不存在环的，比如[8,2]这个数组，从任意位置开始都指向它自己我们认为他是无环的
//
//    重述下无环的判定依据：
//    1）当快慢指针指向的新节点发现和上一个节点符号不一致
//    2）当快慢指针指向的位置不变时
//    3）快慢指针指向了不可能是环中节点的节点(该节点位置已经置0的节点)时
    public boolean circularArrayLoop1(int[] nums) {
        if (nums.length == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            int lastJ, lastK;
            // 慢指针为j,快指针为k
            int j=i, k=i;

            while (true) {
                lastJ = j;
                // 让慢指针走一步
                // j是当前位置，nums[j]是这次要走的步数
                // 这里的1000*nums.length：是处理负的步数，因为循环索引不能为负
                j = (j + nums[j] + 1000*nums.length) % nums.length;
                // nums[lastJ]*nums[j]:判断快慢指针指向的新节点和上一个节点符号是否一致
                if (nums[lastJ]*nums[j] < 0 || nums[j] == 0 || lastJ == j) {
                    setZero(nums, i);
                    break;
                }

                // 让快指针走两步
                lastK = k;
                k = (k + nums[k] + 1000*nums.length) % nums.length;
                if (nums[lastK]*nums[k] < 0 || nums[k] == 0 || lastK == k){
                    setZero(nums, i);
                    break;
                }
                lastK = k;
                k = (k + nums[k] + 1000*nums.length) % nums.length;
                if (nums[lastK]*nums[k] < 0 || nums[k] == 0 || lastK == k){
                    setZero(nums, i);
                    break;
                }
                if (j == k)
                    return true;
            }
        }
        return false;
    }

    private void setZero(int[] nums, int i){
        int j;
        while (true) { // !(nums[j] == 0 || nums[i]*nums[j]<0)
            j = (i + nums[i] + 1000*nums.length) % nums.length;
            // 如果下一步已经为0，或者下一步和本步的方向不同（正负相反）
            // 则直接让本步为0即可
            if (nums[j] == 0 || nums[i]*nums[j]<0) {
                nums[i] = 0;
                break;
            }
            // 否则需要通过循环，把接下来这条链路都设置为0
            //（这条链路已经走不通了，所以只能全部置零）
            nums[i] = 0;
            i = j;
        }
    }

//    解法2：指针法的另一种写法
    public boolean circularArrayLoop2(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            // 修改索引值 保证其 < n
            // 因为这是环形数组，所以可以进行% n
            // 即使不同的数进行% n后的得到同样的值，也没关系
            // 因为nums[i]的意义在于走多少步，在环形数组中
            // 走nums[i]步和走nums[i] % n是一样的
            nums[i] = nums[i] % n;
        }

        for (int i = 0; i < n; ++i) {
            int f = nums[i];
            //跳过之前尝试过的
            if (f >= n) continue;

            int j = i;
            int flag = n + i;
            int last = j;
            while (nums[j] < n) {
                //方向不一致 直接退出
                if (f * nums[j] < 0) break;
                int next = (j + nums[j] + n) % n;
                nums[j] = flag; //标志是已经走过的位置
                last = j;
                j = next;
            }
            // j != last这个条件，说明了环不能是示例2：[-1,2]这种情况
            if (nums[j] == flag && j != last) return true;
        }

        return false;
    }
}

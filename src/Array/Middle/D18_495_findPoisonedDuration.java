package Array.Middle;

//Offer: 给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间
//Target: 你需要输出艾希的中毒状态总时长
//        你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态

//示例2:
//输入: [1,2], 2
//输出: 3
//原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
//但是第 2 秒初，提莫再次攻击了已经处于中毒状态的艾希。
//由于中毒状态不可叠加，提莫在第 2 秒初的这次攻击会在第 3 秒末结束。
//所以最终输出 3 。
public class D18_495_findPoisonedDuration {
//    解法：单次扫描
//    我们只需要对数组进行一次扫描就可以计算出总的中毒持续时间
//    考虑相邻两个攻击时间点 A[i] 和 A[i + 1] 以及中毒持续时间 t
//        如果 A[i] + t <= A[i + 1]
//            那么在第 i + 1 次攻击时，第 i 次攻击造成的中毒的持续时间已经结束，即持续时间为 t
//        如果 A[i] + t > A[i + 1]
//            那么在第 i + 1 次攻击时，第 i 次攻击的中毒仍然在持续，由于中毒状态不可叠加，因此持续时间为 A[i + 1] - A[i]
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        if (n == 0) return 0;

        int total = 0;
        for(int i = 0; i < n - 1; ++i){
            // 循环里计算的中毒时间是从timeSeries[n]到timeSeries[1]所有的中毒时间
            total += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        }
        //最后的结果要记得加上timeSeries[0]到timeSeries[1]的中毒时间，即duration
        return total + duration;
    }
//    时间复杂度：O(N)，其中 N 是数组 A 的长度
//    空间复杂度：O(1)
}

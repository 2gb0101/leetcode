package Array.Easy;

//给你一个整数 n
//请你先求出从 1 到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
//请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个

//输入：n = 13
//输出：4
//解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：
//    [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]
//    总共有 4 个组拥有的数字并列最多
public class D33_1399_countLargestGroup {
//    解法1：借助桶和动态规划的思想
//    1 先思考如何将十进制的各个数位相加，因为求的是从1-n的数位和，所以可以找出规律：
//        sum[i] = sum[i / 10] + i % 10 //按顺序添加sum[i]
//    2 然后统计各数位和的个数（有多少个相同个数并列的组）
//    3 求出并列数目最多的组，也就是统计和中最大的那个数
//    4 最后计算与max相等的数有多少个就可以了
    public int countLargestGroup1(int n) {
        int ans = 0, max = 1;
        int[] count = new int[n + 1];// 统计数位和有多少
        int[] sum = new int[n + 1]; // 计算1-n各个元素的数位和，例如数字i的数位和是sum[i / 10] + i % 10

        for(int i = 1; i <= n; i++){
            sum[i] = sum[i / 10] + i % 10;
            count[sum[i]]++;
            if(count[sum[i]] > max)
                max = count[sum[i]];
        }
        for(int num : count) ans += num == max ? 1 : 0;
        return ans;
    }


//    解法2：辅助数组
//    申请1个array记录，index是数位和，value是相同数位和的数字的个数
//    因为输入最大范围为10^4，所以数位和结果最大是4*9=36，所以array大小设37就好啦
//    第一次for循环记录每个相同sum结果的数字的个数
//    第二次for循环遍历找并列最多的数量（其实也用不了37次）
    public int countLargestGroup2(int n) {
        if(n < 9) return n;
        int[] bit_sum = new int[37];
        for(int i = 1; i <= n; i ++){
            int temp = i;
            int sum = 0;
            while(temp > 0){
                sum += temp % 10;
                temp /= 10;
            }
            bit_sum[sum] ++;
        }
        int max = 1;
        int ans = 1;
        for(int i = 1; i < 38; i++){
            if(bit_sum[i] > max){
                max = bit_sum[i] ;
                ans = 1;
            }else if(bit_sum[i]  == max){
                ans += 1;
            }else{
                break;
            }
        }
        return ans;
    }
}

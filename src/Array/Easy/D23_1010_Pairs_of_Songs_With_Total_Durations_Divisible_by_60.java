package Array.Easy;

//Offer：一个整数数组，代表歌曲列表
//Target：
//在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒
//返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量
//形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0
public class D23_1010_Pairs_of_Songs_With_Total_Durations_Divisible_by_60 {
//    暴力法：OJ显示超时
    public int numPairsDivisibleBy60_1(int[] time) {
        int result = 0;
        for (int i = 0; i < time.length; i++) {
            for (int j = i + 1; j < time.length; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    result++;
                }
            }
        }
        return result;
    }

//    取余法：
//    遍历time数组对60求余
//    再判断 time[i] + time[j] == 60 和 time[i] + time[j] == 0
//    依旧超时，但在OJ上通过的案例比暴力法多
    public int numPairsDivisibleBy60_2(int[] time) {
        int result = 0;
        for (int i = 0; i < time.length; i++) {
            time[i] %= 60;
        }
        for (int i = 0; i < time.length; i++) {
            for (int j = i + 1; j < time.length; j++) {
                if (time[i] + time[j] == 60 || time[i] + time[j] == 0) {
                    result++;
                }
            }
        }
        return result;
    }

//    辅助数组法：
//    沿用取余的思想
//    用一个数组保存time数组求余后的数量
    public int numPairsDivisibleBy60_3(int[] time) {
        int result = 0;
        int[] mod = new int[60];
        for (int i = 0; i < time.length; i++) {
            mod[time[i] % 60]++;
        }
        for (int i = 1; i < 30; i++) {
            result += mod[i] * mod[60 - i];
        }
        //处理特殊情况
        //注意，这里的mod[0] * (mod[0] - 1)需要除以2的原因是
        //比如说mod[0] = 3，即现在有三首长度均为0的歌
        //假如这三首歌分别为A、B、C，他们可以组合出mod[0] * (mod[0] - 1) = 3 * 2，共6种情况
        //但由于AC和CA是同一种组合，所以最后还需要除以2
        result += (mod[0] * (mod[0] - 1) + mod[30] * (mod[30] - 1)) / 2;
        return result;
    }
}

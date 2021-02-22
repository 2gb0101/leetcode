package DynamicProgramming.Easy;

//爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
//最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
//选出任一 x，满足 0 < x < N 且 N % x == 0 。
//用 N - x 替换黑板上的数字 N 。
//如果玩家无法执行这些操作，就会输掉游戏。
//只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。
//假设两个玩家都以最佳状态参与游戏。

//示例 1：
//输入：2
//输出：true
//解释：爱丽丝选择 1，鲍勃无法进行操作。
//
//示例 2：
//输入：3
//输出：false
//解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
public class D36_1025_divisorGame {
//    解法1：数学
//    规则：Alice先手
//    观察：谁先从2的基础减去1谁胜

//    1.若N为奇数，则可以整除的为奇数。
//        若可以整除，Alice先手减去奇数，得到偶数，则Bob只需每次减一直到2，Bob胜；
//        Alice为奇数不能整除，则需每次减1，Bob先得到2，Bob胜。所以奇数的话Alice输。
//
//        问：如果N=5的话，A先减1，剩余4，然后B减2剩余2，最后A减1剩余1，B输？
//        答：如果鲍勃选2，那下次到他的时候，n为1，他一定输了，他们的选择是为了尽可能拿到胜利
//        （题目说了，假设两个玩家都以最佳状态参与游戏），所以鲍勃不会选2而会选1
//        所以最靠谱的选择就是每个人每次都选1，最后到谁的时候n=1，谁就输了
//    2.若N为偶数，则其可以整除的为奇数或偶数。
//        为保证胜利，Alice只需每次减一先得到2即可
//        如果Alice减去1得到奇数，由规则 1 可知，奇数的话先手会输（此时Bob先手）
//        所以偶数的话Alice会赢
    public boolean divisorGame1(int N) {
        return N % 2 == 0;
    }

//    解法2：动态规划
//    dp[]代表一个长度为n+1的数组，如果 dp[n-x]为false,则Alice会减去x
//    即 Bob==dp[n-x]==false, Alice胜，否则Alice输
//    因为不管x是多少，dp[n-x]为true, 则dp[n]==Alice==false
    public boolean divisorGame2(int N) {
        if(N == 1) return false;
        if(N == 2) return true;

        boolean[] dp = new boolean[N+1];
        dp[1] = false;
        dp[2] = true;

        for(int i = 3; i<=N; i++){
            dp[i] = false;
            for(int j = 1; j<i; j++){
                if(i % j == 0 && !dp[i - j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
}

package Array.Easy;

//Offer：
//    A 和 B 在一个 3 x 3 的网格上玩井字棋
//    给你一个数组 moves，其中每个元素是大小为 2 的另一个数组（元素分别对应网格的行和列）
//    它按照 A 和 B 的行动顺序（先 A 后 B）记录了两人各自的棋子位置
//Target：
//    如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；
//    如果游戏以平局结束，则返回 "Draw"；
//    如果仍会有行动（游戏未结束），则返回 "Pending"。
//
//    你可以假设moves都有效（遵循井字棋规则），网格最初是空的，A 将先行动。

import java.util.HashSet;
import java.util.Set;

public class D28_1275_Find_Winner_on_a_Tic_Tac_Toe_Game {
//    暴力法：
//        可以模拟数组 move 中的每一步落子
//        使用两个集合 A 和 B 存放每位玩家当前已经落子的位置
//        并用集合 wins 存放棋子排成一条直线的所有情况
//            排成一行或一列各有 3 种
//            排成对角线有 2 种
//            总计 8 种
//        当某位玩家落子时
//            枚举 wins 中的每一种情况
//            并判断该玩家是否将棋子落在了这些位置
//            如果满足了其中一种情况，则该玩家获胜
//        如果直到落子完毕仍然没有玩家获胜
//        根据数组 move 的长度返回平局 Draw 或游戏未结束 Pending
    public String tictactoe1(int[][] moves) {
        int[][] wins = {  //数组中存的位置为0-8的一个数：moves[i][0] * 3 + moves[i][1];
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        Set<Integer> A = new HashSet<Integer>();
        Set<Integer> B = new HashSet<Integer>();
        for (int i = 0; i < moves.length; ++i) {
            int pos = moves[i][0] * 3 + moves[i][1];
            if (i % 2 == 0) {
                A.add(pos);
                if (checkwin(A, wins)) {
                    return "A";
                }
            } else {
                B.add(pos);
                if (checkwin(B, wins)) {
                    return "B";
                }
            }
        }

        return (moves.length == 9 ? "Draw" : "Pending");
    }

    boolean checkwin(Set<Integer> S, int[][] wins) {
        for (int[] win: wins) {
            boolean flag = true;
            for (int pos: win) {
                if (!S.contains(pos)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

//    位运算法：
//        井字棋总共只有9个格子，且赢面是固定的
//        可以使用一个9位二进制数代表行走的结果，规定：
//        井字棋坐标[i,j]对应于数字的第 (3*i)+j 位
//        每走一步棋等价于与对应的位进行(异)或运算
//    判断游戏结果的方法：
//        将一方的数字num与赢面对应的数字k进行与运算，若结果为k，此方获胜
//            (与运算，结果为k，说明num的值也为k，所以num为赢面对应的数字)
//        若双方都未获胜：
//            若总步数为9步，则平局(DrawDraw)
//            否则，未完成(PendingPending)
//
//    赢面数字：
//    井字棋的赢面只有8种(3种横+3种竖+2种对角)
//    计算举例：
//        {[0,0],[0,1],[0,2]}为横的一种赢面
//                对应的99位二进制数为000000111
//        即十进制下的7
//    事实上，由对应规则可以得知：
//        3种横的赢面数字是公比为8的等比数列
//        3种竖的赢面数字是公比为2的等比数列
//        总共只需要计算出4个数字(1种横+1种竖+2种对角)，其余按倍数推导即可
//    所有赢面数字分别为
//        3种横的:7, 56(即7 * 8), 448(即7 * 8^2),
//        3种竖的:73, 146(即73 * 2), 292(即73 *2^2)
//        对角线：273, 84
    public String tictactoe2(int[][] moves) {
        // a, b record the moving results of A, B
        int a = 0, b = 0, len = moves.length;
        // ac records all cases of winning
        int[] ac = {7, 56, 448, 73, 146, 292, 273, 84};
        for(int i = 0; i < len; i ++){
            // if i is add
            if((i & 1) == 1){
                // record the step result
                b ^= 1 << (3 * moves[i][0] + moves[i][1]);
            }
            else {
                a ^= 1 << (3 * moves[i][0] + moves[i][1]);
            }
        }
        for(int i : ac){
            // if the moving result contains the winning case in record, then win
            if((a & i) == i){
                return "A";
            }
            if((b & i) == i){
                return "B";
            }
        }
        // or judge the result by the amount of steps
        return len == 9 ? "Draw" : "Pending";
    }
}

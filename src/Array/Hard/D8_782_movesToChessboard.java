package Array.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Offer：一个 N x N的 board，仅由 0 和 1 组成
// Target：
// 输出将这个矩阵变为 “棋盘” 所需的最小移动次数。
// 每次移动，能任意交换两列或是两行的位置
// “棋盘” ：指任意一格的上下左右四个方向的值均与本身不同的矩阵。
// 如果不存在可行的变换，输出 -1。
public class D8_782_movesToChessboard {
    public int movesToChessboard(int[][] board) {
        int N = board.length;

        // 解法：分维度计算
//        首先思考，一次交换之后，棋盘会发生什么变化：
//          用交换列来做例子
//          对任意两列进行交换，不会改变任意两行之间的状态
//          即，如果这两行原本就相同，列交换之后这两行依旧相同
//          如果这两行本来就不同，列交换之后也还是不同
//          由于最终的棋盘只有两种不同的行，最初的棋盘也一定只有两种不同的行

//        考虑到只有两种不同的行，可以分别用 0，1 对其表示。
//        要达成最终的棋盘实际上等价于将棋盘的行表示成 0，1相隔的状态。
//        假设在将棋盘的行用 0，1 表示之后得到数组为 [0, 1, 1, 1, 0, 0]
//        那么只需求这个数组变成 [0, 1, 0, 1, 0, 1] 和 [1, 0, 1, 0, 1, 0] 的代价，之后取其中最小的代价就好了
//        同理，对列也是如此，这就将二维问题变成了两个一维问题

//        再来看棋盘行/列的规律
//          棋盘有两种行，这两种行每一位都互相不同
//          对于每一行来说，一定有一半为 1，一半为 0（如果长度为奇数，会多一个 1 或多一个 0）

        // count[code] = v, where code is an integer
        // that represents the row in binary, and v
        // is the number of occurrences of that row
        Map<Integer, Integer> count = new HashMap();
//        先换行再换列跟先换列再换行结果是一样的。在这里先将所有的行调到正确的位置，再将所有的列调到正确的位置。
        for (int[] row: board) {
            int code = 0;
            for (int x: row)
                code = 2 * code + x;
            count.put(code, count.getOrDefault(code, 0) + 1);
            //count的key保存了每一行代表的数字
        }

        int k1 = analyzeCount(count, N);
        if (k1 == -1) return -1;

        // count[code], as before except with columns
        count = new HashMap();
        for (int c = 0; c < N; ++c) {
            int code = 0;
            for (int r = 0; r < N; ++r)
                code = 2 * code + board[r][c];
            count.put(code, count.getOrDefault(code, 0) + 1);
        }

        int k2 = analyzeCount(count, N);
        return k2 >= 0 ? k1 + k2 : -1;
    }

    public int analyzeCount(Map<Integer, Integer> count, int N) {
//        首先需要确认是否有且只有两种行（列）存在，且这两种行（列）的 0，1 排布合法，如果不符合条件直接返回 -1
//        之后需要生成理想的行（列）的状态（即0，1相隔的数组排列），对于每种理想状态，计算其与初始状态之间变换的代价
//        举个例子，对于 [0, 1, 1, 1, 0, 0] 初始状态来说，有两种理想状态
//        分别是 [0, 1, 0, 1, 0, 1] 和 [1, 0, 1, 0, 1, 0]
//        对于 [0, 1, 1, 1, 0] 初始状态只有一种理想状态 [1, 0, 1, 0, 1]

        if (count.size() != 2) return -1;

        List<Integer> keys = new ArrayList(count.keySet());
        int k1 = keys.get(0), k2 = keys.get(1);

        // If lines aren't in the right quantity
        if (!(count.get(k1) == N/2 && count.get(k2) == (N+1)/2) &&
                !(count.get(k2) == N/2 && count.get(k1) == (N+1)/2))
            return -1;
        // If lines aren't opposite
        if ((k1 ^ k2) != (1<<N) - 1) //检查k1和k2的01是否相互交错开来，如果不是，就返回-1
            return -1;

        int Nones = (1 << N) - 1;
        int ones = Integer.bitCount(k1 & Nones); // bitCount统计二进制中1的个数
        int cand = Integer.MAX_VALUE;
//        在 Java 实现中，用整型来表示每行。
//        之后将其与 0b010101010101.....01 进行异或来计算初始状态转换到理想状态的代价
//        为了代码简洁，这里统一使用 0xAAAAAAAA 和 0x55555555（A代表1010,5代表0101）
//        为了不引入额外的转换代价，还需要根据行的长度 N 生成 0b00...0011...11 掩码与结果做与运算
        if (N%2 == 0 || ones * 2 < N) // zero start
            cand = Math.min(cand, Integer.bitCount(k1 ^ 0xAAAAAAAA & Nones) / 2);

        if (N%2 == 0 || ones * 2 > N) // ones start
            cand = Math.min(cand, Integer.bitCount(k1 ^ 0x55555555 & Nones) / 2);

        return cand;
    }
}

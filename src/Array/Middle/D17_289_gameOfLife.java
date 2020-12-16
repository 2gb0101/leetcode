package Array.Middle;

//Offer: 一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞
//        每个细胞都具有一个初始状态：
//            1 即为活细胞（live），或 0 即为死细胞（dead）
//        每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
//            如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
//            如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
//            如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
//            如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
//Target: 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态
//        下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的
//        其中细胞的出生和死亡是同时发生的
//
//        请注意，面板上所有格子需要同时被更新：
//            你不能先更新某些格子
//            然后使用它们的更新后的值再更新其他格子
//进阶：
//你可以使用原地算法解决本题吗？
//本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
public class D17_289_gameOfLife {
//    解法1：矩阵问题通用解法
//    总结：
//    类似的矩阵问题有很多，但不管什么问题，有两个技巧是一定用的上的，一定要记住！！！
//    1、矩阵中某个位置的状态如果发生改变，解法一般如下
//        遍历两次整个矩阵
//        第一遍遍历时，用一个不可能出现在原矩阵中的中间值来保存状态的变化
//        （这样在此次遍历时，不影响其他的位置的判断，比如我们可以用“$”这种没人用的字符）
//        第二遍遍历时，把中间值刷新成为变化后应该变成的值
//    2、如果遍历到某个位置时，需要查看它周边的位置
//        此时如果每一个周围的位置都手写，然后再判断是否越界，就很麻烦
//        可以先用一个数组保存向周边位置变化的坐标偏移值，一次性通过一个循环，来遍历完周边的位置，并且方便进行越界判断

//    分析本题：本题就是上面两个技巧的典型用例
//    因为所有的格子同时刷新变化，所以，你需要两次遍历
//    第一次遍历时，判断下一次刷新后的状态应该是啥，你要记下变化，但你又不能影响本次正在进行的遍历，所以你可以用一个中间值来记录
//    第二次遍历时，刷新中间值

//    定义状态：1 代表细胞活的， 0 代表细胞死的，那么这个位置就四种状态，用【下一个状态，当前状态】表示，最后需要用右移操作更新结果
//    状态 0： 00 ，死的，下一轮还是死的
//    状态 1： 01，活的，下一轮死了
//    状态 2： 10，死的，下一轮活了
//    状态 3： 11，活的，下一轮继续活着
//    想法很简单，因为之前记录细胞生命是否活着的时候用的是 0 和 1
//    相当于只用了 1 个比特位来记录
//    把它们扩展一位，看成 00 和 01
//
//    进一步：下一轮活的可能有两种，也就是要把单元格变为 1
//        这个活细胞周围八个位置有两个或三个活细胞，下一轮继续活，属于 11
//        这个细胞本来死的，周围有三个活着的，下一轮复活了，属于 10
//    那遍历下每个格子看他周围细胞有多少个活细胞就行了，然后更改为状态
//        对于第一种可能，把 board[i][j] 设置为 3，对于第二种可能状态设置为 2
//        设置个高位flag，遍历后面的格子，拿到与他相邻的格子中有多少个 alive 的，和 1 与一下即可
//        最后我们把 board[i][j] 右移 1位，更新结果
//    补充：还原时还有另一种做法，【对 2 求余】:board[i][j] %=2;
    int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    int[][] board;
    int m, n;

    public void gameOfLife(int[][] board) {
        this.board = board;
        // 特判
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        this.m = board.length;
        this.n = board[0].length;
        // 遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 拿到当前位置周围活细胞数量
                int cnt = countAlive(i, j);
                // 1. 活细胞周围八个位置有两个或三个活细胞，下一轮继续活
                if (board[i][j] == 1 && (cnt == 2 || cnt == 3)) board[i][j] = 3;
                // 2. 死细胞周围有三个活细胞，下一轮复活了
                if (board[i][j] == 0 && cnt == 3) board[i][j] = 2;
            }
        }

        // 更新结果
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int countAlive(int x, int y) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            // 如果这个位置为 0，代表当前轮是死的，不需要算进去
            // 如果这个位置为 1，代表当前轮是活得，需要算进去
            // 如果这个位置为 2，代表当前轮是死的（状态10，下一轮是活的），不需要算进去
            // 如果这个位置为 3，代表是当前轮是活的（状态11，下一轮也是活的），需要算进去
            cnt += (board[nx][ny] & 1);
        }
        return cnt;
    }

}
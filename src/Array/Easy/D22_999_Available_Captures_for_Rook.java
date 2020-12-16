package Array.Easy;

//Offer：
//在一个 8 x 8 的棋盘上，有一个白色车（rook）
//也可能有空方块，白色的象（bishop）和黑色的卒（pawn）
//它们分别以字符 “R”，“.”，“B” 和 “p” 给出
//大写字符表示白棋，小写字符表示黑棋。
//
//车按国际象棋中的规则移动：
//它选择四个基本方向中的一个（北，东，西和南）
//然后朝那个方向移动
//直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒
//另外，车不能与其他友方（白色）象进入同一个方格
//
//Target：
//返回车能够在一次移动中捕获到的卒的数量
public class D22_999_Available_Captures_for_Rook {
//    暴力法：循环找到车，然后四个方向搜索
    public int numRookCaptures(char[][] board) {
        int ans = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == 'R') {
                    for (int k = j - 1; k >= 0; k--) {
                        if (board[i][k] == 'B') break;
                        if (board[i][k] == 'p') {
                            ans++;
                            break;
                        };
                    };

                    for (int k = j + 1; k < 8; k++) {
                        if (board[i][k] == 'B') break;
                        if (board[i][k] == 'p') {
                            ans++;
                            break;
                        };
                    };

                    for (int k = i - 1; k >= 0; k--) {
                        //if (board[k][j] == '.') continue;
                        if (board[k][j] == 'B') break;
                        if (board[k][j] == 'p') {
                            ans++;
                            break;
                        };
                    };

                    for (int k = i + 1; k < 8; k++) {
                        if (board[k][j] == 'B') break;
                        if (board[k][j] == 'p') {
                            ans++;
                            break;
                        };
                    };

                    break;
                };
            };
        };
        return ans;
    }
}

package Array.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Offer：
//    给你一个 n 行 m 列的二维网格grid和一个整数k。你需要将grid迁移k次。
//    每次「迁移」操作将会引发下述活动：
//
//    位于 grid[i][j]的元素将会移动到grid[i][j + 1]。
//    位于grid[i][m - 1] 的元素将会移动到grid[i + 1][0]。
//    位于 grid[n - 1][m - 1]的元素将会移动到grid[0][0]。
//
//Target：请你返回 k 次迁移操作后最终得到的 二维网格
public class D28_1260_Shift_2D_Grid {
    //    双向队列
//    先将二维网格线性成一维的双向队列
//    执行k次操作：
//        将双向队列的最后一个数字压入到队列的前面
//        弹出队列内的最后一个数字
//    最后将一维的双向队列重新转换成二维的网格即可
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        //Java中可以利用LinkedList来实现队列
        LinkedList<Integer> dequeGrid = new LinkedList<>();
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                //offer()的作用同add()，但是offer是针对队列的
                //往队列中添加数据
                dequeGrid.offer(grid[i][j]);
            }
        }
        for (int i = 0; i < k; ++i) {
            //获取队列的最后一个值
            int nBack = dequeGrid.getLast();
            //移除最后一个值
            dequeGrid.removeLast();
            //将最后一个值添加至列表头部
            dequeGrid.addFirst(nBack);
        }
        //创建返回的List
        List<List<Integer>> afterList = new ArrayList<List<Integer>>();
        for (int i = 0; i < grid.length; ++i) {
            List<Integer> rowList = new ArrayList<Integer>();
            for (int j = 0; j < grid[i].length; ++j) {
                //将队列中的数从头至尾弹出，弹出的数据不再存在于队列中
                rowList.add(dequeGrid.pop());
            }
            afterList.add(rowList);
        }
        return afterList;
    }
}

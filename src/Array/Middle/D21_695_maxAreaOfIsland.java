package Array.Middle;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//Offer：一个包含了 0 和 1 的非空二维数组 grid
//Target：一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻
//        你可以假设 grid 的四个边缘都被 0（代表水）包围着
//        找到给定的二维数组中最大的岛屿面积
//        (如果没有岛屿，则返回面积为 0 )
public class D21_695_maxAreaOfIsland {

    @Test
    public void test(){
        int[][] nums = new int[][]{
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int temp = new D21_695_maxAreaOfIsland().maxAreaOfIsland1(nums);
        System.out.println(temp);
    }

//    解法1：深度优先遍历(递归实现)
    public int maxAreaOfIsland1(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //计算最大面积
                int currMaxArea = getMaxArea(i, j, grid);
                maxArea = Math.max(currMaxArea, maxArea);
            }
        }

        return maxArea;
    }

    private int getMaxArea(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        //通过将经过的岛屿设置为0来确保下次不会重复访问
        grid[i][j] = 0;

        int upMaxArea = getMaxArea(i - 1, j, grid);

        int downMaxArea = getMaxArea(i + 1, j, grid);

        int leftMaxArea = getMaxArea(i, j - 1, grid);

        int rightMaxArea = getMaxArea(i, j + 1, grid);

        return upMaxArea + downMaxArea + leftMaxArea + rightMaxArea + 1;
    }

//    解法2：深度优先遍历(栈实现)
    public int maxAreaOfIsland2(int[][] grid) {
        Stack<int[]> stack = new Stack<>();
        int[][] moveIndexArray = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                stack.push(new int[]{i, j});
                //计算最大面积
                int currMaxArea = 0;
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    int currI = pop[0];
                    int currJ = pop[1];
                    if (currI < 0 || currI >= grid.length || currJ < 0 || currJ >= grid[0].length || grid[currI][currJ] == 0) {
                        continue;
                    }
                    currMaxArea++;
                    grid[currI][currJ] = 0;
                    for (int[] moveIndex : moveIndexArray) {
                        stack.push(new int[]{currI + moveIndex[0], currJ + moveIndex[1]});
                    }
                }
                maxArea = Math.max(currMaxArea, maxArea);
            }
        }

        return maxArea;
    }

//    解法2和解法3的说明
//            解法2和解法3的代码逻辑是一样的
//    深度优先和广度优先的区别在于两者用的数据结构不同
//    深度优先使用的是栈（利用了其先进后出的特点）
//    广度优先使用的是队列

//    解法3：广度优先遍历(队列实现)
    public int maxAreaOfIsland3(int[][] grid) {
        Deque<int[]> queue = new LinkedList<>();

        int[][] moveIndexArray = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                queue.offer(new int[]{i, j});
                //计算最大面积
                int currMaxArea = 0;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        int[] poll = queue.poll();
                        int currI = poll[0];
                        int currJ = poll[1];
                        if (currI < 0 || currI >= grid.length || currJ < 0 || currJ >= grid[0].length || grid[currI][currJ] == 0) {
                            continue;
                        }
                        currMaxArea++;
                        grid[currI][currJ] = 0;
                        for (int[] moveIndex : moveIndexArray) {
                            queue.offer(new int[]{currI + moveIndex[0], currJ + moveIndex[1]});
                        }
                    }
                }
                maxArea = Math.max(currMaxArea, maxArea);
            }
        }

        return maxArea;
    }
}

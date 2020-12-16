package Array.Easy;

import java.util.ArrayList;
import java.util.List;

//Offer：一个数N
//Target：生成N层的杨辉三角
//思路：
//杨辉三角具体生成的思路是：
//每一行的首个和结尾一个数字都是1
//从第三行开始，中间的每个数字都是上一行的左右两个数字之和
//用于构造杨辉三角的迭代方法可以归类为动态规划
//因为我们需要基于前一行来构造每一行
public class D4_118_PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        // First base case; if user requests zero rows, they get zero rows.
        if (numRows == 0) {
            return triangle;
        }

        // Second base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum - 1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }
        return triangle;
    }
}

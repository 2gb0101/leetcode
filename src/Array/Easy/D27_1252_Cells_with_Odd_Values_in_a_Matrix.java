package Array.Easy;


//Constraints:
//1 <= n <= 50
//1 <= m <= 50
//1 <= indices.length <= 100
//0 <= indices[i][0] < n
//0 <= indices[i][1] < m
public class D27_1252_Cells_with_Odd_Values_in_a_Matrix {

    public int oddCells1(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        int x = 0,y = 0;
        for(int i = 0; i < indices.length; i++){
            //给indices[i][1]列都加1
            while(x < n){
                matrix[x][indices[i][1]] += 1;
                x++;
            }
            //给indices[i][0]行都加1
            while(y < m){
                matrix[indices[i][0]][y] += 1;
                y++;
            }
            //x和y清零，进行下一次循环
            x=0;
            y=0;
        }
        int oddcells = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] % 2 != 0)
                    oddcells++;
            }
        }
        return oddcells;
    }

    public int oddCells2(int n, int m, int[][] indices) {
        int[] rows = new int[n];
        int[] cols = new int[m];
        int x = 0,y = 0;
        for(int i = 0; i < indices.length; i++){
            rows[indices[i][0]]++;
            cols[indices[i][1]]++;
        }
        int oddcells = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if((rows[i] + cols[j]) % 2 != 0)
                    oddcells++;
            }
        }
        return oddcells;
    }

    public int oddCells3(int n, int m, int[][] indices) {
        int[] rows = new int[n];
        int[] cols = new int[m];
        int x = 0,y = 0;
        for(int i = 0; i < indices.length; i++){
            rows[indices[i][0]]++;
            cols[indices[i][1]]++;
        }
        //计算行数组中有多少个位置的数为奇数
        int rowOdd = countOdd(rows);
        //计算列数组中有多少个位置的数为奇数
        int colOdd = countOdd(cols);
        return rowOdd * (m - colOdd) + (n - rowOdd) * colOdd;
    }

    //计算数组中有多少个位置的数为奇数
    int countOdd(int[] array){
        int count = 0;
        for(int tmp : array){
            if(tmp % 2 != 0)
                count++;
        }
        return count;
    }
}

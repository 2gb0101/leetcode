package Array.Easy;

import java.util.Arrays;

//Offer：一个数组
//Target：
//    给数组重新排序
//    使得偶数都排在奇数前面
public class D20_905_Sort_Array_By_Parity {
//    辅助数组法：
//        分别把偶数和奇数分别放到两个数组中
//        然后把奇数数组放在偶数数组之后
//        将拼接成的新数组直接返回即可
    public int[] sortArrayByParity(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int t = 0; t < A.length; ++t){
            B[t] = A[t];
        }

        //假设B为[3,1,2,4]，利用下面的语句排序后
        //得到的结果就是 [2, 4, 3, 1]
        //这里排序的逻辑是：偶数在前，奇数在后
        //因为Integer.compare(a,b)在a > b的时候
        //得到的排序是从小到大
        //而偶数%2 = 0，奇数%2 = 1
        //从而可以根据Integer.compare(a%2, b%2)进行奇偶排序
        Arrays.sort(B, (a, b) -> Integer.compare(a%2, b%2));

        for (int t = 0; t < A.length; ++t)
            A[t] = B[t];
        return A;
    }

//    上面的代码还可以采用这样的写法：
    public int[] sortArrayByParity1(int[] A) {
        return Arrays.stream(A)
                .boxed()
                .sorted((a, b) -> Integer.compare(a%2, b%2))
                .mapToInt(i -> i)
                .toArray();
    }

//    快排法:
//        不需要辅助数组
//        在原数组的基础上进行修改
    public int[] sortArrayByParity2(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] % 2 > A[j] % 2) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            if (A[i] % 2 == 0) i++;
            if (A[j] % 2 == 1) j--;
        }

        return A;
    }

//    两遍扫描法：
//        需要辅助数组
    public int[] sortArrayByParity3(int[] A) {
        int[] ans = new int[A.length];
        int t = 0;

        for (int i = 0; i < A.length; ++i)
            if (A[i] % 2 == 0)
                ans[t++] = A[i];

        for (int i = 0; i < A.length; ++i)
            if (A[i] % 2 == 1)
                ans[t++] = A[i];

        return ans;
    }
}

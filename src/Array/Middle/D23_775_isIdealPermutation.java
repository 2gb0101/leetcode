package Array.Middle;

//数组 A 是 [0, 1, ..., N - 1] 的一种排列，N 是数组 A 的长度
//全局倒置： i,j 满足 0 <= i < j < N 并且 A[i] > A[j]
//局部倒置： i 满足 0 <= i < N 并且 A[i] > A[i+1]
//当数组 A 中全局倒置的数量等于局部倒置的数量时，返回 true

//A 的长度在 [1, 5000]之间
public class D23_775_isIdealPermutation {
//    解法1：维护后缀最小值
//    1、维护后缀数组最小值，减少计算
//    2、计算 局部和 整体个数
//    3、进行比较判断
    public boolean isIdealPermutation1(int[] A) {
        int ju =0;      //局部个数
        for (int j = 0; j < A.length-1; j++) {
            if (A[j]>A[j+1]) ju++;
        }

        //后缀数组最小值
        int [] min = new int[A.length];

        min[A.length-1] = A[A.length-1];
        for (int i = A.length-2; i >=0 ; i--) {
            min[i]= Math.min(min[i+1],A[i]);
        }

        int g = 0;  //整体个数
        for (int i = 0; i < A.length; i++) {
            int j =i+1;
            while(j<A.length && A[i]>min[j]){
                g++;
                j++;
            }
        }
        return g==ju;
    }

//    解法2：找规律
//    首先需要明白如果一个倒置是局部倒置，那它一定是全局倒置，但是反之则不然
//    所以，只要找到不相邻的全局导致，就说明结果为false
//    那我们要做的事情很简单，只需要判断相隔至少一个位置距离的时候前面的数字是否存在大于后面的数字的情况即可
    public boolean isIdealPermutation2(int[] A) {
        int len = A.length;
        if (len <= 2) {
            return true;
            //len=1时也是满足条件的，此时局部倒置和全局倒置的数量都为0
        }
        int max = A[0];
        for (int i = 2; i < len; i ++) {
            if (max > A[i]) {
                return false;
            }
            if (max < A[i - 1]) {
                max = A[i - 1];
            }
        }
        return true;
    }
}

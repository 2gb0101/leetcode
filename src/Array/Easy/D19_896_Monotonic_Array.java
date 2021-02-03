package Array.Easy;

//Offer：一个数组
//Target：
//    判断数组是否单调(递增/递减)
//    不是严格的递增或递减
//    允许有相同的数字的
public class D19_896_Monotonic_Array {
//    直接将相邻的两个数字比较一下即可
//    开始时假设这个数组既是递增的又是递减的
//    使用两个标识符，inc 和 dec
//    初始化均为 true
//    当然这是不可能的，会在后面对其进行更新
//    实现：
//    遍历数组
//        只要发现某个数字大于其身后的数字了，inc 就会赋值为 false
//        只要某个数字小于其身后的数字了，dec 就会被赋值为 false
//    所以在既有递增又有递减的数组中，inc 和 dec 都会变为 false
//    而在单调数组中，二者之间至少有一个还会保持为 true
    public boolean isMonotonic(int[] A) {
        boolean inc = true, dec = true;
        for (int i = 1; i < A.length; ++i) {
            inc &= (A[i - 1] <= A[i]);
            dec &= (A[i - 1] >= A[i]);
            if (!inc && !dec) return false;
        }
        return true;
    }
}

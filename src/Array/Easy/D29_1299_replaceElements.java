package Array.Easy;

//Offer：一个数组 arr
//Target：
//    将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换
//    完成所有替换操作后，请你返回这个数组。
public class D29_1299_replaceElements {
//    从右往左遍历
//    先记录右边最大值 rightMax 为最后一个值
//    向左每次更新 rightMax
//    使用变量 t 先记住当前 arr[i] 就可以了
    public int[] replaceElements(int[] arr) {
        int rightMax = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int t = arr[i];
            arr[i] = rightMax;
            if (t > rightMax)
                rightMax = t;
        }
        return arr;
    }
}

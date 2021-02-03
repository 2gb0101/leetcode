package Array.Easy;

//Offer：一个非递减的 有序 整数数组
//Target：
//已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的25%
//请你找到并返回这个整数
public class D29_1287_Element_Appearing_More_Than_25_In_Sorted_Array {
//    利用非递减性质，可以有两种解法

//    时间复杂度O(N)的解法：
//    如果对于每个元素都做count计数，那数组的非递减属性就浪费掉了
//    利用非递减属性
//        可以从当前数组的第25%位置开始遍历
//        往前和往后依次偏移25%个计数
//        如果偏移后的元素值跟当前值相等，就找到了答案
    public int findSpecialInteger1(int[] arr) {
        int len = arr.length / 4;
        for (int i = len; i < arr.length; i++) {
            if ((arr[i - len] == arr[i]) || (arr[i + len] == arr[i])) {
                return arr[i];
            }
        }
        return 0;
    }

//    时间复杂度O(logN)的解法：
//    自己手画图，可以发现最终的答案肯定是这三个位置之一：25%长度、50%长度、75%长度
//        所以只需要对这三个元素依次用二分法查左右边界
//    确定左右边界差值是否大于1/4长度
//    在实际代码中，可以只判断25%位置和50%位置的边界
//        如果满足就返回
//    如果不满足就直接返回75%位置
    public int findSpecialInteger2(int[] arr) {
        int left = 0;
        int right = 0;
        int len = arr.length >> 2;  //这行其实就是len = arr.length / 4
        for (int i = 1; i <= 2; i++) {
            int index = len * i;
            left = SearchLeft(arr, arr[index]);
            right = SearchRight(arr, arr[index]);
            if (right - left >= len) {
                return arr[index];
            }
        }
        return arr[len * 3];
    }

    int SearchLeft(int[] arr, int n) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= n) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    int SearchRight(int[] arr, int n) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] > n) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high - 1;
    }

}

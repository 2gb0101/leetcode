package Array.Easy;

//Offer: 一个长度固定的整数数组arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
//注意：请不要在超过该数组长度的位置写入元素
//要求：请对输入的数组就地进行上述修改，不要从函数返回任何东西

import java.util.LinkedList;

//Input: [1,0,2,3,0,4,5,0]
//Output: null
//Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
public class D24_1089_Duplicate_Zeros {
//    解法一（双指针法）：
    public void duplicateZeros1(int[] arr) {
        int n = arr.length;
        int i = 0, j = 0;
        while (j < n) {
            if (arr[i] == 0) ++j;
            ++i;
            ++j;
        }
        --i;    // i 回到最后一次合法的位置
        --j;    // j 同理，但 j 仍可能等于 n（例如输入 [0]）
        while (i >= 0) {
            if (j < n) arr[j] = arr[i];
            if (arr[i] == 0) arr[--j] = arr[i];
            --i;
            --j;
        }
    }

//    解法二（偏移量法）：
    public void duplicateZeros2(int[] arr) {
        int [] num = new int [arr.length];
        int [] arrcopy = new int[arr.length];

        for (int i = 0; i < arr.length; i++){
            //赋值一个与原数组一样的数组
            arrcopy[i] = arr[i];
        }
        int dis = 0;
        for (int i = 0; i < arr.length; i++){
            //记录因为0的出现，导致的每一个元素应该偏移的量dis，记录于num数组。
            if (arr[i] == 0){
                dis++;
            }
            num[i] = dis;
        }
        //如果没有0，直接返回了
        if (dis==0)return;
        //初始化原数组，置为0
        for (int i = 0; i < arr.length; i++){
            arr[i] = 0;
        }
        //偏移量作用
        for (int i = 0; i + num[i] < arr.length; i++){
            arr[i+num[i]] = arrcopy[i];
        }

    }

//    解法三（插入排序）：
    public void duplicateZeros3(int[] arr) {

        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 0){
                for (int j = arr.length - 1; j > i; j--){
                    arr[j] = arr[j-1];
                }
                i++;
            }

        }
    }

//    解法四（队列法）
    public void duplicateZeros4(int[] arr) {
        LinkedList<Integer> buf = new LinkedList<>();
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == 0) {
                buf.offer(0);
            }
            if(!buf.isEmpty()) {
                buf.offer(arr[i]);
                arr[i] = buf.poll();
            }
        }
        return;
    }
}

package Array.Easy;

//Offer：
//给你两个数组，arr1 和 arr2
//arr2 中的元素各不相同
//arr2 中的每个元素都出现在 arr1 中
//Target：
//对 arr1 中的元素进行排序
//使 arr1 中项的相对顺序和 arr2 中的相对顺序相同
//未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾
public class D24_1122_Relative_Sort_Array {
//    桶排序法：
//    先分配arr1的数组变成桶
//    然后按arr2的顺序收集桶中的数组
//    最后按升序顺序收集桶中剩余的数组
//
//    这个方法主要是利用了题目里的范围[0,1000]，比较耗内存
//    所以还可以想办法优化桶的个数
//    通过遍历数组
//    查找数组中最大的数和最小的数
//    确定统计的范围，减少内存占用
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        //题目的范围[0,1000]
        int[] m = new int[1001];

        for(int i = 0; i < arr1.length; i++) {
            m[arr1[i]]++;
        }

        int cnt = 0;
        for(int i = 0; i < arr2.length; i++) {
            while(m[arr2[i]] > 0) {
                arr1[cnt++] = arr2[i];
                m[arr2[i]]--;
            }
        }
        if(cnt <= arr1.length) {
            for(int i = 0; i < 1001; i++) {
                while(m[i] > 0) {
                    arr1[cnt++] = i;
                    m[i]--;
                }
            }
        }
        return arr1;
    }

//    上面的解法，申请空间时，是利用了题目声明的范围[0,1000]，为了优化空间，我们可以按下面这样写
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 确定统计的范围，减少内存占用
        int min = arr1[0], max = arr1[0];
        int i = 1;
        for(; i < arr1.length; i++) {
            if(arr1[i] < min) {
                min = arr1[i];
            }
            if(arr1[i] > max) {
                max = arr1[i];
            }
        }
        int[] tmp = new int[max - min + 1];
        for(i = 0; i < arr1.length; i++) {
            tmp[arr1[i] - min]++; // 统计arr1中各元素的数量
        }
        int index = 0, count = 0;
        for(i = 0; i < arr2.length; i++) { // 首次遍历处理出现在arr2的元素
            while(tmp[arr2[i] - min]-- > 0) {
                arr1[index++] = arr2[i];
            }
        }
        for(i = 0; i < tmp.length; i++) { // 第二次遍历处理arr2中没有的元素
            while(tmp[i]-- > 0) {
                arr1[index++] = i + min;
            }
        }
        return arr1;
    }
}

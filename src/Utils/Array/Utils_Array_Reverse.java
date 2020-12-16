package Utils.Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Utils_Array_Reverse {
//    Java 数组元素逆序Reverse的三种方式

    @Test
    public void test(){
        int[] array = new int[]{1,2,3,4,5};
        int[] res = reverse1(array);
        System.out.println(Arrays.toString(res));
    }

//    方案一：使用java自带方法
    public int[] reverse1(int[] originArray) {
        int length = originArray.length;
        int[] reverseArray = new int[length];
        ArrayList arraylist = new ArrayList();
        //Collections.reverse(List<?> list)
        // 参数是一个list,而并非是数组int[]
        // 所以需要先新建一个ArrayList存放原数组originArray的元素
        for (int i = 0; i < length; i++) {
            arraylist.add(originArray[i]); //存放元素
        }
        Collections.reverse(arraylist); //使用方法进行逆序
        //完成逆序后,可以保存到新数组reverseArray
        for (int i = 0; i < length; i++) {
            reverseArray[i] = (int) arraylist.get(i);
        }
        return reverseArray;
    }

//    方案二：产生一个新数组按逆序存放原数组的元素
    public int[]  reverse2(int[] originArray) {
        int length = originArray.length;
        int[] reverseArray = new int[length];
        for (int i = 0; i < length; i++) {
            reverseArray[i] = originArray[length - i - 1];
        }
        return reverseArray;
    }

//    方案三：将原数组通过前后交换实现逆序
    public int[] reverse3(int[] originArray) {
        int length = originArray.length;
        int[] reverseArray = new int[length];
        int temp = 0;
        for (int i = 0; i < length / 2; i++) {
            temp = originArray[i];
            reverseArray[i] = originArray[length - i - 1];
            reverseArray[length - i - 1] = temp;
        }
        return reverseArray;
    }

    /**
     * 翻转的范围为[start,end)
     */
    public static void reverse(int[] originArray, int start, int end) {
        int temp = 0;
        if(start > end || start < 0 || end > originArray.length) return;
        //x = end - start代表end走到start要 x 步
        //x + 1是为了统一 x 为奇/偶数的情况
        int len = end - start;
        int x =  (len + 1) / 2;
        for (int i = 0; i < x; i++) { //***
            temp = originArray[start + i];
            originArray[start + i] = originArray[end - 1 - i];
            originArray[end - 1 - i] = temp;
        }
    }
}

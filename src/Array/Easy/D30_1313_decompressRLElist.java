package Array.Easy;

//Offer：
//    一个以行程长度编码压缩的整数列表 nums
//    考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ）
//    每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表
//Target：返回解压后的列表
//
//示例：
//    输入：nums = [1,2,3,4]
//    输出：[2,4,4,4]
//    解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]
//    第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]
//    最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]
public class D30_1313_decompressRLElist {
    //难点在于声明返回数组的大小;
    //为了得到返回数组的大小,首先遍历一遍数组nums,计算索引为偶数的数字之和得到返回数组的大小len;
    //声明返回数组result的大小为len;
    //再遍历一遍数组,进行解码,解码原则为---索引为奇数i的数字nums[i]重复nums[i-1]次,并依次放入返回数组result中;
    //最后返回out;
    public int[] decompressRLElist(int[] nums) {
        int i=0;
        int len=0;
        while(i<nums.length){
            len+= nums[i];
            i+=2;
        }
        int[] result = new int[len];
        int index=0;
        i=0;
        while(i<nums.length){
            for(int k=0;k<nums[i];k++){
                result[index]=nums[i+1];
                index++;
            }
            i+=2;
        }
        return result;
    }
}

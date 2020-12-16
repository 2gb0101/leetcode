package Array.Middle;

//数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序
//之后再连接起来，使得连接的结果和按升序排序后的原数组相同
//我们最多能将数组分成多少块？
public class D22_769_maxChunksToSorted {
//    解法1：逻辑法
//    当前n个数中的最大值等于遍历到的下标时，这前n个元素才能作为一个块
    public int maxChunksToSorted1(int[] arr) {
        int count=0;
        int max=0;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);
            if(max==i){
                count+=1;
            }
        }
        return count;
    }

//    解法2：判断区间内元素是否合理，双指针
//    题目中规定，数组中的元素只能是[0, 1, ..., arr.length - 1]
//    所以对于数字i排序后其索引为i
//        那么对于数字arr[i]升序排序后索引为arr[i]
//        即，要完成分块，那么arr[i]这个数字所在块对应的区间右边界至少为arr[i]，该块的区间最小为[i, arr[i]]
//        否则对这个块排序后arr[i]是绝对不会放在正确的索引arr[i]处
//    但是为什么是至少呢？因为在区间[i, arr[i]]上，不确定是否有元素arr[j] > arr[i]
//        如果有，将这个块划分为[i, arr[i]]排序后，arr[i]这个元素不会被放在索引arr[i]处（被更大的元素占据了），不是正确位置
//        所以要得到最多能分为几块，就要尽可能将区间分得细碎
//            每一个小区间都需要保证
//            小区间的右边界索引刚好是小区间内最大的元素的值
//            否则无法还原成升序数组
//    有了这个指导思想，可以通过双指针一次遍历数组得到答案
//
//    首先，对于数字arr[i]，其可能最小的块为[i, arr[i]]
//    要去验证这个最琐碎的块是否满足题意
//        需要从int j = i + 1开始
//        遍历区间[i, arr[i]]去判断这个区间内最大的数是否为arr[i]
//            如果不是
//                即if (arr[j] > arr[i]) break
//                内循环会被中断, 此时j <= arr[i]
//                这种情况下[i, arr[i]]的划分不满足题意
//                将指针i移动到j，在下一次外循环中判断区间分割右边界是否为arr[i]
//            反之，说明区间[i, arr[i]]合理，区间块数+1，且指针i移动到j+1，在下一次外循环中继续去寻找最小的合理区间
//        外循环结束后，返回count
//    时间复杂度为O(n)，空间复杂度为O(1)
    public int maxChunksToSorted2(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ) {
            int j = i + 1;
            for (; j <= arr[i]; j++) {
                if (arr[j] > arr[i]) { // 在arr[i]到其正确位置的区间内，有比它大的数，不能分为一个块
                    break;
                }
            }
            if (j <= arr[i]) { // j <= arr[i]意味着：在arr[i]在其正确位置区间内，有比它大的数
                i = j; // 指针i移动到j，arr[i]成为当前最大的数字，下一次循环要去判断[j + 1,arr[i]]内有没有比它大的数
            } else {
                count++;
                i = arr[i] + 1;
            }
        }
        return count;
    }
}

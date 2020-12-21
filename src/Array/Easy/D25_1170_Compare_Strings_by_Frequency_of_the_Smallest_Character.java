package Array.Easy;

//Offer：
//定义一个函数f(s)，其中传入参数s是一个非空字符串；
//该函数的功能是统计s中（按字典序比较）最小字母的出现频次
//例如，若s = "dcce"
//那么f(s) = 2
//因为最小的字母是"c"，它出现了2 次。
//
//Target：
//给你两个字符串数组待查表queries和词汇表words
//请你返回一个整数数组answer作为答案
//其中每个answer[i]是满足f(queries[i])< f(W)的词的数目
//W是词汇表words中的词
public class D25_1170_Compare_Strings_by_Frequency_of_the_Smallest_Character {
//    统计queries和words中每个词的f(s)
//    进行比较即可
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int countMinQuery = countMinCode(queries[i]);
            for (String word : words) { //重复计算了
                if (countMinQuery < countMinCode(word)) {
                    result[i] ++;
                }
            }
        }
        return result;
    }

//    可对 numSmallerByFrequency() 重复计算的部分进行优化
    public int[] numSmallerByFrequency1(String[] queries, String[] words) {
        int[] result = new int[queries.length];
        int[] fw = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            fw[i] = countMinCode(words[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            int countMinQuery = countMinCode(queries[i]);

            for (int tmp : fw) {
                if (countMinQuery < tmp) {
                    result[i] ++;
                }
            }

        }
        return result;
    }

    /**
     * 统计最小出现频次
     * @param s
     * @return
     */
    public static int countMinCode(String s) {
        char min = s.charAt(0);
        int  count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < min) {
                min = s.charAt(i);
                count = 0;
            }
            if (s.charAt(i) == min) {
                count++;
            }
        }
        return count;
    }

}

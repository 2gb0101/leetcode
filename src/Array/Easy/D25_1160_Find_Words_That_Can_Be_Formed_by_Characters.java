package Array.Easy;

//Offer：
//给你一份『词汇表』（字符串数组）words和一张『字母表』（字符串）chars
//Target：
//假如你可以用chars中的『字母』（字符）拼写出 words中的某个『单词』（字符串）
//那么我们就认为你掌握了这个单词
//注意：每次拼写时，chars 中的每个字母都只能用一次。
//返回词汇表 words 中你掌握的所有单词的 长度之和

public class D25_1160_Find_Words_That_Can_Be_Formed_by_Characters {

//    补充一下如何利用clone方法复制数组：
//    clone() 方法也可以实现复制数组
//    是类 Object 中的方法，可以创建一个有单独内存空间的对象
//    因为数组也是一个 Object 类，因此也可以使用数组对象的 clone() 方法来复制数组。
//
//    clone() 方法的返回值是 Object 类型，要使用强制类型转换为适当的类型。
//    示例语句如下：
//    int[] targetArray=(int[])sourceArray.clone();
//    注意：目标数组如果已经存在，将会被重构。


//    辅助数组法：
//        （1）将所给字符按照ASCII值 - a的ASCII为下标进行记录，数值作为数量的记录
//        （2）遍历所需要计算的字符串数组，找到相关字母所对应的索引进行--并判断是否>0，进行计数
//        （3）判断计数器是否与该字符串的长度相同，相同即为可以组成该单词，则进行最终计数器++操作
    public int countCharacters(String[] words, String chars) {
        // toCharArray() 方法将字符串转换为字符数组
        char[] target = chars.toCharArray();
        int[] temp = new int[26];
        int result = 0;
        for (char c : target) {
            temp[c - 'a']++;
        }
        for (String word : words) {
            char[] src = word.toCharArray();
            int one = 0;
            int[] t = temp.clone();
            for (char c : src) {
                if(t[c - 'a']-- > 0){
                    one++;
                }
            }
            if (one == word.length()){
                result += one;
            }
        }
        return result;
    }
}

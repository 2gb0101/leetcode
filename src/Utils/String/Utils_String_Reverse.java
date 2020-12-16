package Utils.String;

public class Utils_String_Reverse {
//  方法1：最简单
    public static String reverse1(String str) {
        return new StringBuffer(str).reverse().toString();
    }

//    SringBuffer类和String一样，也用来代表字符串，相比String,
//    StringBuffer在进行字符串处理时，不生成新的对象，在内存使用上要优于String类
//    当遇到插入、删除等字符串操作时，可以考虑用StringBuffer

//    使用构造方法对StringBuffer初始化：
    StringBuffer s = new StringBuffer(); //初始化出的StringBuffer对象是一个空的对象
    StringBuffer s1 = new StringBuffer("abc"); //初始化出的StringBuffer对象的内容就是字符串”abc”

//    StringBuffer与String的转换：
//    虽然两者都是操作字符串，但属于不同的类，不能直接兼容，相互转换的方法为：
    String s2 = "abc";
    StringBuffer sBuff1 = new StringBuffer("123");
    StringBuffer sBuff2 = new StringBuffer(s);   //String转换为StringBuffer
    String s3 =sBuff2.toString();                //StringBuffer转换为String

//  方法2：最常用
    public static String reverse3(String s) {
        char[] array = s.toCharArray();
        String reverse = "";  //新建空字符串
        for (int i = array.length - 1; i >= 0; i--)
            reverse += array[i];
        return reverse;
    }

//  方法3：
    public static String reverse2(String s) {
        int length = s.length();
        String reverse = "";  //新建空字符串
        for (int i = 0; i < length; i++)
            reverse = s.charAt(i) + reverse;//在新字符串前面添加读取字符，实现翻转
        return reverse;
    }
}

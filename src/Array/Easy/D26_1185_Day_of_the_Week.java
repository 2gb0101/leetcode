package Array.Easy;

//Offer：
//给你一个日期
//输入为三个整数：day、month 和 year，分别表示日、月、年
//Target：
//请你设计一个算法来判断它是对应一周中的哪一天
//您返回的结果必须是这几个值中的一个?{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}
public class D26_1185_Day_of_the_Week {
//    只需要求出1971年1月1日是星期几
//    然后再计算出从今天距离1971年1月1日有多少天
//    根据天数取7的模
//    即可知道最终结果
//    基本上送分题
    public String dayOfTheWeek(int day, int month, int year) {
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] months = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        //1970.01.01是周四
        int start = 4;
        int sum = 0;

        for(int i = 1971 ;i < year; ++i){
            sum += dayOfyear(i);
        }
        for(int i = 1;i < month; ++i){
            if(i == 2 && isLeapYear(year)){
                sum += 29;
            }else{
                sum += months[i-1];
            }
        }

        sum += day;
        sum = sum%7;
        int now = (start + sum)%7;

        return days[now];
    }
    //判断该年是否是闰年
    boolean isLeapYear(int year){
        if(year%400 == 0){
            return true;
        }
        if(year%4 == 0 && year%100 != 0){
            return true;
        }else{
            return false;
        }
    }
    //判断一年有多少天
    int dayOfyear(int year){
        if(isLeapYear(year)){
            return 366;
        }else{
            return 365;
        }
    }

}

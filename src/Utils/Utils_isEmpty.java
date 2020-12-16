package Utils;

import java.util.Collection;
import java.util.Map;

public class Utils_isEmpty {
    public static boolean isEmpty(Object obj){
        return isEmpty(obj, false);
    }

    /**
     * @param flag 当Collection中有且仅有一个null对象时，若flag为true，则返回true（代表为空）
     */
    public static boolean isEmpty(Object obj, boolean flag) {
        if(null == obj){
            return true;
        }else if(obj instanceof String && ((String)obj).trim().length() == 0){
            return true;
        }else if(obj instanceof Collection<?> && ((Collection<?>)obj).size() == 0 || (flag && ((Collection<?>)obj).size() == 1) && (((Collection<?>) obj).contains(null))){
            return true;
        }else if(obj instanceof Map<?,?> && ((Map<?,?>)obj).size() == 0){
            return true;
        }else{
            return false;
        }
    }
}

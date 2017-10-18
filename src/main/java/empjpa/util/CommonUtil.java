package empjpa.util;


public class CommonUtil {
    /**
     * Created by admin on 2017/10/18.
     *
     * 判断一个字符串是否为空
     * @param  input 输入字符串
     * @return 如果为空 true，否则 false
     */
    public static boolean notempty(String input){
        return input != null && !input.isEmpty();
    }
}

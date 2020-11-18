import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author huaqiliang
 * @date: 2020/11/522:05
 * @Description:helloW&M
 * @Version:
 */

public class firstDemo {
    Properties properties = new Properties();
    public static void main(String[] args) {
        System.out.println("hello ! 小孟同学");
        int d = 2;
        System.out.println(d<<1);
        System.out.println(d + "=====" + (d|=(d<<1)));
        System.out.println(d);
        Map<String,String>  buildMap = new HashMap();
        handleMap(buildMap);
    }
    private static Map<String,String> handleMap(Map<String,String> map){
        Map<String,String>  buildMap = new HashMap();
        buildMap.put("name","华启亮");
        buildMap.put("otherName","毛牛");
        buildMap.put("sex","男");
        return null;
    }
}

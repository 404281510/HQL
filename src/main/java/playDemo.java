import java.io.*;
import java.util.Properties;

/**
 * @author huaqiliang
 * @date: 2020/11/522:16
 * @Description:写一些不同的代码
 * @Version:
 */

public class playDemo {
    private final static Properties properties = new Properties();

    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("user.dir"));
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + File.separator + "src/main/resource/hql.system.properties");
            Reader reader = new InputStreamReader(fileInputStream);
            properties.load(reader);
            System.out.println(properties.get("adress"));
            System.out.println(properties.get("name"));
            //System.out.println(System.getProperty("user.dir"));
            //BufferedReader br = new BufferedReader(fr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

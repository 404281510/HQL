package utils;

import java.io.*;
import java.util.Properties;

/**
 * @author huaqiliang
 * @date: 2020/11/1722:53
 * @Description:获取系统配置文件参数
 * @Version:
 */

public class SystemData {
    public static String DEFAULT_CHARSET = null;
    public static String KEY_AES = null;
    public static String KEY = null;
    public static String DB_URL = null;
    public static String DB_USER = null;
    public static String DB_PASSWORD = null;

    static {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        Reader reader = null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + File.separator + "src/main/resource/hql.system.properties");
            reader = new InputStreamReader(fileInputStream);
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SystemData.DEFAULT_CHARSET = properties.getProperty("DEFAULT_CHARSET");
        SystemData.KEY_AES = properties.getProperty("KEY_AES");
        SystemData.KEY = properties.getProperty("KEY");
        SystemData.DB_URL = properties.getProperty("DB_URL");
        SystemData.DB_USER = properties.getProperty("DB_USER");
        SystemData.DB_PASSWORD = properties.getProperty("DB_PASSWORD");
    }
}

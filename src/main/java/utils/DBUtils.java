package utils;

import com.sun.corba.se.spi.legacy.interceptor.UnknownType;
import entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huaqiliang
 * @date: 2020/11/1722:43
 * @Description:一个jdbc工具类
 * @Version:
 */

public class DBUtils {
    private static final String DB_URL = SystemData.DB_URL;
    private static final String DB_USER = SystemData.DB_USER;
    private static final String DB_PASSWORD = SystemData.DB_PASSWORD;

    private static Connection getConnect(){
        Connection connection = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static List<Object> getList(String sql){
        List<Object> list = new ArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnect();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @create by: huaqiliang
     * @description:  获取封装对应实体类的集合
     * @create time: 0:08 2020/11/18
     *
     * @ * @Param: null
     * @return
    **/
    public static List<?> getEntityList(String sql,Class c){
        List<Object> list = new ArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnect();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Object value = null;
                Object object = c.newInstance();
                Field[] fields = c.getDeclaredFields();
                for (Field field : fields) {

                    String fName = field.getName();
                    String fType = field.getType().getSimpleName();
                    String dbName = camelToDbName(fName);
                    if (!isExist(resultSet,dbName)){
                        continue;
                    }
                    String methodName = getSetMethod(fName);
                    Method m = c.getDeclaredMethod(methodName,field.getType());
                    if ("String".equals(fType)){
                        value = resultSet.getString(dbName);
                    }else if ("int".equals(fType)) {
                        value =resultSet.getInt(dbName);
                    }else if ("Date".equals(fType)) {
                        value = resultSet.getDate(dbName);
                    }
                    m.invoke(object,value);

                }
                list.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @create by: huaqiliang
     * @description: 驼峰命名转成数据库字段
     * @create time: 21:08 2020/11/18
     *
     * @ * @Param: null
     * @return
    **/
    private static String camelToDbName(String pName) throws Exception {
        char[] chars = pName.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++){
            if (65 <= chars[0] && 90 >= chars[0]) {
                throw new Exception("数据库对应字段");
            }else if (65 <= chars[i] && 90 >= chars[i]){
                sb.append("_").append(Character.toLowerCase(chars[i]));
            }else {
                sb.append(chars[i]);
            }
        }
        String result = sb.toString().toUpperCase();
        return result;
    }

    /**
     * @create by: huaqiliang
     * @description:获取set方法名
     * @create time: 21:17 2020/11/18
     *
     * @ * @Param: null
     * @return
    **/
    private static String getSetMethod(String fieldName) {
        String fieldMethodName = null;
        fieldMethodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return fieldMethodName;
    }

    /**
     * @create by: huaqiliang
     * @description:  判断结果集中是否存在该字段数据
     * @create time: 0:07 2020/11/18
     *
     * @ * @Param: null
     * @return
    **/
    private static boolean isExist(ResultSet resultSet,String dbName){
        try {
            resultSet.findColumn(dbName);
                return true;
        } catch (SQLException e) {
            System.out.println(dbName + "在数据库中不存在！");
            return false;
        }
    }

    public static void main(String[] args) {
        List<User> list = (List<User>) getEntityList("select * from hql_user", User.class);
        System.out.println("ok!");
    }
}

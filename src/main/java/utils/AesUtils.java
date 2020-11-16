package utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * @author huaqiliang
 * @date: 2020/11/1621:21
 * @Description:AES加密算法练习
 * @Version:
 */

public class AesUtils {

    private static String DEFAULT_CHARSET = null;
    private static String KEY_AES = null;
    private static String KEY = null;

    static {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        Reader reader =null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + File.separator + "src/main/resource/system.AES.properties");
            reader = new InputStreamReader(fileInputStream);
            properties.load(reader);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AesUtils.DEFAULT_CHARSET = properties.getProperty("DEFAULT_CHARSET");
        AesUtils.KEY_AES = properties.getProperty("KEY_AES");
        AesUtils.KEY = properties.getProperty("KEY");
    }

    /**
     * @create by: huaqiliang
     * @description:
     * @create time: 21:30 2020/11/16
     *
     * @ * @Param: 需要加密的字符串
     * @return
    **/
    public static String encrypt(String text){
        return  doAES(text,KEY,Cipher.ENCRYPT_MODE);
    }

    /**
     * @create by: huaqiliang
     * @description:
     * @create time: 21:30 2020/11/16
     *
     * @ * @Param: 需要加密的字符串
     * @return
     **/
    public static String decrypt(String text){
        return  doAES(text,KEY,Cipher.DECRYPT_MODE);
    }

    /**
     * @create by: huaqiliang
     * @description:
     * @create time: 22:22 2020/11/16
     * @param data 待处理数据
     * @param key  密钥
     * @param mode 加解密mode
     * @return
    **/
    private static String doAES(String data, String key, int mode) {
        try {
            if ( null == data  || "" == data || null == key || "" == key) {
                return null;
            }
            //判断是加密还是解密
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            //true 加密内容 false 解密内容
            if (encrypt) {
                content = data.getBytes(DEFAULT_CHARSET);
            } else {
                content = parseHexStr2Byte(data);
            }
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            kgen.init(128, new SecureRandom(key.getBytes()));
            //3.产生原始对称密钥
            SecretKey secretKey = kgen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, KEY_AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                //将二进制转换成16进制
                return parseByte2HexStr(result);
            } else {
                return new String(result, DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @create by: huaqiliang
     * @description:将2进制转换为16进制
     * @create time: 23:24 2020/11/16
     * @param buf
     * @return
    **/

    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * @create by: huaqiliang
     * @description:将16进制转换为二进制
     * @create time: 23:38 2020/11/16
     * @param hexStr
     * @return
     **/
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}

package algorithm;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 128位AES加密算法
 */
public class ASECryptUtil {
    /**
     * 加密
     *
     * @return
     */
    public static String encrypt(String content, String key) {
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            //根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //将加密后的数据转为字符串
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String content, String key){
        try {
            SecretKeySpec secretKeySpec = getSecretKeySpec(key);
            //根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //初始化密码器，第一个参数为加密模式，第二个为使用的key
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            //将加密并编码后的内容解码成字节数组
            byte[] byte_content = content.getBytes();
            //解密
            byte[] result = cipher.doFinal(byte_content);
            String re = new String (result,"utf-8");
            return re;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SecretKeySpec getSecretKeySpec(String key){
        SecretKeySpec secretKeySpec = null;
        try {
            //构成密钥生成器，指定AES算法，不区分大小写
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //根据pwd初始化密钥生成器
            //生成一个128位的随机源，根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            kgen.init(128, secureRandom);
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKeySpec;
    }
}

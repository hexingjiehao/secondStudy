package com.hydee.h3.gateway.util.crypt;

import com.hydee.common.beans.exception.HCloudRuntimeException;
import com.hydee.h3.gateway.enums.OpenApiMessageEnum;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class HMACCryptUtil {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    private static SecretKey secretKey;

    /**
     * 得到一个 指定算法密钥的密钥生成器
     * 生成一个密钥
     */
    static{
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(MAC_NAME);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        secretKey = keyGenerator.generateKey();
    }



    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param content 被签名的字符串
     * @param key  密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String key) {
        try {
            byte[] data = key.getBytes(ENCODING);
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(MAC_NAME);
            //用给定密钥初始化 Mac 对象
            mac.init(secretKey);

            byte[] text = content.getBytes(ENCODING);
            //完成 Mac 操作
            byte[] result = mac.doFinal(text);
            return new BASE64Encoder().encode(result);
        } catch (Exception e) {
            new HCloudRuntimeException(OpenApiMessageEnum.SIGNATURE_FAIL);
        }
        return null;
    }

}

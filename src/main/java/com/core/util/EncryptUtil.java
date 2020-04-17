package com.core.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * @Description 编码解码工具
 * @Author xg.chen
 * @Date 9:10 2020/4/2
**/
public class EncryptUtil {
    public static String encrypt(String data, String key) {

        String ivString = "0000000000000000";
        //偏移量
        byte[] iv = ivString.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes("UTF-8");
            int length = dataBytes.length;
            //计算需填充长度
            if (length % blockSize != 0) {
                length = length + (blockSize - (length % blockSize));
            }
            byte[] plaintext = new byte[length];
            //填充
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            //设置偏移量参数
            //IvParameterSpec ivSpec = new IvParameterSpec(iv);
            //cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryped = cipher.doFinal(plaintext);

            return Base64Coder.encodeLines(encryped);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String desEncrypt(String data, String key) {

        try {
            byte[] encryp = Base64Coder.decode(data);
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] original = cipher.doFinal(encryp);
            return new String(original);
        } catch (Exception e) {
        }
        return null;
    }


    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}

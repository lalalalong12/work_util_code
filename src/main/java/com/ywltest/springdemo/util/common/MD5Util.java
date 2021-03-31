package com.ywltest.springdemo.util.common;

import java.security.MessageDigest;

/**
 * @ProjectName: iot-application-gateway
 * @Package: com.encdata.iot.application.gateway.util
 * @ClassName: MD5Tool
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2020/11/20 14:47
 * @Version: 1.0
 */
public class MD5Util {


    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * 三次盐hash
     *
     * @param pawd
     *         明文密码
     * @param salt
     *         盐值
     * @return
     */
    public static String threeHash(String pawd, String salt) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        if (salt != null) {
            digest.reset();
            digest.update(salt.getBytes("utf-8"));
        }
        byte[] hashed = digest.digest(pawd.getBytes("utf-8"));
        int iterations = 3 - 1;
        //iterate remaining number:
        for (int i = 0; i < iterations; i++) {
            digest.reset();
            hashed = digest.digest(hashed);
        }
        return new String(byteToChar(hashed));
    }


    /**
     * 字节数组转为字符数组
     *
     * @param data
     * @return
     */
    private static char[] byteToChar(byte[] data) {

        int l = data.length;

        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return out;
    }


    /**
     * 创建加密密码
     * @return
     * @throws Exception
     */
    public static String createUser() throws Exception {
        String pawd ="Bocom_123";
        String salt = "admin";
        return threeHash(pawd, salt);
    }


    /**
     * 测试
     * @param args
     */
//    public static void main(String[] args) {
//        try {
//            String user = createUser();
//            System.out.println(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

    // 测试主函数
    public static void main(String args[]) {
        String password = "Bocom_123";
        String gatewayId = "gatewayId";
        String inputStr = password+gatewayId;
        System.out.println("原始：" + password);
        System.out.println("加密的：" + convertMD5(inputStr));
        System.out.println("解密的：" + convertMD5(convertMD5(inputStr)));
        System.out.println("原始：" + convertMD5(convertMD5(inputStr)).replace(gatewayId,""));
    }
}

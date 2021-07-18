package common.util.common;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @ClassName AES.java
 * @Description TODO
 * @Author herong
 * @Version 1.0.0
 * @createtime 2020/4/24 17:54
 */
public class AES {
    private static String iv = "ABCDEF1234123412";//偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
    private static String Algorithm = "AES";
    private static String AlgorithmProvider = "AES/CBC/PKCS5Padding"; //算法/模式/补码方式

    public static byte[] generatorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm);
        keyGenerator.init(128);//默认128，获得无政策权限后可为192或256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static IvParameterSpec getIv() throws UnsupportedEncodingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
        System.out.println("偏移量：" + byteToHexString(ivParameterSpec.getIV()));
        return ivParameterSpec;
    }

    public static byte[] encrypt(String src, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
        return cipherBytes;
    }

    public static byte[] decrypt(String src, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);

        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] hexBytes = hexStringToBytes(src);
        byte[] plainBytes = cipher.doFinal(hexBytes);
        return plainBytes;
    }

    /**
     * 将byte转换为16进制字符串
     *
     * @param src
     * @return
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    public static String randomHexString(int len) {
        try {
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < len; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return null;
    }


    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String keyStr = "1234123412ABCDEF";
//        byte[] key = keyStr.getBytes("utf-8");
//        try {
//            // 密钥必须是16的倍数
//            String src = "何荣123333333333";
//            System.out.println("密钥:"+byteToHexString(key));
//            System.out.println("原字符串:"+src);
//
//
//            String enc = byteToHexString(encrypt(src, key));
//            System.out.println("加密："+enc);
//            System.out.println("解密："+new String(decrypt(enc, key), "utf-8"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//
//        @Override
//        public String getKey() throws NoSuchAlgorithmException {
//            String key = AES.byteToHexString(AES.generatorKey());
//            return AES.randomHexString(16);
//        }

//        @Override
//        public String decryptionInfo(String content, String keyStr) throws UnsupportedEncodingException {
//            byte[] key = keyStr.getBytes("utf-8");
//            String decryptionText = "";
//            try {
//                decryptionText = new String(AES.decrypt(content, key), "utf-8");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return decryptionText;
//        }
////
//
//        @Override
//        public String encryptionInfo(String content, String keyStr) throws UnsupportedEncodingException {
//            byte[] key = keyStr.getBytes("utf-8");
//            String enc = "";
//            try {
//                enc = AES.byteToHexString(AES.encrypt(content, key));
//                System.out.println("加密："+enc);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return enc;
//        }
//}
}

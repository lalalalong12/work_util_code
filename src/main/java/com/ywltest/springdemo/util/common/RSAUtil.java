package com.ywltest.springdemo.util.common;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RSA.java
 * @Description 非对称加密算法
 * @Author yangwenlong
 * @Version 1.0.0
 * @createtime 2020/4/24 17:54
 */
public class RSAUtil {


    /*
    * 私钥
    * */

    private final static  String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJBGFlwVGLqPVg0qsgiHacACEm2uLgOiBiNQ8vDRbEiHwuS63hY6LOp+U+CTFLowoo1JQzHxasncBadJVXG8nQQdYhijoi11BV1RM6WEQclrSW6j8Zb4knztj3tl6z5m/yIRompP2zUJSglLv0Q0JTOb/YkADZeTBPT+piJmpgvbAgMBAAECgYBwzEtZDSl7Wuu1eaceIfkdJMGRvTe+oWPZIsX+YE4f2v0WZb+Tc0KAx+n+UCBJfhLpEdmWZ6DNV9eXti82sHe1dVKCHISxgeV1GBmlzuQ/42dW1nQG2PmX1ILbIoslOJPk1hdb2jlGPA/VUt+EsfnwJkFnzbk8TSQgPPleW04ncQJBAPVypP4uxirB0KXS5/Gqe78arreh79lM9nYJUm8pwtvumb26beMFewTfRNlaUyA1s5EAmGoRueEN7Bwio8V2IEMCQQCWefBn9/ZeP7KNhFV4HvOAxQEI1xAsEIvtXamiRhPolnfrmsuMqPVcQTtpptlCL+kU+kgRjz0V6/TMs8VurZiJAkBlg1nr6qp5Cpnhy120nJ77heyW9Dm/tM4GOYrHgQ0fawpY5t8F0sFzXWgbQPk6TBuNMCLZU6V+CAUMCeOUCmwDAkEAjm7p4l/R8jB1AINsq4EQmKjha862Xh9jc9Eeip2uppAW/wrM9V3QsfyFEmJ+b6oOBG9L3GMrPR7V3xWjIjprkQJAe7ofOVDKLchq91vSHspj/k5ouau21lDe88wm7FE11pcMJFQY1dk1Wx/FGaZBNCxLv/dNn1vmTUbhyuYiOas5yw==";
    /**
     * 公钥
     */
    private final static  String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQRhZcFRi6j1YNKrIIh2nAAhJtri4DogYjUPLw0WxIh8Lkut4WOizqflPgkxS6MKKNSUMx8WrJ3AWnSVVxvJ0EHWIYo6ItdQVdUTOlhEHJa0luo/GW+JJ87Y97Zes+Zv8iEaJqT9s1CUoJS79ENCUzm/2JAA2XkwT0/qYiZqYL2wIDAQAB";


    /**
     * 私钥解密
     * @param rsaAuth
     * @return
     */
    public static String decrypt(String rsaAuth) {

        RSA rsa = new RSA(PRIVATE_KEY, null);
        String decrypt = rsa.decryptStr(rsaAuth, KeyType.PrivateKey);

        return StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
    }




    /**
     * 公钥加密
     * @param map
     * @return
     */
    public static String encryptMap(Map map) {

        RSA rsa = new RSA(null, PUBLIC_KEY);
        String authAndDate = JSONUtil.toJsonStr(map);
        byte[] aByte =  StrUtil.bytes(authAndDate, CharsetUtil.CHARSET_UTF_8);
        return rsa.encryptBase64(aByte, KeyType.PublicKey);
    }

    /**
     * 公钥加密密码
     * @param pw
     * @return
     */
    public static String encryptPw(String pw) {

        RSA rsa = new RSA(null, PUBLIC_KEY);
        byte[] aByte =  StrUtil.bytes(pw, CharsetUtil.CHARSET_UTF_8);
        return rsa.encryptBase64(aByte, KeyType.PublicKey);
    }

    /**
     * 公钥加密
     * @param auth
     * @return
     */
    public static String encrypt(String auth) {

        RSA rsa = new RSA(null, PUBLIC_KEY);

        Map<String,String> map = new ConcurrentHashMap<>(2);
        map.put("auth",auth);
        map.put("time", String.valueOf(System.currentTimeMillis()));
        String authAndDate = JSONUtil.toJsonStr(map);
        byte[] aByte =  StrUtil.bytes(authAndDate, CharsetUtil.CHARSET_UTF_8);
        return rsa.encryptBase64(aByte, KeyType.PublicKey);
    }


    /**
     * 生成公私钥
     */
    public static void createRsaKey() {
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey aPrivate = pair.getPrivate();
        PublicKey aPublic = pair.getPublic();

        String aPrivateString = Base64.encode(aPrivate.getEncoded());

        String aPublicString = Base64.encode(aPublic.getEncoded());
        System.out.println(aPrivateString);
//打印私钥
        System.out.println(aPublicString);
    }


    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {


        createRsaKey();



    }



}

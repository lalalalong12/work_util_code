package com.ywltest.springdemo.service.mqtt.mqttpw;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CustomX509TrustManager extends X509ExtendedTrustManager {

    //根证书认证
    private X509TrustManager rootTrusm;

    public CustomX509TrustManager() throws Exception {
        //public.pem需根据实际证书文件名和路径进行
        InputStream in = this.getClass().getResourceAsStream("/www.smartmetering.top.pem");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate ca = null;
        try {
            ca = cf.generateCertificate(in);
        } catch (CertificateException e) {
            throw e;
        } finally {
            in.close();
        }
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        rootTrusm = (X509TrustManager) tmf.getTrustManagers()[0];

    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType,
                                   Socket socket) {
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType,
                                   SSLEngine engine) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType,
                                   Socket socket) throws CertificateException {

        //验证服务器证书合法性
        rootTrusm.checkServerTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType,
                                   SSLEngine engine) throws CertificateException {
        //验证服务器证书合法性
        rootTrusm.checkServerTrusted(chain, authType);
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
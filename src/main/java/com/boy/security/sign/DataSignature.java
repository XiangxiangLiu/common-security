package com.boy.security.sign;

import com.boy.security.util.Base64Util;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 常用签名算法：RSA、DSA、ECDSA
 *      RSA：非对称算法，基于RSA算法的消息摘要算法，如MD5、SHA1、SHA256
 *      DSA: 数字签名算法，DSA仅包含数字签名，不包含加解密
 *      ESDSA：椭圆曲线数字签名算法，速度快、强度高、签名短
 *
 * RSA签名流程：
 *      发送方 —> 构建密钥对 -> 公钥给对方-> 私钥签名 -> 发送签名、原文 -> 接收方
 *      接收方 -> 公钥、签名数据
 */
public class DataSignature {

    private byte[] privateKey;
    private byte[] publicKey;
    private String keyPairAlgorithm;
    private String signAlgorithm;

    public DataSignature(String keyPairAlgorithm, int keySize, String signAlgorithm) {
        try {
            this.keyPairAlgorithm = keyPairAlgorithm;
            this.signAlgorithm = signAlgorithm;
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyPairAlgorithm);
            keyPairGenerator.initialize(keySize);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate().getEncoded();
            publicKey = keyPair.getPublic().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String sign(String srcData) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(this.keyPairAlgorithm);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance(this.signAlgorithm);
        signature.initSign(privateKey);
        signature.update(srcData.getBytes());
        byte[] signData = signature.sign();
        return Base64Util.encode(signData);
    }

    public boolean verify(String srcData, String signDataB64) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(this.keyPairAlgorithm);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(this.signAlgorithm);
        signature.initVerify(publicKey);
        signature.update(srcData.getBytes());
        return signature.verify(Base64Util.decode(signDataB64));
    }

    public static void main(String[] args) throws  Exception {
        String srcData = "liuxiangxiang1";
        System.out.println("srcData = " + srcData);
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("----------- SHA256withRSA --------------");
        String keyPairAlgorithm = "RSA";
        int keySize = 2048;
        String signAlgorithm = "SHA256withRSA";
        DataSignature signData = new DataSignature(keyPairAlgorithm, keySize, signAlgorithm);
        String signDataB64 = signData.sign(srcData);
        System.out.println("signDataB64 = " + signDataB64);
        boolean verify = signData.verify(srcData, signDataB64);
        System.out.println("verify = " + verify);

        System.out.println("----------- SHA256withDSA --------------");
        keyPairAlgorithm = "DSA";
        keySize = 2048;
        signAlgorithm = "SHA256withDSA";
        signData = new DataSignature(keyPairAlgorithm, keySize, signAlgorithm);
        signDataB64 = signData.sign(srcData);
        System.out.println("signDataB64 = " + signDataB64);
        verify = signData.verify(srcData, signDataB64);
        System.out.println("verify = " + verify);

        System.out.println("----------- SHA256withECDSA --------------");
        keyPairAlgorithm = "EC";
        keySize = 256;
        signAlgorithm = "SHA256withECDSA";
        signData = new DataSignature(keyPairAlgorithm, keySize, signAlgorithm);
        signDataB64 = signData.sign(srcData);
        System.out.println("signDataB64 = " + signDataB64);
        verify = signData.verify(srcData, signDataB64);
        System.out.println("verify = " + verify);
    }
}

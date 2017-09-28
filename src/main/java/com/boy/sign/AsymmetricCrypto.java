package com.boy.sign;

import com.boy.util.Base64Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 加密算法：
 *      DH：密钥交换算法
 *      RSA：基于因子分解。公钥解密，私钥解密
 *      ELGamel：基于离散数
 *      ECC：椭圆曲线加密
 * DH加密流程：
 *      初始化DH密钥对：
 *          发送方：构建发送方密钥 -> 公布发送方密钥给接收方 -> 使用接收者公钥构建发送本地密钥
 *          接收方：使用发送方密钥构建接收方密钥 -> 公布接收者公钥给对方 -> 构建接收方本地密钥
 *      DH算法加密消息传递：
 *          发送方：使用本地密钥加密消息 -> 发送密钥消息给接收方
 *          接收方：使用本地密钥解密消息
 * RSA加密流程：
 *      公钥加密，私钥解密：
 *          发送方：接收方公钥加密消息 -> 发送加密消息给接收方
 *          接收方：使用私钥解密消息
 */
public class AsymmetricCrypto {

    public static void main(String[] args) throws Exception{
        String srcData = "liuxiangxiang1";
        RSACrpyto rsaCrpyto = new RSACrpyto();

        String cryptoDataB64 = rsaCrpyto.encrypt(srcData);
        System.out.println("crypoData = " + cryptoDataB64);
        String data = rsaCrpyto.decrypt(cryptoDataB64);
        System.out.println("srcData = " + data);
    }


    static class RSACrpyto {
        private byte[] privateKey;
        private byte[] publicKey;

        RSACrpyto() throws NoSuchAlgorithmException {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate().getEncoded();
            publicKey = keyPair.getPublic().getEncoded();
        }

        public String encrypt(String srcData) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] crypto = cipher.doFinal(srcData.getBytes());
            return Base64Util.encode(crypto);
        }

        public String decrypt(String cryptoB64) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] srcData = cipher.doFinal(Base64Util.decode(cryptoB64));
            return new String(srcData, "UTF-8");
        }
    }
}

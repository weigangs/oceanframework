//package com.lkyl.oceanframework.common.utils.utils;
//
//import javax.crypto.Cipher;
//import java.io.ByteArrayOutputStream;
//import java.security.*;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
///**
// * TODO
// *
// * @version 1.0
// * @author: nicholas
// * @createTime: 2022年05月17日 22:19
// */
//public class RsaUtil2 {
//
//    /**
//     * RSA最大加密明文大小
//     */
//    private static final int MAX_ENCRYPT_BLOCK = 117;
//
//    /**
//     * RSA最大解密密文大小
//     */
//    private static final int MAX_DECRYPT_BLOCK = 128;
//
//    /**
//     * 获取密钥对
//     *
//     * @return 密钥对
//     */
//    public static KeyPair getKeyPair() throws Exception {
//        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//        generator.initialize(1024);
//        return generator.generateKeyPair();
//    }
//
//    /**
//     * 获取私钥
//     *
//     * @param privateKey 私钥字符串
//     * @return
//     */
//    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        byte[] decodedKey = Base64.decode(privateKey);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
//        return keyFactory.generatePrivate(keySpec);
//    }
//
//    /**
//     * 获取公钥
//     *
//     * @param publicKey 公钥字符串
//     * @return
//     */
//    public static PublicKey getPublicKey(String publicKey) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        byte[] decodedKey = Base64.decode(publicKey);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
//        return keyFactory.generatePublic(keySpec);
//    }
//
//    /**
//     * RSA加密
//     *
//     * @param data 待加密数据
//     * @param publicKey 公钥
//     * @return
//     */
//    public static String encrypt(String data, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        int inputLen = data.getBytes().length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offset = 0;
//        byte[] cache;
//        int i = 0;
//        // 对数据分段加密
//        while (inputLen - offset > 0) {
//            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
//                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offset = i * MAX_ENCRYPT_BLOCK;
//        }
//        byte[] encryptedData = out.toByteArray();
//        out.close();
//        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
//        // 加密后的字符串
//        return Base64.encode(encryptedData);
//    }
//
//    /**
//     * RSA解密
//     *
//     * @param data 待解密数据
//     * @param privateKey 私钥
//     * @return
//     */
////    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
////        Cipher cipher = Cipher.getInstance("RSA");
////        cipher.init(Cipher.DECRYPT_MODE, privateKey);
////        byte[] dataBytes = Base64.decode(data);
////        int inputLen = dataBytes.length;
////        ByteArrayOutputStream out = new ByteArrayOutputStream();
////        int offset = 0;
////        byte[] cache;
////        int i = 0;
////        // 对数据分段解密
////        while (inputLen - offset > 0) {
////            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
////                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
////            } else {
////                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
////            }
////            out.write(cache, 0, cache.length);
////            i++;
////            offset = i * MAX_DECRYPT_BLOCK;
////        }
////        byte[] decryptedData = out.toByteArray();
////        out.close();
////        // 解密后的内容
////        return new String(decryptedData, "UTF-8");
////    }
//
//    /**
//     * 签名
//     *
//     * @param data 待签名数据
//     * @param privateKey 私钥
//     * @return 签名
//     */
//    public static String sign(String data, PrivateKey privateKey) throws Exception {
//        byte[] keyBytes = privateKey.getEncoded();
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PrivateKey key = keyFactory.generatePrivate(keySpec);
//        Signature signature = Signature.getInstance("MD5withRSA");
//        signature.initSign(key);
//        signature.update(data.getBytes());
//        return new String(Base64.encode(signature.sign()));
//    }
//
//    /**
//     * 验签
//     *
//     * @param srcData 原始字符串
//     * @param publicKey 公钥
//     * @param sign 签名
//     * @return 是否验签通过
//     */
//    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
//        byte[] keyBytes = publicKey.getEncoded();
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey key = keyFactory.generatePublic(keySpec);
//        Signature signature = Signature.getInstance("MD5withRSA");
//        signature.initVerify(key);
//        signature.update(srcData.getBytes());
//        return signature.verify(Base64.decode(sign));
//    }
//
//    /**
//     *
//     * @param str        内容
//     * @param privateKey 私钥key用于解密
//     * @return
//     * @throws Exception
//     */
//    public static String decrypt(String str, String privateKey) throws Exception{
//        //64位解码加密后的字符串
//        byte[] inputByte = org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes("UTF-8"));
//        //base64编码的私钥
//        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(privateKey);
//        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
//        //RSA解密
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, priKey);
//        String outStr = new String(cipher.doFinal(inputByte));
//        return outStr;
//    }
//
//    public static void main(String[] args) {
//        try {
//
//            // RSA解密
//            String decryptData = decrypt("nB9ZupXTKtzx8ZZQjk9Cp79kDXtd58419aMOyfvSfp/WM7OHC3sp3aqCfXDbL5TNHSWJFQulZraYF3g8zU5v2SHN+5nFjP9TFN8GusJ5MHchVzqEOPLWgQ1Q3DLEfZM7sA49+tBwlj0eMlT8/5MCaz6VeGc4UCSegLGGl+MPFls=",
//                    "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOEsITHctTlSwELj+RbXptFwJJjfAZw1J0ejPWOSfqywN3po5RmzLWLmnWhvsCFb1hNvNrSOK6kVesg0FxjUIm/bPQzVF/AcXkZtNTosSgwk7espbg9N7/fBrhbX4emFS6ZCpr+GAks0p5PBVnoN2FXX3Hzb/rHzgzhDok8jFtD9AgMBAAECgYA4G1DiJcaaU/5ILJoCkRWmFjVoShkSnUP+W7SOPRCHYxlPzRdZAgaLID+UqE/Q4BtFmG6fKtXCOfHwNNqUezdhA/7r6+3hiaK949FtUemmzMeVdqA+FuXx0mXkEJqn8BiCyn5se7WC713ePY5YDbBwVOI9EAO0wGYsyTELR8a9GQJBAP4A5+Nzx2uviFCLUgCbPMZF5Ps0tARvG1kqxwfGUlJqnvjSUJoaChABtUvqg/QwNW0jvjpgNtNdMUq1Qnw9LmcCQQDi8TYMq8E7UppELfsOQhjK4c318w0a+XdJ0pDU/NMSY9IY7lDaASuW8xkxSCFuFWSG5j07bS53C6P37OKgG977AkBNPElGyHXjMMTqePK+bHXWdHpkSGpUztQqEO/kVVHC7djZIFqSAUj+BQbzxqPJJL+aKDw30/nX24aZiPRmgtQRAkEAvRAC3U+BbbCFQGOmEdzS1sKDWXEg6+YEkQXRDv+JwHpUn9x6kwQCkoD37eyPnSxJUXEidg2hdh/GfFdm/cf6XQJBAKEN/moacxZZJb0iPfau1I6vV27vlzVLfKTjXkz9K57Nd2euPND4sS+/NnlQjFsFV36FBTLSyFc1j4Fe9DooABQ=");
//            System.out.println("解密后内容:" + decryptData);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.print("加解密异常");
//        }
//    }
//}

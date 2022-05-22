package com.lkyl.oceanframework.common.utils.utils;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
 * rsa加密
 * 此类主要针对于jsencrypt.js给明文加密，server端java解密
 * @author liangjiawei
 *
 */
@SuppressWarnings("restriction")
public class RSAUtil {
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public static void main(String[] args) {
        try {

            // RSA解密
            String decryptData = decrypt("3uhXMDvkpQ0Q2+jWXb2epeaGyqYomObS6Lgo4f3jDsuw5cSLcsRHM5nzi+jdwUETZ9AxK0cd9IOkRYRK7r86P8P4i2Uf0g7ilXnsggnKmEUyxJ5tLrEC0PzAAldtANQAIJiNIRf1JK1aDYuoR3ax0zpwHzu31HcGHORXdDTZa00=",
                    "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOEsITHctTlSwELj+RbXptFwJJjfAZw1J0ejPWOSfqywN3po5RmzLWLmnWhvsCFb1hNvNrSOK6kVesg0FxjUIm/bPQzVF/AcXkZtNTosSgwk7espbg9N7/fBrhbX4emFS6ZCpr+GAks0p5PBVnoN2FXX3Hzb/rHzgzhDok8jFtD9AgMBAAECgYA4G1DiJcaaU/5ILJoCkRWmFjVoShkSnUP+W7SOPRCHYxlPzRdZAgaLID+UqE/Q4BtFmG6fKtXCOfHwNNqUezdhA/7r6+3hiaK949FtUemmzMeVdqA+FuXx0mXkEJqn8BiCyn5se7WC713ePY5YDbBwVOI9EAO0wGYsyTELR8a9GQJBAP4A5+Nzx2uviFCLUgCbPMZF5Ps0tARvG1kqxwfGUlJqnvjSUJoaChABtUvqg/QwNW0jvjpgNtNdMUq1Qnw9LmcCQQDi8TYMq8E7UppELfsOQhjK4c318w0a+XdJ0pDU/NMSY9IY7lDaASuW8xkxSCFuFWSG5j07bS53C6P37OKgG977AkBNPElGyHXjMMTqePK+bHXWdHpkSGpUztQqEO/kVVHC7djZIFqSAUj+BQbzxqPJJL+aKDw30/nX24aZiPRmgtQRAkEAvRAC3U+BbbCFQGOmEdzS1sKDWXEg6+YEkQXRDv+JwHpUn9x6kwQCkoD37eyPnSxJUXEidg2hdh/GfFdm/cf6XQJBAKEN/moacxZZJb0iPfau1I6vV27vlzVLfKTjXkz9K57Nd2euPND4sS+/NnlQjFsFV36FBTLSyFc1j4Fe9DooABQ=");
            System.out.println("解密后内容:" + decryptData);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}

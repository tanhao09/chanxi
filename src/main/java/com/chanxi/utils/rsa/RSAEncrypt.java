package com.chanxi.utils.rsa;

import com.chanxi.utils.MessageDigestUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAEncrypt {
    private static Logger logger=Logger.getLogger(RSAEncrypt.class);
    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static RSAEncrypt loginRSAEncrypt = null;
    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;
    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 字节数据转十六进制字符串
     *
     * @param data 输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                //stringBuilder.append(' ');
            }
        }

        return stringBuilder.toString();
    }

    public static byte[] stringToByteArray(String str) {
        final byte[] byteArray = new byte[str.length() / 2];
        int k = 0;

        for (int i = 0; i < byteArray.length; i++) {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(str.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(str.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }

        return byteArray;
    }

    public static RSAEncrypt getLoginRSAEncrypt() {
        if (loginRSAEncrypt == null) {
            loginRSAEncrypt = new RSAEncrypt();

            try {
                loginRSAEncrypt.loadPublicKey(RSAEncrypt.class.getClassLoader().getResourceAsStream("conf/login_rsa.pub"));
                System.out.println("加载公钥成功");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("加载公钥失败");
            }

            // 加载私钥
            try {
                loginRSAEncrypt.loadPrivateKey(RSAEncrypt.class.getClassLoader().getResourceAsStream("conf/login_rsa.key"));

                System.out.println("加载私钥成功");
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("加载私钥失败");
            }
        }

        return loginRSAEncrypt;
    }

    public static void setLoginRSAEncrypt(RSAEncrypt loginRSAEncrypt) {
        RSAEncrypt.loginRSAEncrypt = loginRSAEncrypt;
    }

    public static void main(String[] args) {
        RSAEncrypt rsaEncrypt = RSAEncrypt.getLoginRSAEncrypt();

        // 测试字符串
        String username = "15801207618";
        String pwd = MessageDigestUtils.md5("1234567890123456");
        String str = username + ":" + pwd + ":abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

        try {

            System.out.println("source:" + str);
            String encryStr = rsaEncrypt.encryptString(str);
            System.out.println("加密后:" + encryStr);
            encryStr = "jIGd91YxnfxpxgyTAXq2cU/Rhaq1lRzGkdkchMeqAVmOc9I24UAuoiLjoBMDGq+s8lMz2M4Z3e154H4W1HGD0Pcuc7bCEIek3zt6RJdOFuACSn6NhDB1btTbtdWPn5Om8CioinGEPEulH0mwNcVTbWTP4brqqe6dzM/wndKNBWU=";
            String decryStr = rsaEncrypt.decryptString(encryStr);
            System.out.println("解密后：" + decryStr);
            /*
            // 加密
			byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(),
					encryptStr.getBytes("UTF-8"));
			// 解密
			byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(),
					cipher);
			System.out.println("密文长度:" + cipher.length);
			System.out.println();
			System.out.println(RSAEncrypt.byteArrayToString(cipher));
			System.out.println("明文长度:" + plainText.length);
			System.out.println(RSAEncrypt.byteArrayToString(plainText));
			System.out.println(new String(plainText));
			*/
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * 获取私钥
     *
     * @return 当前的私钥对象
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * 获取公钥
     *
     * @return 当前的公钥对象
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 随机生成密钥对
     */
    public void genKeyPair() {
        KeyPairGenerator keyPairGen = null;

        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.toString(),e);
        }

        keyPairGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();

        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine;
            StringBuilder sb = new StringBuilder();

            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') continue;

                sb.append(readLine);
                sb.append('\r');
            }

            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(String publicKeyStr) throws Exception {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);

            this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (IOException e) {
            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @param in 私钥文件名
     * @throws Exception
     */
    public void loadPrivateKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine;
            StringBuilder sb = new StringBuilder();

            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') continue;

                sb.append(readLine);
                sb.append('\r');
            }

            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            logger.error(e.toString(),e);
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            logger.error(e.toString(),e);
            throw new Exception("私钥输入流为空");
        }
    }

    public void loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (IOException e) {
            throw new Exception("私钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return byte[]
     * @throws Exception 加密过程中的异常信息
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        if (publicKey == null) throw new Exception("加密公钥为空, 请设置");

        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(plainTextData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            logger.error(e.toString(),e);
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {

        if (privateKey == null) throw new Exception("解密私钥为空, 请设置");

        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return cipher.doFinal(cipherData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            logger.error(e.toString(),e);
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    public String encryptString(String str) {
        String encryStr = null;

        try {
            byte[] cipher = this.encrypt(this.getPublicKey(), str.getBytes("UTF-8"));
            encryStr = (new BASE64Encoder()).encodeBuffer(cipher);
            //encryStr = this.byteArrayToString(cipher);
        } catch (Exception e) {
            logger.error(e.toString(),e);
        }

        return encryStr;
    }

    public String decryptString(String encryStr) {
        String str = null;

        try {
            byte[] cipher = (new BASE64Decoder()).decodeBuffer(encryStr);
            //byte[] cipher = this.stringToByteArray(encryStr);
            byte[] txt = this.decrypt(this.getPrivateKey(), cipher);

            str = new String(txt, "UTF-8");
        } catch (Exception e) {
            logger.error(e.toString(),e);
        }

        return str;
    }
}

package com.chanxi.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author baboy
 */
public final class MessageDigestUtils {
    @Autowired
    private static Logger logger=Logger.getLogger(MessageDigestUtils.class);

    /**
     * 全局数组*
     */
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * 返回形式为数字跟字符串
     *
     * @param bByte
     * @return
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param bByte
     * @return
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String encode(String str, String algorithm, boolean lowercase) {
        String code = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            code = byteToString(md.digest(str.getBytes()));
            if (lowercase) {
                code = code.toLowerCase();
            } else {
                code = code.toUpperCase();
            }
        } catch (NoSuchAlgorithmException ex) {
           logger.error(ex.toString(),ex);
        }
        return code;
    }

    /**
     * MD5加密
     *
     * @param str       待加密的字符串
     * @param lowerCase 大小写
     * @return
     */
    public static String md5(String str, boolean lowerCase) {
        return encode(str, "MD5", lowerCase);
    }

    public static String md5(String str) {
        return encode(str, "MD5", true);
    }

    public static String sha256(String str, boolean lowerCase) {
        return encode(str, "SHA-256", lowerCase);
    }

    public static String sha256(String str) {
        return encode(str, "SHA-256", true);
    }

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(MessageDigestUtils.md5("123456"));
        System.out.println(MessageDigestUtils.sha256("123456"));
        System.out.println(d.getTime());
    }

}

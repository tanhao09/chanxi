package com.chanxi.utils;

import com.google.common.base.Charsets;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class Base62Utils {
    private static char[] encodes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static byte[] decodes = new byte[256];

    static {
        for (int i = 0; i < encodes.length; i++) {
            decodes[encodes[i]] = (byte) i;
        }
    }

    public static String encode(String data) { return encode(data, Charsets.UTF_8); }

    public static String encode(String data, Charset charset) {
        if (data == null) return "";
        if (charset == null) charset = Charsets.UTF_8;

        return encode(data.getBytes(charset));
    }

    public static String encode(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        int pos = 0, val = 0;

        for (byte aData : data) {
            val = (val << 8) | (aData & 0xFF);
            pos += 8;

            while (pos > 5) {
                char c = encodes[val >> (pos -= 6)];

                sb.append(c == 'i' ? "ia" : c == '+' ? "ib" : c == '/' ? "ic" : c);

                val &= ((1 << pos) - 1);
            }
        }

        if (pos > 0) {
            char c = encodes[val << (6 - pos)];

            sb.append(c == 'i' ? "ia" : c == '+' ? "ib" : c == '/' ? "ic" : c);
        }

        return sb.toString();
    }

    public static byte[] decode(String data) { return decode(data.toCharArray()); }

    public static String decode(String data, Charset charset) {
        if (data == null) return "";
        if (charset == null) charset = Charsets.UTF_8;

        byte[] buffer = decode(data.toCharArray());

        return new String(buffer, charset);
    }

    public static byte[] decode(char[] data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
        int pos = 0, val = 0;

        for (int i = 0; i < data.length; i++) {
            char c = data[i];

            if (c == 'i') {
                c = data[++i];
                c = c == 'a' ? 'i' : c == 'b' ? '+' : c == 'c' ? '/' : data[--i];
            }

            val = (val << 6) | decodes[c];
            pos += 6;

            while (pos > 7) {
                baos.write(val >> (pos -= 8));

                val &= ((1 << pos) - 1);
            }
        }

        return baos.toByteArray();
    }


}
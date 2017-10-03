package com.destiny.work.common;

import java.security.MessageDigest;

/**
 * Created by Destiny_hao on 2017/8/11.
 */

public class MD5 {

    public final static String toMD5(String oldStr) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        //System.out.println("原始字符串为："+oldStr);
        try {
            // 参数oldStr表示要加密的字符串
            // 转换成字节流
            byte[] oldBytes = oldStr.getBytes();
//            for(byte b:oldBytes)
//            {
//                System.out.print(b+" ");
//            }
//            System.out.println();

            // 得到对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 初始化
            md.update(oldBytes);

            // 运行加密算法
            byte[] newBytes = md.digest();
//            for(byte b:newBytes)
//            {
//                System.out.print(b+" ");
//            }
//            System.out.println();

            // 构造长度为2倍的字符串(32)
            char newStr[] = new char[newBytes.length*2];

            // 循环进行处理
            for (int i = 0; i < newBytes.length; i++) {
                byte tmp = newBytes[i];
                newStr[2*i] = hexDigits[tmp >>> 4 & 0xf];
                newStr[2*i+1] = hexDigits[tmp & 0xf];
            }

            return String.valueOf(newStr);
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(MD5.toMD5("admin"));
    }
}

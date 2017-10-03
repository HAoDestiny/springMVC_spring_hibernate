package com.destiny.work.common;

import java.awt.*;
import java.io.ByteArrayInputStream;

/**
 * Created by Destiny_hao on 2017/8/8.
 */
public class FontByTe {

    private String strs;

    public Font getFont(int fontHeight) {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT,
                    new ByteArrayInputStream(hex2byte(getFontByteStr())));

            return baseFont.deriveFont(Font.PLAIN, fontHeight);
        } catch (Exception e) {
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }

    private byte[] hex2byte(String str) {
        if (str == null)
            return null;
        strs = str.trim();
        int len = strs.length();
        if (len == 0 || len % 2 == 1)
            return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < strs.length(); i += 2) {
                b[i / 2] = (byte) Integer
                        .decode("0x" + strs.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * ttf字体文件的十六进制字符串
     *
     * @return
     */
    private String getFontByteStr() {
        return strs;//字符串太长 在附件中找
    }

}

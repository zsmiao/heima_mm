package xyz.itclay.heima_mm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 获得id等字符串
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 9:36
 **/
public class GuidUtil {

    private static final Random ran = new Random();

    /**
     * 获取UUID
     *
     * @return UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getGuid() {
        StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        int n = (int) (Math.random() * 90000.0D + 10000.0D);
        return now.append(n) + "";
    }

    public static String getGuidDdh() {
        StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));
        return now + "";
    }


    public static String getGuidBillno() {
        StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyymmdd").format(new Date()));
        String code = "0123456789";
        StringBuffer cc = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }
        return now + cc.toString();
    }


    public static String getSmailGuid() {
        StringBuffer cc = new StringBuffer();
        cc.append("6");
        String code = "0123456789";
        for (int i = 0; i < 8; i++) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }
        return cc.toString();
    }

    public static String getCodeNum() {
        StringBuffer cc = new StringBuffer();
        String code = "0123456789";
        for (int i = 0; i < 6; i++) {
            cc.append(code.charAt(ran.nextInt(code.length())));
        }
        return cc.toString();
    }

    public static String getStrGuid() {
        StringBuffer sb = new StringBuffer();
        String code = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 8; i++) {
            sb.append(code.charAt(ran.nextInt(code.length())));
        }
        return sb.toString();
    }
}

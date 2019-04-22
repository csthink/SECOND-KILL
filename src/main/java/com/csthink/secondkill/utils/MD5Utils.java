package com.csthink.secondkill.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    // 密码公盐
    private static final String SALT = "!@#$124*&^%(";

    /**
     * 简单方式获取MD5散列值
     *
     * @param str 待加密字符串
     * @return 加密后的值
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    /**
     * 明文密码转换成表单中提交的md5密码
     *
     * @param rawPass 明文密码
     * @return 加密后的密码
     */
    public static String rawPassToFormPass(String rawPass) {
        String str = SALT.charAt(0) + SALT.charAt(4) + rawPass + SALT.charAt(2) + SALT.charAt(6);
        return md5(str);
    }

    /**
     * 表单提交的密码转换成数据库中存储的密码
     *
     * @param formPass 表单提交的加密后的密码
     * @param salt 密码公盐
     * @return 数据库保存的密码
     */
    public static String formPassToDbPass(String formPass, String salt) {
        String str = salt.charAt(1) + salt.charAt(3) + formPass + salt.charAt(5) + salt.charAt(7);
        return md5(str);
    }

    /**
     * 明文密码转换成数据库中存储的密码
     *
     * @param rawPass 明文密码
     * @param saltForDB 用户密码私盐
     * @return 数据库中保存的密码
     */
    public static String rawPassToDbPass(String rawPass, String saltForDB) {
        return formPassToDbPass(rawPassToFormPass(rawPass), saltForDB);
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.rawPassToDbPass("123456", SALT));
    }

}

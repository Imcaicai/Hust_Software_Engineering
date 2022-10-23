package com.example.library.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*为密码加密*/
public class PasswordUtil {
    public static String encipherPassword(String password) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash){
                if ((b & 0xff) < 0x10){
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xff));
            }
            return hex.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }
}

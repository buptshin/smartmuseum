package com.example.smartmuseum.util;


import java.util.regex.Pattern;

/**
 * 放各种校验
 */
public class RegexUtils {

    /**
     * 手机号的校验（没有放很复杂的校验，1开头的十位数字即可）
     * @param tel
     * @return
     */
    public static boolean validateMobilePhone(String tel){
        Pattern telPattern = Pattern.compile("^[1]\\d{10}$");
        return telPattern.matcher(tel).matches();
    }
}

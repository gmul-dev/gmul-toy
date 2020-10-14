package com.gmul.toy.example.domain;

import com.gmul.toy.example.type.PasswordStrength;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {

        // 5
        if(s == null) {
            return PasswordStrength.INVALID;
        }

        // 1
        // return null

        // 3
        if(s.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        // 4 => method 구성
//        boolean containsNum = false;
//        for(char ch : s.toCharArray()) {
//            if(ch >= '0' && ch <= '9') {
//                containsNum = true;
//                break;
//            }
//        }
        boolean containsNum = meetsContainingNumberCriteria(s);
        if(!containsNum) {
            return PasswordStrength.NORMAL;
        }

        // 6
//        boolean containsUpp = false;
//        for(char ch : s.toCharArray()) {
//            if(Character.isUpperCase(ch)) {
//                containsUpp = true;
//                break;
//            }
//        }
        boolean containsUpp = meetsContainingUppercaseCriteria(s);
        if(!containsUpp) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for(char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for(char ch : s.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}

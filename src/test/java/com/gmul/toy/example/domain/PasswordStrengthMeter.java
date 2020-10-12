package com.gmul.toy.example.domain;

import com.gmul.toy.example.type.PasswordStrength;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String s) {
//        return null;
        return PasswordStrength.STRONG;
    }
}

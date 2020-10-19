package com.gmul.toy.example.domain;

import com.gmul.toy.example.type.CardValidity;

public class RegisterResult {

    public static RegisterResult error(CardValidity validity) {
        return null;
    }

    public static RegisterResult success() {
        return null;
    }

    public CardValidity getValidity() {
        return null;
    }
}

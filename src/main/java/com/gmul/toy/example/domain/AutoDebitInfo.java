package com.gmul.toy.example.domain;

import java.time.LocalDateTime;

public class AutoDebitInfo {
    private String userId;
    private String cardNumber;
    private LocalDateTime createdAt;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime createdAt) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.createdAt = createdAt;
    }

    public AutoDebitInfo(String userId, String cardNumber) {
        this(userId, cardNumber, LocalDateTime.now());
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void changeCardNumber(String cardNumber) {

    }
}
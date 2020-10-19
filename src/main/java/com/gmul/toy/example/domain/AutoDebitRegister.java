package com.gmul.toy.example.domain;

import com.gmul.toy.example.dao.AutoDebitInfoRepository;
import com.gmul.toy.example.domain.RegisterResult;
import com.gmul.toy.example.type.CardValidity;
import com.gmul.toy.example.validator.CardNumberValidator;

import java.time.LocalDateTime;

public class AutoDebitRegister {
    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());
        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }
        AutoDebitInfo info = repository.findOne(req.getUserId());
        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
           AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDateTime.now());
           repository.save(newInfo);
        }
        return RegisterResult.success();
    }

    public static class AutoDebitReq {
        private String cardNumber;
        private String userId;

        public AutoDebitReq(String userId, String cardNumber) {
            this.userId = userId;
            this.cardNumber = cardNumber;
        }

        public String getCardNumber() {
            return this.cardNumber;
        }

        public String getUserId() {
            return this.userId;
        }
    }


}

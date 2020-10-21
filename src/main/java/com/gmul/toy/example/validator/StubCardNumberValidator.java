package com.gmul.toy.example.validator;

import com.gmul.toy.example.type.CardValidity;

// CardNumberValidator's Stub
public class StubCardNumberValidator extends CardNumberValidator {
    private String invalidNo;
    private String theftNo;

    public StubCardNumberValidator() {
        super(null);
    }

    public StubCardNumberValidator(String server) {
        super(server);
    }

    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }

    @Override
    public CardValidity validate(String cardNumber) {
        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID;
        }
        if (theftNo != null && theftNo.equals(cardNumber)) {
           return CardValidity.THEFT;
        }
        return CardValidity.VALID;
    }
}

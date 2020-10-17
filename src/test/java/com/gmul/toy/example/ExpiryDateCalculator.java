package com.gmul.toy.example;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
//        return LocalDate.of(2019, 4, 1);
        return billingDate.plusMonths(1);
    }

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonth = payData.getPaymentAmount() / 10_000;
        if(addedMonth >= 10) {
            addedMonth += 2;
        }
        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonth);
        } else {
            return payData.getBillingDate().plusMonths(addedMonth);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonth) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonth);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if(dayOfFirstBilling != candidateExp.getDayOfMonth()) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return  candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }
}

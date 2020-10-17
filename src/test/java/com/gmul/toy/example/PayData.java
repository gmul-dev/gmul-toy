package com.gmul.toy.example;

import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

public class PayData {
    private LocalDate billingDate;
    private int paymentAmount;
    private LocalDate firstBillingDate;

    private PayData() {
    }

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int paymentAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData data = new PayData();

        public Builder billingDate(LocalDate billingDate) {
            data.billingDate = billingDate;
            return this;
        }

        public Builder paymentAmount(int paymentAmount) {
            data.paymentAmount = paymentAmount;
            return this;
        }

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            data.firstBillingDate = firstBillingDate;
            return this;
        }

        public PayData build() {
            return data;
        }
    }
}
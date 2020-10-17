package com.gmul.toy.example;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ExpiryDateCalculatorTest {

    @Test
    public void 만원_납부하면_한달뒤가_만료일이_됨() {
        // 중복 제거
//        LocalDate billingDate = LocalDate.of(2019,3,1);
//        int payAmount = 10_000;
//
//        ExpiryDateCalculator cal = new ExpiryDateCalculator();
//        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//
//        assertEquals(LocalDate.of(2019, 4, 1), expiryDate);
//
//        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
//        int payAmount2 = 10_000;
//
//        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
//        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2);
//
//        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2);

//        assertExpiryDate(LocalDate.of(2019, 3, 1)
//                , 10_000
//                , LocalDate.of(2019, 4, 1));
//
//        assertExpiryDate(LocalDate.of(2019, 5, 5)
//                , 10_000
//                , LocalDate.of(2019, 6, 5));

        PayData data = PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .paymentAmount(10_000)
                .build();

        PayData data2 = PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 5))
                .paymentAmount(10_000)
                .build();

        assertExpiryDate(data, LocalDate.of(2019, 4, 1));

        assertExpiryDate(data2, LocalDate.of(2019, 6, 5));

    }

    @Test
    public void 납부일과_한달뒤_일자가_같지않음() {

        PayData data = PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .paymentAmount(10_000)
                .build();

        PayData data2 = PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 31))
                .paymentAmount(10_000)
                .build();

        assertExpiryDate(data, LocalDate.of(2019, 2, 28));

        assertExpiryDate(data2, LocalDate.of(2019, 6, 30));
    }

    @Test
    public void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .paymentAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expiryDate, expectedExpiryDate);
    }

    private void assertExpiryDate(PayData data, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(data);
        assertEquals(expiryDate, expectedExpiryDate);
    }
}

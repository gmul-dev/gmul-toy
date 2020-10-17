package com.gmul.toy.example;

import org.apache.tomcat.jni.Local;
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

    // 예외 경우와 쉬운 경우중 위 테스트와 공통부분이 있는 예외 경우 테스트 먼저 진행
    @Test
    public void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .paymentAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .paymentAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .paymentAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    @Test
    public void 이만원_이상_납부하면_비례해서_만료일_계산() {

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .paymentAmount(20_000)
                .build()
            , LocalDate.of(2019, 5, 1));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .paymentAmount(30_000)
                        .build()
                , LocalDate.of(2019, 6, 1));
    }

    @Test
    public void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(
            PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .paymentAmount(20_000)
                .build()
            , LocalDate.of(2019, 4, 30));

        assertExpiryDate(
            PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 3, 31))
                .billingDate(LocalDate.of(2019, 4, 30))
                .paymentAmount(30_000)
                .build()
            , LocalDate.of(2019, 7, 31));
    }

    @Test
    public void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 28))
                .paymentAmount(100_000)
                .build()
            , LocalDate.of(2020, 1, 28));

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .paymentAmount(130_000)
                        .build()
                , LocalDate.of(2020, 5, 28));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expiryDate, expectedExpiryDate);
    }

    private void assertExpiryDate(PayData data, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(data);
        assertEquals(expectedExpiryDate, expiryDate);
    }
}

package com.gmul.toy.example;

import com.gmul.toy.example.domain.PasswordStrengthMeter;
import com.gmul.toy.example.type.PasswordStrength;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PasswordStrengthMeterTest {

    // 1. 빈 테스트 메서드로 테스트 환경을 갖추었는지 테스트
//    @Test
//    public void name() {
//
//    }

    // 2. 모든 규칙을 충족하는 경우
    // 모든 규칙을 충족하는 경우의 테스트 코드를 작성하고
    // 테스트 코드가 통과할 만큼만 코드 추rk
    @Test
    public void 모든_조건충족시_암호강도_강함 () {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);
        PasswordStrength result2 = meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }
}

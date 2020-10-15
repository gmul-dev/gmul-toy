package com.gmul.toy.example;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

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
//        PasswordStrengthMeter meter = new PasswordStrengthMeter();
//        PasswordStrength result = meter.meter("ab12!@AB");
//        assertEquals(PasswordStrength.STRONG, result);
//        PasswordStrength result2 = meter.meter("abc1!Add");
//        assertEquals(PasswordStrength.STRONG, result2);
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    // 3. 지금까지의 테스트를 통과하도록 수정
    @Test
    public void 길이만_8글자미만_나머지충족() {
//        PasswordStrengthMeter meter = new PasswordStrengthMeter();
//        PasswordStrength result = meter.meter("ab12!@A");
//        assertEquals(PasswordStrength.NORMAL, result);
//        PasswordStrength result2 = meter.meter("Ab12!c");
//        assertEquals(PasswordStrength.NORMAL, result2);
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    // 4
    @Test
    public void 숫자_포함X_나머지충족() {
//        PasswordStrengthMeter meter = new PasswordStrengthMeter();
//        PasswordStrength result = meter.meter("ab!@ABqwer");
//        assertEquals(PasswordStrength.NORMAL, result);
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    // 5 각 단계별 테스트 코드가 모두 통과하는지 확인해라
    @Test
    public void 값이_없음() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    // 6
    @Test
    public void 대문자_포함X_나머지충족() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    // 7 한가지만 충족
    @Test
    public void 길이가_8글자만_충족() {
        assertStrength("aaadskjlf", PasswordStrength.WEAK);
    }

    // 8
    @Test
    public void 숫자포함만_충족() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    // 9
    @Test
    public void 대문자포함만_충족() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    // 10
    @Test
    public void 아무_조건도_충족X() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}

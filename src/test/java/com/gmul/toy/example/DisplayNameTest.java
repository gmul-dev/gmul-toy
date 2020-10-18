package com.gmul.toy.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("@DisplayName 테스트")
public class DisplayNameTest {

    @DisplayName("값 같은지 비교")
    @Test
    public void assertEqualsMethod() {

    }

    // 실행하고 싶지 않을때
    @Disabled
    @Test
    public void failMethod() {

    }

    @DisplayName("익셉션 발생 여부 테스트")
    @Test
    public void assertThrowsTest() {

    }

    @Test
    public void assertAllTest() {

    }

}

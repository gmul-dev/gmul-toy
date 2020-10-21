package com.gmul.toy.example;

import com.github.tomakehurst.wiremock.*;
import com.gmul.toy.example.type.CardValidity;
import com.gmul.toy.example.validator.CardNumberValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

class CardNumberValidatorTest {
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() {
        // 테스트 실행전 WireMockServer를 시작한다. 실제 HTTP 서버가 뜬다.
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        // 테스트 실행 후 WireMockServer를 서버 중지
        wireMockServer.stop();
    }

    @Test
    public void valid() {
        // WireMockServer 동작 기술
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .withRequestBody(equalTo("1234567890")) // 요청이 위와 같으면 아래를 리턴
                .willReturn(aResponse()
                                .withHeader("Content-Type", "text/plain")
                                .withBody("ok")));

        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.VALID, validity);
    }

    @Test
    public void timeout() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .willReturn(aResponse().withFixedDelay(5000)));

        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.TIMEOUT, validity);
    }

}
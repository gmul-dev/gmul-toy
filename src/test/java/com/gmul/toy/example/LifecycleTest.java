package com.gmul.toy.example;


import org.junit.jupiter.api.*;

public class LifecycleTest {

    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

//    @BeforeAll
//    모든 테스트 메서드가 실행되기전 한번 실행, 정적메서드에 사용
    @BeforeEach
    public void setUp() {
        // private X
        // 테스트를 실행하는데 필요한 준비 작업, 임시파일 생성이나 테스트 메서드에서 사용할 객체 생성
        System.out.println("setUp");
    }

    @Test
    public void a() {
        System.out.println("A");
    }

    @Test
    public void b() {
        System.out.println("B");
    }

    @AfterEach
    public void tearDown() {
        // private X
        //테스트를 실행한후 정리, 임시파일 삭제
        System.out.println("tearDown");
    }

//    @AfterAll
//    모든 테스트 메서드가 실행한뒤에 실행, 정적메스드에 적용
}

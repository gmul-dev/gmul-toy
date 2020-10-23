package com.gmul.toy.example;

import com.gmul.toy.example.domain.User;
import com.gmul.toy.example.exception.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class NOTICETest {

    private final static String TEST_DATE = "1945년 8월 15일";

    @Test
    public void 변수나_필드사용해서_기댓값표현_X () {
        LocalDate date = LocalDate.of(1945,8,15);
        String dateStr = date.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"));
        //assertEquals((date.getYear() + "년 " + date.getMonthValue() + "월 " + date.getDayOfMonth() + "일"), dateStr);
        //assertEquals(TEST_DATE, dateStr);
        // 논리적으로는 맞지만 가독성이 떨어진다

        assertEquals("1945년 8월 15일", dateStr);
    }

    @Test
    public void 두개_이상_검증_X () {

        /**
         * 한 테스트에서 검증하는 내용이 두개 이상이면 결과를 확인할때 집중도 저하
         * 반드시 한가지만 검증해야 하는 것은 아니나 검증 대상이 명확하게 구분 된다면
         * 테스트 메서드도 구분하는것이 유지보수에 유리
         *
        // 검증 1 : 회원 데이터가 올바르게 저장되었는지 검증
        User savedUser = fackRepository.findById();
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());

        // 검증 2 : 이메일 발송을 요청했는지 검증
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                .should()
                .sendRegisterEmail(captor.capture());
        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
         */
    }

    @Test
    public void 정확하게_일치하는_값으로_모의객체_설정_X () {
        /**
         *

        // 전달 파라미터가 조금만 바뀌어도 테스트가 깨지게 된다
//        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("pw"))
//                .willReturn(true);

        // 약한 암호의 경우 회원등록 테스트라는 목적에 맞으면서 범용적인 값을 사용하도록
        // 약간의 코드 수정으로 테스트가 실패하는 것을 방지할 수 있다
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak(Mockito.anyString()))
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () ->{
           userRegister.register("id", "pw", "email");
        });

        assertThrows(WeakPasswordException.class, () ->{
            userRegister.register("id", "pw2", "email");
        });
         */
    }

    @Test
    public void 과도하게_구현_검증_X () {

        /**
         *
         * 내부 구현을 검증하는 것은 나쁘지 않으나
         * 구현을 조금만 변경해도 테스트가 깨질 가능성이 커진다
         *
         * 내부 구현은 언제든지 바뀔 수 있기 때문에 테스트 코드는 내부 구현보다 실행 결과를 검증

        userRegister.register("id", "pw", "email");

        // checkPasswordWeak() 메서드 호출 검사
        BDDMockito.then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(Mockito.anyString());

        // findById() 호출하지 않는 것을 검사
        BDDMockito.then(mockRegister)
                .should()
                .findById(Mockito.anyString());

         */

        /**
         * 레거시 코드의 DAO에선 다양한 CRUD가 혼합되어 있기 때문에
         * 메모리를 이용한 가짜 구현으로 대체하기 쉽지 않다
         * 모의 객체를 호출하는 여부를 확인하는 것은 내부 구현을 검증하는 것이지만
         * 이메일이 변경되었는지 확인할 수 있는 수단은 이것뿐이다

        BDDMockito.given(mockDao.countById(Mockito.anyString()))
                .willReturn(1);

        emailService.changeEmail("id", "new@somehost.com");

        BDDMockito.then(mockDao)
                .should()
                .updateEmail(Mockito.anyString(), Mockito.matches("new@somehost.com"));
         */
    }

    @Test
    public void 셋업을_이용해서_중복된_상황_설정_X () {
        /**
         * 테스트 중 각 테스트 코드에서 동일한 상황 필요할 경우
         * 중복 제거를 위해 @BeforeEach 메서드를 이용해 상황 구성 가능 하지만
         * 테스트 메서드는 별도 프로그램으로 검증 내용을 스스로 설명할 수 있어야
         * 테스트가 깨지거나 가독성이 떨어지는 걸 방지할 수 있다

 //    @BeforeEach
 //    public void setUp() {
 //        changeService = new ChangeUserService(memoryRepository);
 //        memoryRepository.save(
 //                new User("id", "name", "pw", new Address("서울", "북부"))
 //        );
 //    }

        memoryRepository.save(
            new User("id", "name", "pw", new Address("서울", "남부"))
        );

        changeService.changeAddress("id", new Address("경기", "남부"));

        User user = memoryRepository.findById("id");
        assertEquals("경기", user.getAddress().getCity());
         */
    }
}

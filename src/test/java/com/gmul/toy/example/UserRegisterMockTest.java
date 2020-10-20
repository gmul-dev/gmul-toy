package com.gmul.toy.example;

import com.gmul.toy.example.api.EmailNotifier;
import com.gmul.toy.example.dao.MemoryUserRepository;
import com.gmul.toy.example.dao.UserRepository;
import com.gmul.toy.example.domain.User;
import com.gmul.toy.example.domain.UserRegister;
import com.gmul.toy.example.exception.WeakPasswordException;
import com.gmul.toy.example.validator.WeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

// 메서드의 호출 여부 검증 Mock : 기대한대로의 상호작용을 하는지 행위 검증, 예외시 익셉션, 스텁이자 스파이
public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordchecker = Mockito.mock(WeakPasswordChecker.class);
//    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    public void setUp() {
        userRegister = new UserRegister(mockPasswordchecker, mockUserRepository, mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    public void weakPassword() {
        // pw 인자를 사용해서 모의 객체의 checkPasswordWeak 메서드를 호출하면 return ture
        BDDMockito.given(mockPasswordchecker.checkPasswordWeak("pw"))
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    public void checkPassword() {
        userRegister.register("id", "pw", "email");

        BDDMockito.then(mockPasswordchecker)    // 인자로 전달한 mockPasswordChecker의 모의 객체의
            .should()                       // 특정 메서드가 호출됐는지 검증하는데
            .checkPasswordWeak(BDDMockito.anyString()); // 임의의 String 타입 인자를 이용해서 checkPasswordWeak() 메서드 호출 여부를 확인한다.
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    public void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        // 모의 객체를 호출할때 전달한 객체가 담김
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                .should()
                .sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue(); // 보관한 인자값
        assertEquals("email@email.com", realEmail);
    }

    @DisplayName("회원 가입 성공")
    @Test
    public void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        BDDMockito.then(mockUserRepository)
                .should()
                .save(captor.capture());

        User savedUser = captor.getValue();
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());

        // 테스트 코드가 Fake를 사용하였을때 보다 복잡
        // 모의 객체를 사용한 경우 - 레파지토리의 save() 메서드를 호출해야하고 이때 전달한 객체의 값이 어때야한다..
        // 가짜 구현의 경우 - 레파지토리에 저장된 값이 어때야한다 -> 실제 검증 내용에 가까움
        // 결과 값을 검증하는 수단으로는 다른 방벙이 낫다
        // dao 나 repository로는 메모리를 이용한 fake 구현이 더 간결하고 관리하기 쉽다
    }
}

package com.gmul.toy.example;

import com.gmul.toy.example.api.SpyEmailNotifier;
import com.gmul.toy.example.dao.MemoryUserRepository;
import com.gmul.toy.example.domain.User;
import com.gmul.toy.example.domain.UserRegister;
import com.gmul.toy.example.exception.DupIdException;
import com.gmul.toy.example.exception.WeakPasswordException;
import com.gmul.toy.example.validator.StubWeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {
    private UserRegister userRegister;
    // 약한 암호 확인 Stub : 구현을 단순한 것으로 대체
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    // 레파지토리 Fake : 제품에는 적합치 않지만 실제 동작하는 구현 제공
    private MemoryUserRepository fackRepository = new MemoryUserRepository();
    // 이메일 발송 여부 확인 Spy : 호출된 내역을 기록, 스텁이기도 함
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    public void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fackRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    public void weakPassword() {
        stubWeakPasswordChecker.setWeak(true); // 암호 약함으로 설정

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    public void dupIdExists() {
        fackRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    public void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        User savedUser = fackRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 발송함")
    @Test
    public void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }
}

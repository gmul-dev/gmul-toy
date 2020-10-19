package com.gmul.toy.example;

import com.gmul.toy.example.dao.MemoryAutoDebitInfoRepository;
import com.gmul.toy.example.domain.AutoDebitInfo;
import com.gmul.toy.example.domain.AutoDebitRegister;
import com.gmul.toy.example.domain.RegisterResult;
import com.gmul.toy.example.type.CardValidity;
import com.gmul.toy.example.validator.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AutoDebitRegisterTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    public void setUp() {
//        CardNumberValidator validator = new CardNumberValidator();
        stubValidator = new StubCardNumberValidator();
//        AutoDebitInfoRepository repository = new AutoDebitInfoRepository();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, repository);
    }

    @Test
    public void invalidCard() {
        //업체에서 받음 테스트용 유효한 카드번호 사용
        stubValidator.setInvalidNo("111122223333");

        AutoDebitRegister.AutoDebitReq req = new AutoDebitRegister.AutoDebitReq("user1", "111122223333");
        RegisterResult result = this.register.register(req);
        assertEquals(CardValidity.VALID, result.getValidity());
    }

    @Test
    public void theftCard() {
        //업체에서 받은 도난 테스트용 카드번호 사용
        stubValidator.setTheftNo("123343524626");

        AutoDebitRegister.AutoDebitReq req = new AutoDebitRegister.AutoDebitReq("user1", "123343524626");
        RegisterResult result = this.register.register(req);
        assertEquals(CardValidity.THEFT, result.getValidity());
    }

    @Test
    public void 이미_등록된_정보_업데이트() {
        repository.save(new AutoDebitInfo("user1", "111222333", LocalDateTime.now()));

        AutoDebitRegister.AutoDebitReq req = new AutoDebitRegister.AutoDebitReq("user1", "1352342626");
        RegisterResult result = this.register.register(req);
        AutoDebitInfo saved = repository.findOne("user1");

        assertEquals("1352342626", saved.getCardNumber());
    }

    @Test
    public void 새로_등록() {
        AutoDebitRegister.AutoDebitReq req = new AutoDebitRegister.AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234123412341234", saved.getCardNumber());
    }

}
package com.gmul.toy.example;

import com.gmul.toy.domain.exampledb.Member;
import com.gmul.toy.example.domain.Game;
import com.gmul.toy.example.domain.Score;
import com.gmul.toy.example.domain.User;
import com.gmul.toy.example.exception.DupIdException;
import com.gmul.toy.example.exception.WeakPasswordException;
import com.gmul.toy.example.helper.UserGivenHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Sql("classpath:init-data.sql")
public class NOTICETest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private UserGivenHelper given;

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

        /**
         * @Sql 애노테이션을 사용하여 테스트 실행전 특정쿼리를 실행시킬수 있음
         * 셋업과 마찬가지로 쿼리 파일을 조금만 변경해도 많은 테스트가 깨질수 있고 그때마다 쿼리 파일도 같이 수정해야함
         *
         * 통합 테스트 코드를 만들때는 두가지로 초기화 데이터를 나눠서 생각
         * 1. 모든 테스트가 같은 값을 사용하는 데이터 ex) 코드값 => 공유해도 괜찮
         * 2. 테스트 메서드에서만 필요한 데이터 => 각 테스트 메서드에서만 생성해야함
         *
         */
    }

    @Test
    public void 통합테스트_상황설정_위한_보조클래스_사용 () {
//        given.givenUser("chk", "pw", "chk@ckd.com");
//
//        assertThrows(DupIdException.class, () -> {
//            register.register("chk", "pw", "chk@ckd.com");
//        });
    }

    @Test
    @EnabledOnOs({ OS.LINUX, OS.LINUX})
    @DisabledOnOs(OS.WINDOWS)
    public void 실행환경이_다르다고_실패_X () {
        /**
         로컬 개발환경에서는 성공하는데 빌드 서버에서는 실패하거나
         윈도우에서는 성공하는데 맥에서는 실패하는 식으로 환경에 따라 테스트 실패하면 안됨

        private String filePath = "D:\\mywork\\temp\\bluk.txt";

        BulkLoader loader = new BulkLoader();
        ...

        //시스템이 제공하는 임시 폴더 경로를 사용
        String folder = System.getProperty("java.io.tmpdir");
        Exporter exporter = new Exporter(folder);

        Path path = Paths.get(folder, "file.txt");

         */
    }

    @Test
    public void 실행시점이_다르다고_실패_X () {

        /*
        LocalDateTime expiry = LocalDateTime.of(2019,12,31,0,0,0);
        Member m = Member.builder().expiryDate(expiry).build();
        // 2019/08/21 에는 테스트가 성공이지만 2019/12/31 이후에는 깨짐
        assertFalse(m.isExpired());


        public boolean passedExpiryDate(LocalDateTime time) {
            return expiryDate.isBefore(time);
        }

        assertFalse(m.passedExpiryDate(LocalDateTime.of(2019,12,30,0,0,0)));
         */
    }

    @Test
    public void 랜덤하게_실패_X () {
        /**
         *
        Game g = new Game();
        Score s = g.guess(?,?,?); // 랜덤 클래스로 값을 생성하여 테스트를 통과 시킬수 있는 값이 매번 바뀜
        assertEquals(0, s.strikes());
        assertEquals(0, s.balls());


        // 랜덤 값 생성을 별도 타입으로 분리하고
        // 이를 대역으로 대체해서 사용
        GameNumGen gen = mock(GameNumGen.clas);
        given(gen.generate()).willReturn(new int[] {1, 2, 3});

        Game g = new Game(gen);
        Score s = g.guess(4,5,6);
        assertEquals(0, s.strikes());
        assertEquals(0, s.balls());
         */
    }
}

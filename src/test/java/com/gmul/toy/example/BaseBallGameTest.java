package com.gmul.toy.example;

import com.gmul.toy.example.domain.BaseballGame;
import com.gmul.toy.example.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseBallGameTest {

    // 상황, 실행, 결과 확인은 영어표현 given, when, then에 대응

    private BaseballGame game;

    @BeforeEach
    public void givenGame() {
        game = new BaseballGame("456");
    }

    @Test
    public void exactMatch() {
        // 정답이 456인 상황
        // given
//        BaseballGame game = new BaseballGame("456");

        // 실행
        // when
        Score score = game.guess("456");

        // 결과 확인
        // then
        assertEquals(3, score.strikes());
        assertEquals(0, score.balls());
    }

    @Test
    public void notMatch() {

        //given
//        BaseballGame game = new BaseballGame("456");

        //when
        Score score = game.guess("123");

        //then
        assertEquals(0, score.strikes());
        assertEquals(0, score.balls());
    }

    @Test
    public void 정답에_동일한_숫자가_있으면_생성실패() {
        assertThrows(IllegalArgumentException.class,
                () -> new BaseballGame("110"));
    }
}

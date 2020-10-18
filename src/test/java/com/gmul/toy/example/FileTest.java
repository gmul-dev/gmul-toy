package com.gmul.toy.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("외부상황과 외부결과")
public class FileTest {
    // 외부 상황이 테스트에 영향을 줄 경우
    // 알맞게 상황 제어
    // 외부 결과를 확인해야하는 경우 (ex. database..)
    // 대역을 사용하면 쉽게 확인 가능

    @Test
    public void noDataFile_Then_Exception() {
        // 파일이 있을수도 있으므로 없는 상황을 명시적으로 만듬
        givenNoFile("badpath.txt");
        File file = new File("badpath.txt");
        assertThrows(IllegalArgumentException.class,
                () -> MathUtils.sum(file));
    }

    private void givenNoFile(String path) {
        File file = new File(path);
        if(file.exists()) {
            boolean deleted = file.delete();
            if(!deleted) {
                throw new RuntimeException("fail givenNoFile: " + path);
            }
        }
    }

    @DisplayName("상황에 알맞는 파일 미리 생성")
    @Test
    public void dataFileSumTest() {
        // 파일을 미리 생성해둔다
        // datafile => content {
        // 1
        // 2
        // 3
        // 4
        //}
        File file = new File("/src/test/resources/datafile.txt");
        long sum = MathUtils.sum(file);
        assertEquals(10L, sum);
    }

    @DisplayName("테스트에서 파일도 같이 생성")
    @Test
    public void dataFileSumTest2() {
        givenDataFile("target/datafile.txt", "1", "2", "3", "4");
        File file = new File("target/datafile.txt");
        long sum = MathUtils.sum(file);
        assertEquals(10L, sum);
    }

    private void givenDataFile(String path, String... lines) {
        try {
            Path dataPath = Paths.get(path);
            if (Files.exists(dataPath)) {
               Files.delete(dataPath);
            }
            Files.write(dataPath, Arrays.asList(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class MathUtils {
        public static long sum(File file) {
            if(!file.exists()) {
                throw new IllegalArgumentException();
            }
            return 10L;
        }
    }
}

package com.hhplus.week02.domain.lecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LectureValidatorUnitTest {

    private final LectureValidator lectureValidator = new LectureValidator();

    @DisplayName("유효한 강의 ID 값 테스트")
    @ParameterizedTest(name = "{0} 이 유효한 ID 인지 테스트")
    @ValueSource(longs = {1L, 100L, 100_000L, Long.MAX_VALUE})
    public void isValidUserId(Long userId) {
        lectureValidator.validator(userId);

        assertTrue(true);
    }

    @DisplayName("유효하지 강의 않은 ID 값 테스트")
    @ParameterizedTest(name = "{0} 은 유효하지 않은 아이디")
    @ValueSource(longs = {-1L, 0L})
    @NullSource
    public void isInvalidUserId(Long userId) {
        assertThrows(LectureException.class, () -> {
            lectureValidator.validator(userId);
        });
    }
}
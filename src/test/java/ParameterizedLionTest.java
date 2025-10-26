import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.BeforeEach;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParameterizedLionTest {

    private Feline feline;

    @BeforeEach
    void setUp() {
        feline = mock(Feline.class);
    }

    @ParameterizedTest
    @DisplayName("Параметризованная проверка создания льва с разным полом")
    @MethodSource("sexProvider")
    void testLionCreationWithDifferentSex(String sex, boolean expectedHasMane) throws Exception {
        when(feline.getKittens()).thenReturn(1);

        Lion lion = new Lion(sex, feline);

        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    private static Stream<Arguments> sexProvider() {
        return Stream.of(
                Arguments.of("Самец", true),
                Arguments.of("Самка", false)
        );
    }

    @ParameterizedTest
    @DisplayName("Параметризованная проверка исключения при неверном поле")
    @ValueSource(strings = {"", "Unknown", "Мужской", "Женский"})
    void testLionCreationWithInvalidSex(String invalidSex) {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(invalidSex, feline);
        });

        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Параметризованная проверка количества котят")
    @ValueSource(ints = {0, 1, 3, 5})
    void testGetKittensWithDifferentCounts(int kittensCount) throws Exception {
        when(feline.getKittens()).thenReturn(kittensCount);

        Lion lion = new Lion("Самец", feline);
        int result = lion.getKittens();

        assertEquals(kittensCount, result);
        verify(feline, times(1)).getKittens();
    }
}

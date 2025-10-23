import com.example.Feline;
import com.example.Lion;
import com.example.Predator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LionTest {

    private Feline feline;

    @BeforeEach
    void setUp() {
        feline = mock(Feline.class);
    }

    @Test
    @DisplayName("Создание льва-самца")
    void testCreateMaleLion() throws Exception {
        when(feline.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самец", feline);

        assertTrue(lion.doesHaveMane());
    }

    @Test
    @DisplayName("Создание льва-самки")
    void testCreateFemaleLion() throws Exception {
        when(feline.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самка", feline);

        assertFalse(lion.doesHaveMane());
    }

    @Test
    @DisplayName("Исключение при неверном поле")
    void testCreateLionWithInvalidSex() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Неизвестно", feline);
        });

        assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }

    @Test
    @DisplayName("Проверка метода getKittens")
    void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(2);

        Lion lion = new Lion("Самец", feline);
        int kittens = lion.getKittens();

        assertEquals(2, kittens);
        verify(feline, times(1)).getKittens();
    }

    @Test
    @DisplayName("Проверка метода getFood")
    void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", feline);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(feline, times(1)).eatMeat();
    }

    @Test
    @DisplayName("Проверка метода eatMeat")
    void testEatMeat() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", feline);
        List<String> actualFood = lion.eatMeat();

        assertEquals(expectedFood, actualFood);
        verify(feline, times(1)).eatMeat();
    }


    @Test
    @DisplayName("Проверка getKittens когда predator не Feline")
    void testGetKittensWhenPredatorIsNotFeline() throws Exception {
        Predator otherPredator = mock(Predator.class);

        Lion lion = new Lion("Самец", otherPredator);
        int kittens = lion.getKittens();

        assertEquals(0, kittens);
    }

}
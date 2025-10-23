import com.example.Feline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    @Test
    @DisplayName("Проверка метода eatMeat")
    void testEatMeat() throws Exception {
        Feline feline = new Feline();
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, feline.eatMeat());
    }

    @Test
    @DisplayName("Проверка метода getFamily")
    void testGetFamily() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    @DisplayName("Проверка метода getKittens без параметров")
    void testGetKittensWithoutParameters() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @Test
    @DisplayName("Проверка метода getKittens с параметром")
    void testGetKittensWithParameter() {
        Feline feline = new Feline();
        assertEquals(3, feline.getKittens(3));
    }
}

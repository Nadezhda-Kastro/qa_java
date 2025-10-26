import com.example.Cat;
import com.example.Predator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatTest {

    private Predator predator;

    @BeforeEach
    void setUp() {
        predator = mock(Predator.class);
    }

    @Test
    @DisplayName("Проверка метода getSound")
    void testGetSound() {
        Cat cat = new Cat(predator);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    @DisplayName("Проверка метода getFood")
    void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(predator);
        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);
        verify(predator, times(1)).eatMeat();
    }
}

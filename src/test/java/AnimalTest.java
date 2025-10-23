import com.example.Animal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal animal = new Animal();

    @Test
    @DisplayName("Проверка метода getFood для Травоядного")
    void testGetFoodForHerbivore() throws Exception {
        List<String> expectedFood = List.of("Трава", "Различные растения");
        assertEquals(expectedFood, animal.getFood("Травоядное"));
    }

    @Test
    @DisplayName("Проверка метода getFood для Хищника")
    void testGetFoodForPredator() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expectedFood, animal.getFood("Хищник"));
    }

    @ParameterizedTest
    @DisplayName("Проверка исключения для неизвестного вида животного")
    @ValueSource(strings = {"", "Всеядное", "Насекомоядное", "test"})
    void testGetFoodThrowsExceptionForUnknownAnimalKind(String animalKind) {
        Exception exception = assertThrows(Exception.class, () -> {
            animal.getFood(animalKind);
        });

        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",
                exception.getMessage());
    }

    @Test
    @DisplayName("Проверка метода getFamily")
    void testGetFamily() {
        String expectedFamily = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        assertEquals(expectedFamily, animal.getFamily());
    }
}

package town;

import com.opencsv.bean.CsvDate;

import java.time.LocalDate;
import java.util.Objects;
import static town.City.isLoading;
/**
 * Класс человека (правителя города).
 */
public class Human {
    private Integer height; //Значение поля должно быть больше 0
    @CsvDate("yyyy-MM-dd")
    private java.time.LocalDate birthday;
    /**
     * Создаёт объект человека.
     * При флаге isLoading = {@code true} пропускает ввод (используется при загрузке из файла).
     * @see Waiter#getInteger(String,Integer)
     * @see Waiter#getDate()
     */
    public Human(){
        if(isLoading) {
            return;
        }
        height = Waiter.getInteger("Рост",210);
        birthday = Waiter.getDate();
    }
    /**
     * Возвращает хеш-код объекта на основе полей height и birthday.
     * @return хеш-код объекта
     */
    public int hashCode() {
        return Objects.hash(height, birthday);
    }
    /**
     * Возвращает строковое представление человека.
     * @return строка в формате "Человек {рост}: {дата рождения}"
     */
    public String toString() {
        return String.format("Человек %s: %s", height, birthday);
    }

    public boolean validate() {
        if (birthday.isAfter(LocalDate.now())) {
            System.err.println("Human: дата рождения из будущего");
            return false;
        }
        if (height <= 0 || height > 210) {
            System.err.println("Human: рост должен быть от 1 до 210 см");
            return false;
        }
        return true;
    }
}

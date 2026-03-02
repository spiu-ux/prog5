package town;

import com.opencsv.bean.CsvDate;
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
     * @see Waiter#getInteger(String)
     * @see Waiter#getDate()
     */
    public Human(){
        if(isLoading) {
            return;
        }
        height = Waiter.getInteger("Рост");
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
}

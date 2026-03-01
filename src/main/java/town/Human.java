package town;

import com.opencsv.bean.CsvDate;

import java.util.Objects;

import static town.City.isLoading;

public class Human {
    private Integer height; //Значение поля должно быть больше 0
    @CsvDate("yyyy-MM-dd")
    private java.time.LocalDate birthday;

    public Human(){
        if(isLoading) {
            return;
        }
        height = Waiter.getInteger("Рост");
        birthday = Waiter.getDate();
    }
    public int hashCode() {
        return Objects.hash(height, birthday);
    }
    public String toString() {
        return String.format("Человек %s: %s", height, birthday);
    }
}

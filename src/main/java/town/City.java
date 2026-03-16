package town;

import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvIgnore;
import com.opencsv.bean.CsvRecurse;

import java.time.LocalDateTime;

/**
 * City class - главный класс коллекции
 */
public class City implements Comparable<City>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    @CsvRecurse
    private Coordinates coordinates; //Поле не может быть null
    @CsvDate("yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float area; //Значение поля должно быть больше 0
    private Long population; //Значение поля должно быть больше 0, Поле не может быть null
    private double metersAboveSeaLevel;
    private Boolean capital; //Поле может быть null
    private Government government; //Поле может быть null
    private StandardOfLiving standardOfLiving; //Поле не может быть null
    @CsvRecurse
    private Human governor; //Поле не может быть null
    @CsvIgnore
    static Integer count = 1;
    /**
     * isLoading-флаг, который при true пропускает ввод в коллекцию
     */
    static public boolean isLoading = false;

    public City(){
        if(isLoading) {
            return;
        }
            this.id = count++;
            this.creationDate = java.time.LocalDateTime.now();
            this.governor = new Human();
            this.name = Waiter.getString("Название города");
            this.area = Waiter.getFloat("Площадь", true);
            this.population = Waiter.getLong("Население");
            this.metersAboveSeaLevel = Waiter.getDouble("Высота над уровнем моря");
            this.capital = Waiter.getBoolean(this.name);
            this.government = Waiter.enumChoice("Government", Government.class, Government.values());
            this.standardOfLiving = Waiter.enumChoice("StandartOfLiving", StandardOfLiving.class, StandardOfLiving.values());
            this.coordinates = new Coordinates();
    }

    /**
     * Обновление полей города
     */
    public void update() {
        System.out.println(
                "Какое поле обновить?\n" +
                        "1. Имя\n" +
                        "2. Координаты\n" +
                        "3. Площадь\n" +
                        "4. Население\n" +
                        "5. Метры над уровнем моря\n"+
                        "6. Столица\n"+
                        "7. Правительство\n"+
                        "8. Уровень жизни\n"+
                        "9. Губернатор");
        int n = 0;
        while(n<1||n>9){
            n=Waiter.getInteger("Какой id",null);
        }
           switch (n) {
               case 1 -> this.name = Waiter.getString("Имя");
               case 2 -> this.coordinates = new Coordinates();
               case 3 -> this.area = Waiter.getFloat("Площадь",true);
               case 4 -> this.population = Waiter.getLong("Население");
               case 5 -> this.metersAboveSeaLevel = Waiter.getDouble("Высота над уровнем моря");
               case 6 -> this.capital = Waiter.getBoolean("Столица");
               case 7 -> this.government = Waiter.enumChoice("Правительство", Government.class, Government.values());
               case 8 -> this.standardOfLiving = Waiter.enumChoice("Уровень жизни", StandardOfLiving.class, StandardOfLiving.values());
               case 9 -> this.governor = new Human();
           }
        System.out.println("Изменено!");
    }
    /**
     * @return Строковое представление города.
     */
    public String toString() {
        return String.format("id: %d\n" +
                        "name: '%s'\n" +
                        "creationDate: %s\n" +
                        "area: %.2f\n" +
                        "population: %d\n" +
                        "metersAboveSeaLevel: %.1f m\n" +
                        "capital: %s\n" +
                        "government: %s\n" +
                        "standardOfLiving: %s\n", this.id, this.name, this.creationDate,this.area,this.population,
               this.metersAboveSeaLevel,this.capital,this.government,
               this.standardOfLiving);
    }
    /**
     * @return id города
     */
    public Integer getId(){
        return id;
    }
    /**
     * @return Имя города
     */
    public String getName(){
        return name;
    }
    /**
     * @return Правителя города
     */
    public  Human getGovernor(){
        return governor;
    }
    /**
     * Сравнивает города по населению
     */
    public int compareTo(City c) {
        return Long.compare(this.population, c.population);
    }
    /**
     * @return статус столицы
     */
    public Boolean getCapital(){
        return capital;
    }
    public static void setCount(Integer newCount){
        count=newCount;
    }
        /**
         * Валидирует объект города.
         * @return {@code true}, если все поля корректны
         */
        public boolean validate() {
            if (id == null || id <= 0) {
                System.err.println("Неверный ID = " + id);
                return false;
            }
            if (name == null || name.trim().isEmpty()) {
                System.err.println("Город " + id + ": пустое название");
                return false;
            }
            if (!coordinates.validate()){
                System.err.println("Город " + id + ": неверные координаты");
                return false;
            }
            if (creationDate != null && creationDate.isAfter(LocalDateTime.now())){
                System.err.println("Город " + id + ": дата создания из будущего");
                return false;
            }
            if (area <= 0) {
                System.err.println("Город " + id + ": площадь должна быть > 0");
                return false;
            }
            if (population == null || population < 0) {
                System.err.println("Город " + id + ": неверное население");
                return false;
            }
            if (!governor.validate() && governor == null) {
                System.err.println("Город " + id + ": неверные данные правителя");
                return false;
            }
            return true;
        }

}

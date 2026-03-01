package town;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class CollectionManager {
    static ArrayDeque<City> cities = new ArrayDeque<>();
    static java.time.LocalDateTime initTime = java.time.LocalDateTime.now();

    public static void clear(){
        cities.clear();
    }
    public static void add(){
        cities.add(new City());
    }
    public static void info(){
        System.out.printf("Тип коллекции: %s\n"+
                "Количество элементов: %s\n"+
                "Время создания: %s\n", cities.getClass(),cities.size(),initTime);
    }
    public static void show(){
        for (City c : cities){
            System.out.println(c);
        }
    }
    public static void removeFirst(){
        cities.removeFirst();
    }
    public static void removeById(Integer id){
        for(City c: cities){
            if(c.getId().equals(id)){
                cities.remove(c);
                System.out.println("Город успешно удален!!!!!.");
                return;
            }
        }
        System.out.println("Город не удален!!!!!.");
    }
    public static void printUniqueGovernor(){
        HashMap<Integer, Human> dino = new HashMap<>();
        for(City c: cities){
            Human human_t = c.getGovernor();
            dino.put(human_t.hashCode(),human_t);
        }
        System.out.println(dino.values());
        for (Human h: dino.values())
            System.out.println(h);
        }

    public static void filterContainsName(String pattern){
        for(City c: cities){
            if(c.getName().contains(pattern)){
                System.out.println(c);
            }
        }
    }
    public static void update_id(Integer id) {
        City c = find_by_id(id);
        if (c == null) {return;}
        c.update();
    }

    private static City find_by_id(Integer id) {
        for (City c: cities) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        System.out.printf("Город с идентификатором '%s' не найден.\n", id);
        return null;
    }
    public static void add_if_max(City newCity) {
        for (City c: cities) {
            if (newCity.compareTo(c) < 0) {
                System.out.println("Город не был добавлен, он не является максимальным:(");
                return;
            }
        }
        cities.add(newCity);
        System.out.println("Город добавлен!");
    }
    public static void remove_el(City newCity) {
        HashSet<City> toRemove = new HashSet<>();
        for (City c: cities) {
            if (newCity.compareTo(c) > 0) {
                toRemove.add(c);
            }
        }
        for (City c: toRemove) {
            cities.remove(c);
            System.out.printf("Город '%s' удален.\n", c);
        }
        toRemove.clear();
    }
    public static void desc_capital(){
        ArrayDeque<City> cities_i = cities.clone();
        for(City c: cities.clone()){
            if(c.getCapital()){
                System.out.println(c.getCapital());
                cities_i.remove(c);
            }
        }
        for(City c: cities_i) {
                System.out.println(c.getCapital());
        }
    }
    public static ArrayDeque<City> getCity(){
        return cities;
    }
    public static void setCity(ArrayDeque<City> c){
        cities = c;
    }

}

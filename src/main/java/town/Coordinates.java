package town;


import static town.City.isLoading;
/**
 * Coordinates class
 */
public class Coordinates {
    private float x;
    private double y;
    /**
     * Создаёт новый объект координат.
     * <p>
     * При значении флага {@link town.City#isLoading} равном {@code true},
     * конструктор пропускает ввод данных.
     * Если {@code false} запрашивает значения координат через класс {@link Waiter}.
     * </p>
     * @throws NumberFormatException если введено некорректное числовое значение
     * @see Waiter#getFloat(String, boolean)
     * @see Waiter#getDouble(String)
     */
    public Coordinates() {
        if(isLoading) {
            return;
        }
        x = Waiter.getFloat("Координата х ", false);
        y = Waiter.getDouble("Координата y ");
    }
    public boolean validate() {
        if (Float.isNaN(x)) {
            System.err.println(" Coordinates: X не может быть null");
            return false;
        }
        if (Double.isNaN(y)) {
            System.err.println("Coordinates: Y не может быть null");
            return false;
        }
        return true;
    }
}

package town;


import static town.City.isLoading;

public class Coordinates {
    private float x;
    private double y;

    public Coordinates() {
        if(isLoading) {
            return;
        }
        x = Waiter.getFloat("Координата х ", false);
        y = Waiter.getDouble("Координата y ");
    }
}

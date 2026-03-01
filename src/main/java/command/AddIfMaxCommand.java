package command;

import town.City;
import town.CollectionManager;

public class AddIfMaxCommand implements Command{
    public String desc(){
        return "Добавьте город, если он больше, чем все остальные маршруты";
    }
    public void execute(String[] args) {
        CollectionManager.add_if_max(new City());
    }
}

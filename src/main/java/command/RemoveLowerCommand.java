package command;

import town.City;
import town.CollectionManager;

public class RemoveLowerCommand implements Command{
    public String desc() {
        return "Удалите все элементы, размеры которых меньше входных";
    }
    public void execute(String... args) {
        CollectionManager.removeIfLess(new City());
    }
}

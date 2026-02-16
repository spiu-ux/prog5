package command;
import town.CollectionManager;

public class ClearCommand implements Command{
    public String desc(){
        return "удалить объкт";
    }
    public void execute(){
        CollectionManager.clear();
    }
}

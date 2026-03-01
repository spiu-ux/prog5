package command;
import town.*;

public class AddCommand implements Command{
    public String desc(){
        return "объект добавлен";
    }
    public void execute(String[] args){
        CollectionManager.add();
    }
}

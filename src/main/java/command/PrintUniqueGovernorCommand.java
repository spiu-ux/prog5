package command;

import town.CollectionManager;

public class PrintUniqueGovernorCommand implements Command{
    public String desc(){
        return "Вывести уникальные значения поля governor всех элементов в коллекции";
    }
    public void execute(String[] args){
        CollectionManager.printUniqueGovernor();
    }
}

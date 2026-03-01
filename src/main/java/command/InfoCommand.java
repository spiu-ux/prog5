package command;

import town.CollectionManager;

public class InfoCommand implements Command{
    public String desc(){
        return "Вывод информации об ArrayDequeue";
    }
    public void execute(String[] args){
        CollectionManager.info();
    }
}

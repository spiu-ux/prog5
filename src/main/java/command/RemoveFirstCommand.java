package command;

import town.CollectionManager;

public class RemoveFirstCommand implements Command{
    public String desc(){
        return "Удаляет первый объект из коллекции";
    }
    public void execute(String[] args){
        CollectionManager.removeFirst();
    }
}

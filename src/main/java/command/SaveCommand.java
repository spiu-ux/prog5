package command;

import town.CollectionManager;
import town.FileManager;


public class SaveCommand implements Command{
    public String desc(){
        return "Файл сохранен!";
    }
    public void execute(String[] args){
        FileManager.writeCSV(CollectionManager.getCity());
    }
}

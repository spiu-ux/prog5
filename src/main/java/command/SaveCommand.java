package command;

import town.CollectionManager;
import town.FileManager;


public class SaveCommand implements Command{
    public String desc(){
        return "Сохранение в файл";
    }
    public void execute(String[] args){
        FileManager.writeCSV(CollectionManager.getCity());
    }
}

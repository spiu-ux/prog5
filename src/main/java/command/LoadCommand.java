package command;

import town.FileManager;


public class LoadCommand implements Command{
    public String desc(){
        return "Загрузка из файла в коллекцию";
    }
    public void execute(String[] args){
        FileManager.readCSV();
    }
}

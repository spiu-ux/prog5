package command;

import town.FileManager;


public class LoadCommand implements Command{
    public String desc(){
        return "Загрузка прошла успешно! Я молодец!";
    }
    public void execute(String[] args){
        FileManager.readCSV();
    }
}

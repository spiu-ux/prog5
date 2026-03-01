package command;

import town.CollectionManager;

public class PrintFieldDescendingCapitalCommand implements Command{
    public String desc(){
        return "Вывести значения поля capital всех элементов в порядке убывания";
    }
    public void execute(String[] args){
        CollectionManager.desc_capital();
    }
}

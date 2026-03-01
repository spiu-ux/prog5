package command;

import town.CollectionManager;

public class FilterContainsNameCommand implements Command{
    public String desc(){
        return "Вывести элементы, значение поля name которых содержит заданную подстроку";
    }
    public void execute(String[] args){
        try{
            CollectionManager.filterContainsName(args[0]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Введите аргумент!!!");
        }
    }
}

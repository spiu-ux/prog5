package managers;

public class Request {
//поля которые я буду передавать серверу,
// конструктор с Comands
// setter'ы для полей (кроме Comands)
// getter'ы для всего
    private CommandType command;
    private Object arguments;
    //private City cities;
    //private String[] arguments;
    public Request(CommandType command, Object arguments){
        this.command = command;
        this.arguments = arguments;
    }
    public CommandType getCommand(){
        return command;
    }
    public Object getArguments(){
        return arguments;
    }
//    public City getCities(){
//        return cities;
//    }
//    public String[] getArguments(){
//        return arguments;
//    }
//    public void setArguments(String [] arguments){
//        this.arguments = arguments;
//    }
//    public void setCities(City cities){
//        this.cities = cities;
//    }
}

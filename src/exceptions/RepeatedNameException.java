package exceptions;

public class RepeatedNameException extends Exception{

    public RepeatedNameException(String name){
        super("El proceso " + name + ", ya existe");
    }
}

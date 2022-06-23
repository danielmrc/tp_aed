package exception;

public class NotFoundAccountException extends Exception{

    public NotFoundAccountException(String msg){
        super(msg);
    }

    public NotFoundAccountException(){
        super("Conta não encontrada!!");
    }

    public NotFoundAccountException(String msg, Throwable cause){
        super(msg, cause);
    }
}

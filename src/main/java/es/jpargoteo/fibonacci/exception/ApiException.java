package es.jpargoteo.fibonacci.exception;

public class ApiException extends Exception {

    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

package pl.edu.mimuw.exshare;

public class DBAccessException extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return "Databse acces exception with message: " + message;
    }

    public DBAccessException (String message) {
        this.message = message;
    }
}

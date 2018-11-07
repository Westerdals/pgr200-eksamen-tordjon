package no.kristiania.pgr200.core.http;

public class Status {
    private int code;
    private String text;

    public Status(int code) {
        this.code = code;
        setText(code);
    }

    private void setText(int code) {
        switch (code) {
            case 200:
                text = "OK";
                break;
            case 201:
                text = "CREATED";
                break;
            case 204:
                text = "NO CONTENT";
                break;
            case 400:
                text = "BAD REQUEST";
                break;
            case 404:
                text = "NOT FOUND";
                break;
            default:
                this.code = 500;
                text = "Internal Server Error";
        }
    }

    @Override
    public String toString() {
        return code + " " + text;
    }
}

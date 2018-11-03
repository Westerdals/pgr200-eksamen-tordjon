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
            case 404:
                text = "NOT FOUND";
                break;
            default:
                this.code = 500;
                text = "Internal Server Error";
        }
    }


    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return code + " " + text;
    }
}

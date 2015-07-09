package parser;

public class Main {

    public static void main(String[] args) {
        // you may want to validate and format json before execute code
        // use http://jsonlint.com/ to do this
        new JsonParser("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/json");
    }
}

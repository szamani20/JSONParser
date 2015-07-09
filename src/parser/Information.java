package parser;

/**
 * Created by Szamani on 7/9/2015.
 */
public class Information {
    private String title;
    private String songPrice;
    private String currency;
    private String rights;

    public Information(String title, String songPrice, String currency, String rights) {

        this.title = title;
        this.songPrice = songPrice;
        this.currency = currency;
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public String getSongPrice() {
        return songPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRights() {
        return rights;
    }
}

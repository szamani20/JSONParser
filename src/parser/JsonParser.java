package parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szamani on 7/9/2015.
 */
public class JsonParser {
    private HttpURLConnection connection;
    private BufferedReader reader;
    private URL url;
    private InputStream inputStream;
    private StringBuffer buffer;
    private List<Information> informationList;

    public JsonParser(String address) {
        try {
            url = new URL(address); // url of the json feed

            connection = (HttpURLConnection) url.openConnection(); // open up a connection to save json
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            buffer = new StringBuffer();

            String temp;

            while ((temp = reader.readLine()) != null)
                buffer.append(temp + "\n"); // "\n" is just for make json easy to read

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect(); // we should check connection not equals to null! but i'm too lazy to do that
            try {
                reader.close(); // also reader needs to be checked. I'm still lazy :D
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(buffer.toString());

        informationList = new ArrayList<>();

        parseJSON();
        showData();
    }

    private void showData() {
        for (Information information : informationList)
            System.out.println(information.getTitle() + "   " + information.getSongPrice() + "   " + information.getCurrency() + "   " + information.getRights()); // formatting like a boss :)
    }

    private void parseJSON() {

        try {
            JSONObject entryObject = new JSONObject(buffer.toString());

            // these strings below is based on the json.
            JSONObject feedObject = entryObject.getJSONObject("feed");   // this is an object : "feed":{}
            JSONArray dataArray = feedObject.getJSONArray("entry");    // this is an array : "entry":[]

            for (int i = 0; i < dataArray.length(); i++) {
                Information information;

                JSONObject songObject = dataArray.getJSONObject(i);

                JSONObject priceObject = songObject.getJSONObject("im:price");
                JSONObject attributesObject = priceObject.getJSONObject("attributes");
                String price = attributesObject.getString("amount");
                String currency = attributesObject.getString("currency");

                JSONObject rightsObject = songObject.getJSONObject("rights");
                String rights = rightsObject.getString("label");

                JSONObject titleObject = songObject.getJSONObject("title");
                String title = titleObject.getString("label");

                information = new Information(title, price, currency, rights);

                informationList.add(information);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

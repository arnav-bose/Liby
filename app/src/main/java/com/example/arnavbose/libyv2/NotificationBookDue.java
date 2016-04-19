package com.example.arnavbose.libyv2;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Iterator;

public class NotificationBookDue {
    public static int getBorrowerNumber() {
        return 4;
    }
    public static void main (String args[]) {
        String json = "";
        int borrowerNumber = getBorrowerNumber();

        String domain = "http://127.0.0.1/";
        String notification_url = domain + "cgi-bin/koha/_notify-due.pl";
        notification_url = notification_url + "?borrowernumber=" + borrowerNumber;

        try {
            json = Jsoup.
                    connect(notification_url).
                    timeout(10 * 1000).
                    ignoreContentType(true).
                    execute().
                    body();
            System.out.println(json);
            System.out.println(notification_url);
            org.json.JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                System.out.println(key);
                String title = jsonObject.getJSONObject(key).getString("title");
                System.out.println(title);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

package com.example.arnavbose.libyv2;

/**
 * Created by shree on 19/4/16.
 */
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shree on 31/3/16.
 */
public class Scraper {
    public static void main (String args[]) {
        String domain = "http://127.0.0.1/";
        String login_url = domain + "cgi-bin/koha/opac-user.pl";
        String main_url = domain + "cgi-bin/koha/opac-main.pl";
        String username = "arnav.bose";
        String password = "b@rc3l0n@";
        try {
            Connection.Response loginForm = Jsoup.
                    connect(login_url).
                    timeout(10 * 1000).
                    method(Connection.Method.GET).
                    execute();
            Document document = Jsoup.connect(main_url)
                    .data("cookieexists", "false")
                    .data("userid", username)
                    .data("koha_login_context", "opac")
                    .data("password", password)
                    .cookies(loginForm.cookies())
                    .post();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

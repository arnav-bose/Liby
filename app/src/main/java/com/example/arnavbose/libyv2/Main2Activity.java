package com.example.shree.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main2Activity extends AppCompatActivity {

    String url = "https://gist.githubusercontent.com/anonymous/73fcf36863462ebe2e9a/raw/2a44ef7f952e669ebed2f1b2e4d1c5a9d4f69d58/file.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_main2);
        String query = "new book";
        // String url = "http://nulibrary-intra.mydnsname.org/cgi-bin/koha/catalogue/search.pl?q=" + query.replaceAll(" ", "+");
        new Title().execute();*/
       /* try {

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }




}

package com.example.arnavbose.libyv2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Search extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String url = "https://192.168.43.155/cgi-bin/koha/opac-search.pl?q=new+book";
    ArrayList<SearchDataSet> arrayList = new ArrayList<SearchDataSet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        arrayList.add(new SearchDataSet("AUTHOR", "TITLE", "BIBLIO"));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewSearchAdapter(arrayList);
        mRecyclerView.setAdapter(mAdapter);


        Button button = (Button) findViewById(R.id.buttonSearch);
        final EditText editText = (EditText) findViewById(R.id.editTextSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editText.getText().toString().replaceAll(" ", "+");
                // url = "http://nulibrary-intra.mydnsname.org/cgi-bin/koha/catalogue/search.pl?q=" + query.replaceAll(" ", "+");
                new Title().execute();

            }
        });
    }

    private class Title extends AsyncTask<String, SearchDataSet, Void> {
        String title="DEFAULT VALUE";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList.clear();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                // Connect to the web site
                Log.v("VIVEK", "url = " + url);
                Document document = Jsoup.connect(url).timeout(10 * 1000).get();
                Log.v("VIVEK", "title = " + document.title());
                Elements titles = document.getElementsByClass("title");
                Iterator<Element> iteratorTitles = titles.iterator();
                Elements authors = document.select("p.author");
                Iterator<Element> iteratorAuthors = authors.iterator();
                while(iteratorTitles.hasNext()) {
                    Element temp = iteratorTitles.next();
                    String author = iteratorAuthors.next().child(0).text();
                    String title = temp.text();
                    String biblionumber = temp.attr("href");
                    biblionumber = biblionumber.substring(biblionumber.indexOf("=") + 1);
                    Log.v("VIVEK", title + " by " + author + ":" + biblionumber);
                    SearchDataSet searchDataSet = new SearchDataSet(title, author, biblionumber);
                    publishProgress(searchDataSet);
                    // arrayList.add(searchDataSet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        protected void onProgressUpdate(SearchDataSet... searchDataSets) {
            //super.onProgressUpdate(values);
            arrayList.add(searchDataSets[0]);
        }

        @Override
        protected void onPostExecute(Void result) {

            Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
            // txttitle.setText(title);
        }
    }

    private ArrayList<SearchDataSet> getSearchDataSet() {
        ArrayList results = new ArrayList<SearchDataSet>();
        for (int index = 0; index < 20; index++) {
            SearchDataSet object = new SearchDataSet("<Title " + index + ">",
                    "<Author " + index + ">", "<BiblioNumber>");
            results.add(index, object);
        }
        return results;
    }
}

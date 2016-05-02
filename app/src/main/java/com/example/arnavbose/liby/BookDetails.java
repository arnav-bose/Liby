package com.example.arnavbose.liby;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class BookDetails extends AppCompatActivity {

    Toolbar toolbar;
    String biblioNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Book Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundleBookDetails = getIntent().getExtras();
        biblioNumber = bundleBookDetails.getString("biblioNumberBookDetails");
        Log.d("FALCON", biblioNumber);

        BookDetailsTask bookDetailsTask = new BookDetailsTask(BookDetails.this);
        String method = "Book Details";
        bookDetailsTask.execute(method, biblioNumber);
    }

    public void onClickReserve(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String borrowerNumber = sharedPreferences.getString("borrowerNumber", "");

        String method = "Reserve";
        ReserveTask reserveTask = new ReserveTask(BookDetails.this);
        reserveTask.execute(method, borrowerNumber, biblioNumber);
    }
}

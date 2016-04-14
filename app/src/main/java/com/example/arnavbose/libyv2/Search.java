package com.example.arnavbose.libyv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Search extends AppCompatActivity {

    private EditText editTextSearch;
    private String titleSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
    }

    public void onClickSearch(View view) {
        titleSearch = editTextSearch.getText().toString();
        String method = "Search";
        //if(radioButtonTitle.isChecked()){
        SearchTask searchTask = new SearchTask(Search.this);
        searchTask.execute(method, titleSearch);
        //}
    }
}

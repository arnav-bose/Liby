package com.example.arnavbose.liby;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Search extends AppCompatActivity {

    private EditText editTextSearch;
    private String titleSearch;
    private RadioButton radioButtonSearchType;
    private RadioGroup radioGroupSearchType;
    String type = "title";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        radioGroupSearchType = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroupSearchType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButtonSearchType = (RadioButton) findViewById(checkedId);
                String radioButton = radioButtonSearchType.getText().toString();
                if (radioButton.equals("Title")) {
                    type = "title";
                } else if (radioButton.equals("Author")) {
                    type = "author";
                }
            }
        });
    }


    public void onClickSearch(View view) {
        titleSearch = editTextSearch.getText().toString();
        if(!titleSearch.equals("")){
            String method = "Search";
            SearchTask searchTask = new SearchTask(Search.this);
            searchTask.execute(method, titleSearch, type);
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        else if(titleSearch.equals(""))
            Toast.makeText(this, "Please Enter Keyword to Search", Toast.LENGTH_SHORT).show();
    }

}

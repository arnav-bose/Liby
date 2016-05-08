package com.example.arnavbose.liby;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Arnav on 29/04/2016.
 */
public class AsyncTaskCurrentIssues extends AsyncTask<String, DataSetCurrentIssues, Void> {

    Context contextCurrentIsses;
    Activity activityCurrentIssues;
    RecyclerView recyclerViewCurrentIssues;
    RecyclerView.Adapter adapterCurrentIssues;
    RecyclerView.LayoutManager layoutManagerCurrentIssues;
    ArrayList<DataSetCurrentIssues> arrayListCurrentIssues = new ArrayList<>();
    TextView textViewCurrentIssues;


    public AsyncTaskCurrentIssues(Context contextCurrentIsses){
        this.contextCurrentIsses = contextCurrentIsses;
        activityCurrentIssues = (Activity)contextCurrentIsses;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        recyclerViewCurrentIssues = (RecyclerView) activityCurrentIssues.findViewById(R.id.recyclerViewCurrentIssues);
        layoutManagerCurrentIssues = new LinearLayoutManager(contextCurrentIsses);

        textViewCurrentIssues = (TextView)activityCurrentIssues.findViewById(R.id.textViewCurrentIssues);
    }

    @Override
    protected Void doInBackground(String... params) {

        //String current_issues_url = "http://192.168.99.1/cgi-bin/current_issues.pl"; //10.0.2.2 for Emulator and 192.168.43.140 for Micromax TODO: CHANGE URLs!
        //String current_issues_url = "http://172.19.17.58/cgi-bin/koha/current_issues.pl"; //TODO: Add PHP(Write) URL here.
        String current_issues_url = "http://10.0.2.2/cgi-bin/current_issues.pl";
        String method = params[0];
        if (method.equals("Current Issues")) {
            String borrowerNumber = params[1];
            try {
                URL url = new URL(current_issues_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("borrowernumber", "UTF-8") + "=" + URLEncoder.encode(borrowerNumber, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String response = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();

                inputStream.close();
                httpURLConnection.disconnect();

                //JSON Parsing
                JSONObject parentObject = new JSONObject(response);
                JSONArray parentArray = parentObject.getJSONArray("Books");
                if(parentArray.isNull(0)){
                    publishProgress();
                }
                else{
                    for (int i = 0; i <= parentArray.length(); i++) {
                        JSONObject finalObject = parentArray.getJSONObject(i);
                        String jsonTitle = finalObject.getString("title");
                        String jsonAuthor = finalObject.getString("author");
                        String jsonDueDate = finalObject.getString("due_date");

                        DataSetCurrentIssues dataSetCurrentIssues = new DataSetCurrentIssues(jsonTitle, jsonAuthor, jsonDueDate);
                        Log.d("FALCON", jsonTitle + " : " + jsonAuthor);
                        publishProgress(dataSetCurrentIssues);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(DataSetCurrentIssues... dataSetCurrentIssues) {
        if(arrayListCurrentIssues.size()!=0){
            textViewCurrentIssues.setVisibility(View.GONE);
            recyclerViewCurrentIssues.setLayoutManager(layoutManagerCurrentIssues);
            adapterCurrentIssues = new RecyclerViewCurrentIssuesAdapter(arrayListCurrentIssues);
            recyclerViewCurrentIssues.setHasFixedSize(true);
            recyclerViewCurrentIssues.setAdapter(adapterCurrentIssues);
            arrayListCurrentIssues.add(dataSetCurrentIssues[0]);
            adapterCurrentIssues.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}

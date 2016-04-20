package com.example.arnavbose.libyv2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

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

/**
 * Created by Arnav on 4/19/2016.
 */
public class BookDetailsTask extends AsyncTask<String, Void, String[]> {

    Context contextBookDetails;
    Activity activityBookDetails;

    BookDetailsTask(Context context){
        this.contextBookDetails = context;
        activityBookDetails = (Activity)context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String[] doInBackground(String... params) {
        String book_details_url = "http://10.0.2.2/cgi-bin/bookdetails.pl"; //10.0.2.2 for Emulator and 192.168.43.140 for Micromax
        String method = params[0];
        if (method.equals("Book Details")) {
            String biblionumber = params[1];
            try {
                URL url = new URL(book_details_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("biblionumber", "UTF-8") + "=" + URLEncoder.encode(biblionumber, "UTF-8");

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
                JSONArray parentArray = parentObject.getJSONArray("BookDetails");
                JSONObject finalObject = parentArray.getJSONObject(0);
                String[] bookDetails = new String[8];
                bookDetails[0] = finalObject.getString("title");
                bookDetails[1] = finalObject.getString("author");
                bookDetails[2] = finalObject.getString("count(biblionumber)");
                bookDetails[6] = finalObject.getString("notes");
                bookDetails[5] = finalObject.getString("publicationyear");
                bookDetails[3] = finalObject.getString("publishercode");
                bookDetails[4] = finalObject.getString("pages");
                bookDetails[7] = finalObject.getString("rating_value");

                return bookDetails;

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
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String[] result) {
        TextView textViewTitle = (TextView)activityBookDetails.findViewById(R.id.textViewBookDetailsTitle);
        TextView textViewAuthor = (TextView)activityBookDetails.findViewById(R.id.textViewBookDetailsAuthor);
        TextView textViewAvailable = (TextView)activityBookDetails.findViewById(R.id.textViewBookDetailsAvailable);
        TextView textViewPublisher = (TextView)activityBookDetails.findViewById(R.id.textViewBookDetailsPublisher);
        TextView textViewPages = (TextView)activityBookDetails.findViewById(R.id.textViewBookDetailsPages);
        TextView textViewYear = (TextView)activityBookDetails.findViewById(R.id.textViewBookDetailsYear);
        TextView textViewDescription = (TextView)activityBookDetails.findViewById(R.id.textViewBookDetailsDescription);

        textViewTitle.setText(result[0]);
        textViewAuthor.setText(result[1]);
        textViewAvailable.setText(result[2]);
        textViewPublisher.setText(result[3]);
        textViewPages.setText(result[4]);
        textViewYear.setText(result[5]);
        textViewDescription.setText(result[6]);
    }
}

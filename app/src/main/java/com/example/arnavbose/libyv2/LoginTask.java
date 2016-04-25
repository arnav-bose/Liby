package com.example.arnavbose.libyv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
=======
import android.content.IntentFilter;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
>>>>>>> pradumn
import android.text.style.TtsSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD
=======
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

>>>>>>> pradumn
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
 * Created by Arnav on 4/20/2016.
 */
public class LoginTask extends AsyncTask<String, Void, String> {

    Context contextLogin;
    Activity activityLogin;
    EditText editTextUsername;
    EditText editTextPassword;
    Boolean login;
    String jsonFirstName = "";
    String jsonLastName = "";
    String jsonBorrowerNumber = "";

    LoginTask(Context context){
        this.contextLogin = context;
        activityLogin = (Activity)context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        editTextUsername = (EditText)activityLogin.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)activityLogin.findViewById(R.id.editTextPassword);
    }

    @Override
    protected String doInBackground(String... params) {

<<<<<<< HEAD
        String search_url = "http://10.0.2.2/cgi-bin/login.pl"; //10.0.2.2 for Emulator and 192.168.43.140 for Micromax
=======
        String search_url = "http://192.168.43.140/cgi-bin/login.pl"; //10.0.2.2 for Emulator and 192.168.43.140 for Micromax
>>>>>>> pradumn
        String method = params[0];
        if (method.equals("Login")) {
            String username = params[1];
            String password = params[2];
            try {
                URL url = new URL(search_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data =
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
//                        URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(titleSearch, "UTF-8");

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

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

<<<<<<< HEAD
=======

>>>>>>> pradumn
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String test = "";
        //JSON Parsing
        if(result.equals("{\"Login\":[]}")){
            Toast.makeText(contextLogin, "Login Failed. Please Try Again.", Toast.LENGTH_SHORT).show();
            editTextUsername.setText("");
            editTextPassword.setText("");
            login = false;
        }
        else{
            try{
                JSONObject parentObject = new JSONObject(result);
                JSONArray parentArray = parentObject.getJSONArray("Login");
                JSONObject finalObject = parentArray.getJSONObject(0);
                jsonFirstName = finalObject.getString("firstname");
                jsonLastName = finalObject.getString("surname");
                jsonBorrowerNumber = finalObject.getString("borrowernumber");

                Intent i = new Intent(contextLogin, MainActivity.class);
                contextLogin.startActivity(i);
                login = true;
                activityLogin.finish();

            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

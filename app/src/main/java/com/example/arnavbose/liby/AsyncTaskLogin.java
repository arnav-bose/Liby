package com.example.arnavbose.liby;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.EditText;
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

/**
 * Created by Arnav on 4/20/2016.
 */
public class AsyncTaskLogin extends AsyncTask<String, Void, String> {

    Context contextLogin;
    Activity activityLogin;
    EditText editTextUsername;
    EditText editTextPassword;
    Boolean login;
    String jsonFirstName = "";
    String jsonLastName = "";
    String jsonBorrowerNumber = "";
    ProgressDialog progressDialogLogin;

    AsyncTaskLogin(Context context){
        this.contextLogin = context;
        activityLogin = (Activity)context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        editTextUsername = (EditText)activityLogin.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)activityLogin.findViewById(R.id.editTextPassword);

        progressDialogLogin = new ProgressDialog(activityLogin);
        progressDialogLogin.setTitle("Liby");
        progressDialogLogin.setMessage("Logging In...");
        progressDialogLogin.setIndeterminate(false);
        progressDialogLogin.show();
    }

    @Override
    protected String doInBackground(String... params) {

        //String login_url = "http://192.168.99.1/cgi-bin/login.pl"; //10.0.2.2 for Emulator and 192.168.99.101 for Connectify
        //String login_url = "http://172.19.17.58/cgi-bin/koha/login.pl"; //TODO: Add PHP(Write) URL here.
        String login_url = "http://" + AppData.SERVER_ADDRESS + "/cgi-bin/login.pl";
        String method = params[0];
        if (method.equals("Login")) {
            String username = params[1];
            String password = params[2];
            try {
                URL url = new URL(login_url);
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
            progressDialogLogin.dismiss();
        }
        else{
            try{
                JSONObject parentObject = new JSONObject(result);
                JSONArray parentArray = parentObject.getJSONArray("Login");
                JSONObject finalObject = parentArray.getJSONObject(0);
                jsonFirstName = finalObject.getString("firstname");
                jsonLastName = finalObject.getString("surname");
                jsonBorrowerNumber = finalObject.getString("borrowernumber");

                AppData.LOGIN_CHECK = true;
                AppData.myData = PreferenceManager.getDefaultSharedPreferences(contextLogin);
                SharedPreferences.Editor editor = AppData.myData.edit();
                editor.putString("borrowerNumber", jsonBorrowerNumber);
                editor.putString("name", jsonFirstName + " " + jsonLastName);
                editor.putBoolean("LOGIN_CHECK", AppData.LOGIN_CHECK);
                editor.apply();
                progressDialogLogin.dismiss();

                Intent i = new Intent(contextLogin, MainActivity.class);
                contextLogin.startActivity(i);
                activityLogin.finish();

            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

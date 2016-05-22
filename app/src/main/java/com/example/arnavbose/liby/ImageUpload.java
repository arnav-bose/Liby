package com.example.arnavbose.liby;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ImageUpload extends AppCompatActivity implements View.OnClickListener {

    public static final String UPLOAD_URL = "http://" + AppData.SERVER_ADDRESS + "/upload_image/upload.php";
    public static final String UPLOAD_KEY = "image";

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;

    private EditText editTextNotesTitle;
    private EditText editTextNotesContributedBy;

    private ImageView imageView;

    private Bitmap bitmap;

    private Uri filePath;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);

        editTextNotesTitle = (EditText)findViewById(R.id.editTextNotesTitle);
        editTextNotesContributedBy = (EditText)findViewById(R.id.editTextNotesContributedBy);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbarNotes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contribute Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser();
        }

        if(v == buttonUpload){
            if(!editTextNotesTitle.getText().toString().equals("") && !editTextNotesContributedBy.getText().toString().equals("") && imageView.getDrawable()!=null){
                uploadImage();
            }
            else
                Toast.makeText(this, "Please enter the details of your note", Toast.LENGTH_SHORT).show();
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        return encodedImage;
    }

    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog progressDialogImageUpload;
            String title = editTextNotesTitle.getText().toString();
            String contributedBy = editTextNotesContributedBy.getText().toString();
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialogImageUpload = new ProgressDialog(ImageUpload.this);
                progressDialogImageUpload.setTitle("Liby");
                progressDialogImageUpload.setMessage("Uploading...");
                progressDialogImageUpload.setIndeterminate(false);
                progressDialogImageUpload.show();
                title.replace(' ', '_');
                contributedBy.replace(' ', '_');

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialogImageUpload.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();

                data.put(UPLOAD_KEY, uploadImage);
                data.put("title", title);
                data.put("contributed_by", contributedBy);
                String result = rh.sendPostRequest(UPLOAD_URL, data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
}
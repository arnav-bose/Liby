package com.example.arnavbose.liby;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by arnavbose on 13-02-2016.
 */
public class FragmentNotes extends Fragment {

    public static int flag=0;
    String picturePath;
    // LogCat tag
    private static final String TAG = Menu.class.getSimpleName();
    public static final String IMAGE_DIRECTORY_NAME = "Cloud Uploads";

    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int GALLERY_UPLOAD_IMAGE_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;

    private Bitmap bitmap;
    private Uri fileUri; // file url to store image/video

    Button buttonNotes, buttonRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes, container, false);

        setRetainInstance(true);
        buttonRefresh = (Button)view.findViewById(R.id.buttonRefresh);

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskGetURL asyncTaskGetURL = new AsyncTaskGetURL(getContext());
                asyncTaskGetURL.execute();

                String[] path = asyncTaskGetURL.jsonPath;
                int i = 0;
                while(path[i]!=null){
                    AsyncTaskGetNotes asyncTaskGetNotes = new AsyncTaskGetNotes(getContext());
                    asyncTaskGetNotes.execute(path);
                    i++;
                }
            }
        });

        buttonNotes = (Button)view.findViewById(R.id.buttonNotes);
        buttonNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                chooseGallery();
            }
        });

        getActivity().setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return view;
    }

    private void chooseGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, GALLERY_UPLOAD_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            // successfully captured the image
            //refreshing the gallery
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(fileUri);
            getActivity().sendBroadcast(mediaScanIntent);
            flag=1;
            launchUploadActivity(true);             // launching upload activity


        }else if (requestCode == GALLERY_UPLOAD_IMAGE_REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            // Successfully selected the pic from gallery
            // launching upload activity
            fileUri = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getActivity().getContentResolver().query(fileUri,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            picturePath = c.getString(columnIndex);
            c.close();
            flag=2;
            launchUploadActivity(true);

        } else if (resultCode == getActivity().RESULT_CANCELED) {

            // user cancelled Image capture
            Toast.makeText(getContext(),
                    "User cancelled image capture", Toast.LENGTH_SHORT)
                    .show();

        } else {
            // failed to capture image
            Toast.makeText(getContext(),
                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void launchUploadActivity(boolean isImage) {
        Intent i = new Intent(getActivity(), ImageUpload.class);
        i.putExtra("isImage", isImage);
        if(flag==1){
            i.putExtra("filePath", fileUri.getPath());
        }else if(flag==2) {
            i.putExtra("picturePath", picturePath);
        }else{
            Toast.makeText(getContext(),"Flag neither 1 nor 2!",Toast.LENGTH_LONG).show();
        }
        startActivity(i);
    }
}
